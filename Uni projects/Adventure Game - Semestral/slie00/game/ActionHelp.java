package game;

/**
 * Třída představující nazev a těxtový řetězec příkazu, pomocí kterého hráč dozví, co má dělat
 * 
 * @author Sliusareva Elizaveta
 * @version ZS-2020, 2021-01-10
 */

public class ActionHelp implements IAction
{
    private Game game;
    
    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public ActionHelp(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>nápověda</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "nápověda";
    }

    /**
     * Metoda vrací obecnou nápovědu ke hře. Nyní vypisuje vcelku primitivní
     * zprávu o herním příběhu a seznam dostupných příkazů, které může hráč
     * používat.
     *
     * @param parameters parametry příkazu <i>(aktuálně se ignorují)</i>
     * @return nápověda, kterou hra vypíše na konzoli
     */
    @Override
    public String execute(String... parameters)
    {
        return "Tvým úkolem je najít východ ze strašidelného domu."
                + "\nPotkáš zde zlého ducha, ale budeš mít šanci se s ním spřátelit"
                + "\n\nVe hře můžeš používat tyto příkazy:\n"
                + game.getAllActions().getAllNames();
    }
}
