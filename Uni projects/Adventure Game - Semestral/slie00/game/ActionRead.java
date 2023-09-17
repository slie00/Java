package game;

/**
 * Třída představující nazev akce a související s ní textové řetězce příkazů, pomocí které hráč může přečíst objekt  
 * Obsahuje metody, které vyhodnocují jednotlivé příkazy zadané uživatelem.
 * 
 * @author Sliusareva Elizaveta
 * @version ZS-2020, 2021-01-10 
 */

public class ActionRead implements IAction
{ private Game game;
    
     /**
     * Konstruktor třídy.
     *
     * @param game hra, ve které se bude příkaz používat
     */
    public ActionRead(Game game)
    {
        this.game = game;
    }
    
    /**
     * Metoda vrací název příkazu tj.&nbsp; slovo <b>přečti</b>.
     *
     * @return název příkazu
     */
    @Override
    public String getName()
    {
        return "přečti";
    }

    /**
     * Metoda kontroluje, zda hráč zadal spravné parametry
     * Provádí se kontrola,  objekt, který hráč chce přečíst je v batohu a zda persona, které postava má přečíst knihu je v pokoje 
     * Metoda kontroluje, zda objekt, kterému máme přečíst knihu není věc
     * Pokud výše uvedené podmínky jsou splněný, metoda odstrání z pokoje všechny objekty a v batohu se objeví klíč, který je hlavní podmínkou pro výhru
     * Vrátí se odpovídající texové řetězce pokud podmínky jsou splněný nebo není
     */
    @Override
    public String execute(String... parameters)
    {
        if (parameters.length == 0) {
            return "Tomu nerozumím, musíš mi říct, co mám dělat";
            
        }
        
        if (parameters.length==1) {
            return "Tomu nerozumím, musíš říct komu přečíst.";
            
        }
        if(parameters.length > 2) {
            return "Tomu nerozumím, musíš říct co a komu přečíst.";
        }
        
        String itemName = parameters[0];
        String personName = parameters[1];
        
        Place currentPlace = game.getWorld().getCurrentPlace();
        
        if (!game.getBag().contains(itemName)) {
            return "Předmět '" + itemName  +"' nemáš v batohu.";
        }
        if (!currentPlace.containsItem(personName)) {
            return "Persona '" + personName  +"' tady není.";
        }
        
       
        Item person = currentPlace.getItem(personName);
        if(!person.isPerson()) {
            return "Item '" + personName + "' není persona.";
            
        } 
        if(itemName.equals("kniha") && person.getName().equals("duch")) {
            game.getBag().removeItem(itemName);
            game.getWorld().getCurrentPlace().removeItem("duch");
            Item key = new Item("klíč","s jeho pomocí otevříš dveře a dostaneš se z domu");
            game.getBag().putItem(key);
            return "Spřátelil ses s duchem, gratuluju! Klíč k východu už máš v batohu.";
        }
        return "Tohle dělat nesmíš '" + itemName + "' není možné přečíst " + person.getName();
        
        
        
    }
}
