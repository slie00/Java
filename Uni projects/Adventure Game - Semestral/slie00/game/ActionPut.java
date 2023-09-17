package game;

/**
 * Třída představující nazev akce a související s ní textové řetězce příkazů, pomocí které hráč může vrátit objekt do místntosti
 * Obsahuje metody, které vyhodnocují jednotlivé příkazy zadané uživatelem.
 * 
 * @author Sliusareva Elizaveta
 * @version ZS-2020, 2021-01-10 
 */

public class ActionPut implements IAction
{ private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public ActionPut(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>polož</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "polož";
    }

    /**
     * Metoda provádí kontrolu, zda hráč zadal spravné parametry
     * Provádí se kontrola, zda objekt, který hráč chce vrátit do pokoje je v batohu
     * Pokud hráč vrátil předmět do pokoje, metoda odstrání ho z batohu, přídá ho název do pokoje a vrátí přílušný textový řetězec
     */
    @Override
    public String execute(String... parameters)
    {
        if (parameters.length == 0) {
            return "Tomu nerozumím, musíš mi říct, co mám dělat";
            
        }
        
        if (parameters.length>1) {
            return "Tomu nerozumím, neumím položit více věcí současně";
            
        }
        
        String itemName = parameters[0];
        if(!game.getBag().contains(itemName)){
            return "Předmět '" + itemName + "' není v batohu";
        }
        
        Item item = game.getBag().removeItem(itemName);

        game.getWorld().getCurrentPlace().addItem(item);
        
     
        return "Položil(a) jsi předmět '" + itemName + "' do pokoje";
        
        
        
    }
}
