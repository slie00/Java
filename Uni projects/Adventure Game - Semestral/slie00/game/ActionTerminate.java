package game;

/**
 * Třída implementující příkaz pro předčasné ukončení hry.
 * 
 * @author Sliusareva Elizaveta
 * @version ZS-2020, 2021-01-10
 */
public class ActionTerminate implements IAction
{
    private Game game;
    
    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public ActionTerminate(Game game)
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
        return "konec";
    }
    
    /**
     * V případě, že je metoda zavolána bez parametrů <i>(hráč na konzoli zadá
     * pouze slovo <b>konec</b>)</i>, ukončí hru. Jinak vrátí chybovou zprávu
     * a hra pokračuje.
     *
     * @param parameters parametry příkazu <i>(očekává se prázdné pole)</i>
     * @return informace pro hráče, které hra vypíše na konzoli 
     */
    @Override
    public String execute(String... parameters)
    {
        if (parameters.length > 0) {
            return "Tomu nerozumím, nemůžeš si vybírat, co ukončit." +
                   "\nTento příkaz se používá bez parametru a ukončuje celou hru.";
        }

        game.setGameOver(true);

        return "Hra byla ukončena příkazem KONEC.";
    }
}
