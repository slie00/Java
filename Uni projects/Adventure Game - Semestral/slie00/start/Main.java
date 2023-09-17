package start;

import game.Game;
import tests.Scenario;
import tests.Tests;
import ui.TextUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Spouštěcí třída aplikace.
 *
 * @author Sliusareva Elizaveta
 * @version ZS-2020, 2021-01-10
 */
public class Main
{
    /**
     * Spouštěcí metoda aplikace. Vyhodnotí parametry, se kterými byla aplikace
     * spuštěna, a na základě nich rozhodne, jakou operaci provede <i>(hra, základní
     * nebo podrobný výpis testovacích scénářů, spuštění testovacích scénářů)</i>.
     * <p>
     * Pokud byla aplikace spuštěna s nepodporovanými parametry, vypíše nápovědu
     * a skončí.
     *
     * @param args parametry aplikace z příkazové řádky
     */
    public static void main(String[] args)
    {
        if (args.length == 0) {
            Game game = new Game();
            TextUI ui = new TextUI(game);

            ui.play();

            return;
        }

        if (!Mode.checkMode(args[0])) {  // Ověříme, zda je první parametr přípustný
            System.out.println("Aplikace byla spuštěna s nepodporovaným parametrem '" + args[0] + "'!");

            System.out.println("\nAplikaci je možné spustit s následujícími parametry:");
            System.out.println("\n  <bez parametrů>                        : Spustí hru.");
            System.out.println("\n  SIMULATE_SIMPLE SCÉNÁŘ_1 SCÉNÁŘ_2 ...  : Vypíše kroky zadaných scénářů");
            System.out.println("                                           (pouze příkazy a odpovědi)");
            System.out.println("\n  SIMULATE_COMPLEX SCÉNÁŘ_1 SCÉNÁŘ_2 ... : Vypíše kroky zadaných scénářů");
            System.out.println("                                           (příkazy, odpovědi a další");
            System.out.println("                                           požadavky na stav hry)");
            System.out.println("\n  RUN_TESTS SCÉNÁŘ_1 SCÉNÁŘ_2 ...        : Provede testy zadaných scénářů");
            System.out.println("\n  Názvy scénářů u parametrů SIMULATE_SIMPLE, SIMULATE_COMPLEX a RUN_TESTS");
            System.out.println("  jsou nepovinné. Pokud nebude uveden žádný název scénáře, operace se provede");
            System.out.println("  pro všechny existující scénáře.");

            return;
        }

        List<Scenario> scenarios = null;
        if (args.length == 1) {
            scenarios = Arrays.asList(Scenario.values());  // V parametrech nejsou žádné názvy scénářů, budeme zpracovávat všechny
        } else {
            scenarios = new ArrayList<>();

            for (int i = 1; i < args.length; i++) {  // Zpracujeme všechny scénáře předané v parametrech (začínáme od 2. parametru)
                String scenarioName = args[i];

                if (checkScenarioName(scenarioName)) {  // Ověříme, zda scénář s daným názvem existuje
                    scenarios.add(Scenario.valueOf(scenarioName));
                } else {
                    System.out.println("Scénář s názvem '" + scenarioName + "' neexistuje.");
                }
            }

            if (scenarios.isEmpty()) {  // Ověříme, zda po kontrole zbyl alespoň jeden scénář ke zpracování
                System.out.print("\nVšechny zadané názvy scénářů jsou neplatné!");
                return;
            }
        }

        Tests tests = new Tests();

        switch (Mode.getMode(args[0])) {
            case SIMULATE_SIMPLE:
                tests.simulate(scenarios, false);
                break;
            case SIMULATE_COMPLEX:
                tests.simulate(scenarios, true);
                break;
            case RUN_TESTS:
                tests.runTests(scenarios);
                break;
        }
    }

    private static boolean checkScenarioName(String scenarioName)
    {
        for (Scenario s : Scenario.values()) {
            if (s.name().equals(scenarioName)) {
                return true;
            }
        }

        return false;
    }
}
