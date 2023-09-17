package game;

/**
 * Třída představující nazev akce a související s ní textové řetězce příkazů, pomocí které hráč dozví aktualní invěntář hlavní postavy  
 * Obsahuje metody, které vyhodnocují jednotlivé příkazy zadané uživatelem.
 * 
 * @author Sliusareva Elizaveta
 * @version ZS-2020, 2021-01-10 
 */
public class ActionBag implements IAction
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public ActionBag(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>batoh</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "batoh";
    }
    
    /**
     * Vrátí textový řetězec s názvy všech věcí v batohu
     */

    @Override
    public String execute(String... parameters)
    {
        return "Věci v batohu:\n"
                + game.getBag().getItems();
    }
}
