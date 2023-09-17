package game;
import java.util.List;

/**
 * Třída představující nazev akce a související s ní textové řetězce příkazů, pomocí které hráč může sníst objekt  
 * Obsahuje metody, které vyhodnocují jednotlivé příkazy zadané uživatelem.
 * 
 * @author Sliusareva Elizaveta
 * @version ZS-2020, 2021-01-10 
 */
public class ActionEat implements IAction 
{ private Game game;

    /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public ActionEat(Game game)
    {
        this.game = game;
    }

    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>sněz</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "sněz";
    }
    
    /**
     * Metoda provádí kontrolu, zda hráč zadal spravné parametry
     * Provádí se kontrola, zda objekt, který hráč chce sníst je v batohu:  
     * pokud je to čokoláda, postava má síly
     * Metoda kontroluje, zda je objekt jedlý a vrátí odpovídající texové řetězce
     */
    @Override
    public String execute(String... parameters)
    {
        if (parameters.length == 0) {
            return "Tomu nerozumím, musíš mi říct, co mám dělat";
            
        }
        
        if (parameters.length>1) {
            return "Tomu nerozumím, neumím jíst více potravin současně";
            
        }
        
        String itemName = parameters[0];
        if(!game.getBag().contains(itemName)){
            return "Věc '" + itemName + "' není v batohu";
        }
        boolean eatable = false;
        List<Item> items = game.getBag().getItems();
        int size = items.size();
        for (int i = 0; i < size; i++) {
            Item item = items.get(i);
            if(item.getName().equals(itemName) && item.isEatable()) {
                Item removedItem = game.getBag().removeItem(itemName);
                eatable = true;
                if(removedItem.getName().equals("čokoláda")) {
                    game.setStrong();
                }
                break;
            }
        }
        
        if(eatable) {
            return "Snědl(a) jsi '" + itemName  + "' a teď máš více síl.";
        } else {
            return "Tohle sníst nemůžeš";
        }
      
 
    }
}
