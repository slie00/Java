package game;
/**
 * Třída představující nazev akce a související s ní textové řetězce příkazů, pomocí které se hráč pohybuje mezi místnostmi. 
 * Kontroluje se, zda cíl, kam se hrdina pohybuje, je správně nastaven a taky jestli zadaný prostor ve hře vůbec existuje. 
 * Provádí kontrolu, jestli je splněná podmínka výhry. 
 * Obsahuje podmínky, po kterých je hra automaticky považována za prohránou.
 *
 * @author Sliusareva Elizaveta
 * @version ZS-2020, 2021-01-10
 */

public class ActionMove implements IAction
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public ActionMove(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>jdi</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "jdi";
    }
    
    private final static String errorText = "Nemáš dost sil na to, abys šel/šla do Podkroví";
    private final static String dieText = "Nepřečetl(a) jsi knihu, která by uklidnila ducha a on tě zabil. Litujeme!";
    private final static String dieWithTrap = "Tvá zvědavost tě dostala do špatné situace, byl(a) jsi chycen(a) do pavoučí sítě!";
    private final static String win = "Prošel/prošla jsi všemi zkoušky a vyhral(a) jsi, běž domů!";
    
    /** 
     * Nejdřív metoda provádí kontrolu, zda hráč zadal spravné parametry;
     * Provádí se kontrola, zda hráč snědl čokoládu a má dost sil na to, aby šel do místnosti s duchem;
     * Provádí se kontrola, zda hráč našel knihu předtím než jít do místnosti s duchem;
     * Provádí se kontrola, zda hráč má v batohu klíč - podmínka pro výhru. Pokud klíč nemá, do východu se nedostáne.
     * Ukončí hru výhrou, jestli jsou splněny všechny podmínky;
     * Ukončí hru prohrou, pokud hráč zajde do místnosti "sklep";
     * Provádí se kontrola, zda pokoj sousedí s místností, do které se hráč z aktiální místnosti chce dostat. Pokud ne, vratí textový řetězec, že tam jít nemůže
     */
    @Override
    public String execute(String... parameters)
    {
        if (parameters.length == 0) {  // Pokud hráč nezadá žádný parametr (cíl cesty)
            return "Tomu nerozumím, musíš zadat nějaký cíl, kam mám jít.";
        }

        if (parameters.length > 1) {  // Pokud hráč zadá více parametrů
            return "Tomu nerozumím, neumím se 'rozdvojit' a jít na více míst současně.";
        }

        String targetPlaceName = parameters[0];

        for (Place place : game.getWorld().getCurrentPlace().getNeighbors()) {
            if (targetPlaceName.equals(place.getName())) {
                if(targetPlaceName.equals("Podkroví") && game.isStrong() == false) {
                    return errorText;
                }
                
                if(game.getWorld().getCurrentPlace().getName().equals("Podkroví") && game.getWorld().getCurrentPlace().containsItem("duch")) {
                    game.setGameOver(true);
                    return dieText;
                }
                
                if(targetPlaceName.equals("Výhod") && !game.getBag().contains("klíč")) {
                    return "Nemas klic todo";
                }
                
                game.getWorld().setCurrentPlace(place);
                
                if(targetPlaceName.equals("Výhod")) {
                    game.setGameOver(true);
                    return win;
                }
                if(game.getWorld().getCurrentPlace().getName().equals("Sklep")) {
                    game.setGameOver(true);
                    return dieWithTrap;
                }
                
                return place.getFullDescription();
            }
        }

        return "Do prostoru '" + targetPlaceName + "' se odsud jít nedá.";
    }
}
