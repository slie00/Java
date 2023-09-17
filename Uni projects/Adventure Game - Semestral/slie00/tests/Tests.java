package tests;

import game.Game;
import game.Item;
import game.Place;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Třída obsahuje logiku pro výpis a vyhodnocování testovacích scénářů.
 *
 * @author  Jan Říha
 * @version ZS-2020, 2020-12-17
 */
public class Tests
{
    /**
     * Metoda provede výpis předaných testovacích scénářů na konzoli.
     *
     * @param scenarios kolekce testovacích scénářů pro výpis
     * @param complex příznak, zda se má provádět podrobný výpis <i>(včetně požadovaných stavů hry)</i>
     */
    public void simulate(Collection<Scenario> scenarios, boolean complex)
    {
        for (Scenario scenario : scenarios) {
            System.out.println("\nScénář: " + scenario.name()
                    + "\n##################################################");

            simulate(scenario, complex);
        }
    }

    private void simulate(Scenario scenario, boolean complex)
    {
        for (ScenarioStep step : scenario.steps()) {
            System.out.println("\n" + step.index + ". " + step.command
                    + "\n--------------------------------------------------"
                    + "\nOdpověď hry: " + (step.message == null ? "<nevyhodnocuje se>" : step.message));

            if (complex) {
                System.out.println("--------------------------------------------------"
                        + "\nProstor:     " + (step.place == null ? "<nevyhodnocuje se>" : step.place)
                        + "\nSousedé:     " + (step.neighbors == null ? "<nevyhodnocuje se>" : collectStrings(step.neighbors))
                        + "\nPředměty:    " + (step.items == null ? "<nevyhodnocuje se>" : collectStrings(step.items))
                        + "\nBatoh:       " + (step.bag == null ? "<nevyhodnocuje se>" : collectStrings(step.bag)));
            }

            System.out.println("==================================================");
        }
    }

    /**
     * Metoda spustí testy předaných testovacích scénářů a vypisuje na konzoli podrobné výsledky.
     *
     * @param scenarios kolekce testovacích scénářů pro spuštění
     */
    public void runTests(Collection<Scenario> scenarios)
    {
        boolean allSuccess = true;

        for (Scenario scenario : scenarios) {
            System.out.println("\nScénář: " + scenario.name()
                    + "\n##################################################");

            if (runTest(scenario)) {
                System.out.println("\nSCÉNÁŘ PROBĚHL ÚSPĚŠNĚ");
            } else {
                System.out.println("\n!!! BĚHEM SCÉNÁŘE DOŠLO K CHYBÁM !!!");
                allSuccess = false;
            }
        }

        System.out.println("\n##################################################");
        if (allSuccess) {
            System.out.println("\nTEST PROBĚHL ÚSPĚŠNĚ");
        } else {
            System.out.println("\n!!! BĚHEM TESTU DOŠLO K CHYBÁM !!!");
        }
    }

    private boolean runTest(Scenario scenario) 
    {
        Game game = new Game();  // Připravíme novou hru pro provedení testu
        boolean success = true;

        for (ScenarioStep step : scenario.steps()) {
            //Thread.sleep(2000);
            String actionResult = game.executeAction(step.command);  // Spustíme příkaz z testovacího scénáře

            CheckResult actionResultCheck = checkActionResult(step, actionResult);  // Provedeme všechny kontroly
            CheckResult placeCheck = checkPlace(step, game);
            CheckResult neighborsCheck = checkNeighbors(step, game);
            CheckResult placeItemsCheck = checkPlaceItems(step, game);
            CheckResult bagItemsCheck = checkBagItems(step, game);

            System.out.println("\n" + step.index + ". " + step.command  // Vypíšeme informace
                    + "\n--------------------------------------------------"
                    + "\n" + actionResult
                    + "\n--------------------------------------------------"
                    + "\nOdpověď hry: " + actionResultCheck.getMessage()
                    + "\n--------------------------------------------------"
                    + "\nProstor:     " + placeCheck.getMessage()
                    + "\nSousedé:     " + neighborsCheck.getMessage()
                    + "\nPředměty:    " + placeItemsCheck.getMessage()
                    + "\nBatoh:       " + bagItemsCheck.getMessage()
                    + "\n==================================================");

            // Vyhodnotíme dopad kroku na celkový výsledek scénáře
            success = success && actionResultCheck.isSuccess() && placeCheck.isSuccess()
                    && neighborsCheck.isSuccess() && placeItemsCheck.isSuccess()
                    && bagItemsCheck.isSuccess();
        }

        return success;
    }

    private CheckResult checkActionResult(ScenarioStep step, String actionResult)
    {
        if (step.message == null) {
            return CheckResult.IGNORED_RESULT;
        }

        if (step.message.equals(actionResult)) {
            return new CheckResult(true, "OK");
        }

        return new CheckResult(false, "FAIL (test očekává:\n" + step.message + ")" );
    }

    private CheckResult checkPlace(ScenarioStep step, Game game)
    {
        if (step.place == null) {
            return CheckResult.IGNORED_RESULT;
        }

        if (step.place.equals(game.getWorld().getCurrentPlace().getName())) {
            return new CheckResult(true, "OK");
        }

        return new CheckResult(false, "FAIL (test očekává: " + step.place + ")" );
    }

    private CheckResult checkNeighbors(ScenarioStep step, Game game)
    {
        if (step.neighbors == null) {
            return CheckResult.IGNORED_RESULT;
        }

        Set<String> wantedNeighbors = Set.of(step.neighbors);
        Set<String> actualNeighbors = new HashSet<>();

        for (Place neighbor : game.getWorld().getCurrentPlace().getNeighbors()) {
            actualNeighbors.add(neighbor.getName());
        }

        if (wantedNeighbors.size() == actualNeighbors.size() && !actualNeighbors.retainAll(wantedNeighbors)) {
            return new CheckResult(true, "OK");
        }

        return new CheckResult(false, "FAIL (test očekává: " + collectStrings(step.neighbors) + ")" );
    }

    private CheckResult checkPlaceItems(ScenarioStep step, Game game)
    {
        if (step.items == null) {
            return CheckResult.IGNORED_RESULT;
        }

        Set<String> wantedItems = Set.of(step.items);
        Set<String> actualItems = new HashSet<>();

        for (Item item : game.getWorld().getCurrentPlace().getItems()) {
            if(!item.isVisible()) continue;
            actualItems.add(item.getName());
        }

        if (wantedItems.size() == actualItems.size() && !actualItems.retainAll(wantedItems)) {
            return new CheckResult(true, "OK");
        }

        return new CheckResult(false, "FAIL (test očekává: " + collectStrings(step.items) + ")" );
    }

    private CheckResult checkBagItems(ScenarioStep step, Game game)
    {
       if (step.items == null) {
            return CheckResult.IGNORED_RESULT;
        }

        Set<String> wantedItems = Set.of(step.bag);
        Set<String> actualItems = new HashSet<>();

        for (Item item : game.getBag().getItems()) {
            actualItems.add(item.getName());
        }

        if (wantedItems.size() == actualItems.size() && !actualItems.retainAll(wantedItems)) {
            return new CheckResult(true, "OK");
        }

        return new CheckResult(false, "FAIL (test očekává: " + collectStrings(step.items) + ")" );
    }

    private static String collectStrings(String[] strings)
    {
        return Stream.of(strings)
                     .collect(Collectors.joining(", "));
    }

}
