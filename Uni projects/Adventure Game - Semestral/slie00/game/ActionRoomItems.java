package game;

/**
 * Třída představující nazev akce a související s ní textové řetězce příkazů, pomocí které hráč dozví aktualní seznám věcí v místnosti   
 * Obsahuje metody, které vyhodnocují jednotlivé příkazy zadané uživatelem.
 * 
 * @author Sliusareva Elizaveta
 * @version ZS-2020, 2021-01-10 
 */
public class ActionRoomItems implements IAction
{
    private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public ActionRoomItems(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>pokoj</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "pokoj";
    }

    /**
     * Metoda kontroluje, zda je předmět viditelný hned po přechodu hráče do místnosti
     * Podle toho, jestli je to pravda nebo ne, vrátí odpovídající texové řetězce
     */
    @Override
    public String execute(String... parameters)
    {
        String items = "";
        for(Item item : game.getWorld().getCurrentPlace().getItems()) {
            if(item.isVisible()) {
                items += " " + item.getName();
        
            }
        }
        if(items.length() == 0){
            return "Nejsou tu žádné věci.";
        }
        return "Věci v pokoje '" + game.getWorld().getCurrentPlace().getName() + "': "
                + items;
    }
}
