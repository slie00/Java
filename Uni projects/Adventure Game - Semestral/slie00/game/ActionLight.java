package game;
/**
 * Třída představující nazev akce a související s ní textové řetězce příkazů, pomocí které hráč může rozsvětit pokoj  
 * Obsahuje metody, které vyhodnocují jednotlivé příkazy zadané uživatelem.
 * 
 * @author Sliusareva Elizaveta
 * @version ZS-2020, 2021-01-10 
 */
public class ActionLight implements IAction
{ private Game game;
    
    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public ActionLight(Game game)
    {
        this.game = game;
    }
    
    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>rozsvěti</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "rozsvěti";
    }
    /**
     * Metoda provádí kontrolu, zda hráč zadal spravné parametry a jestli baterka, pomocí které je možné rozsvětit pokoj, je vůbec v batohu.
     * Pokud ano, baterka je používáná a metoda ji odstrání z batohu. 
     * Pak všechny předměty v pokoje se stávají viditelnýmí 
     * @return odpovídající texový řetězec
     */
    @Override
    public String execute(String... parameters)
    {
       
        
        if (parameters.length>=1) {
            return "Tomu nerozumím";
        }
        
        if(!game.getBag().contains("baterka")){
            return "Baterka není v batohu";
        }
        
        game.getBag().removeItem("baterka");

        game.getWorld().getCurrentPlace().setItemsVisible();
        
        return "Rozsvítil(a) jsi tenhle pokoj,teď můžeš najít skryté věci...";
        
    }
}
