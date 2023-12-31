package game;

/**
 * Třída představující mapu prostorů herního světa. V datovém atributu
 * {@link #currentPlace} uchovává odkaz na aktuální prostor, ve kterém
 * se hráč právě nachází. Z aktuálního prostoru je možné se prostřednictvím
 * jeho sousedů dostat ke všem přístupným prostorům ve hře.
 *
 * @author Elizaveta Sliusareva
 * @version ZS-2020, 2021-01-10
 */
public class World
{
    public static final String VICTORY_PLACE_NAME = "Východ";
    private Place currentPlace;

    /**
     * Konstruktor třídy, vytvoří jednotlivé prostory a propojí je pomocí
     * východů.
     */
    public World()
    {
        Place hall = new Place("Sála", "Tady jsi se probudil(a). Vidíš dveře do kuchyně, biblioteky a koupelny. ");
        Place kitchen = new Place("Kuchyň", "Toto je kuchyň, tady najdeš něco k jídlu.");
        Place library = new Place("Biblioteka", "Zde máš najít knihu, ve které je napsana pohádka, pomocí které uklidníš zlého ducha a on ti dá klíč.");
        Place bathroom = new Place("Koupelna", "Tady najdeš baterku .");
        Place attic = new Place("Podkroví", "Zde uvidíš zlého ducha, který má rád pohádky");
        Place stairs = new Place("Schody", "Před tebou je rozcestí. Pokud půjedeš doprava, dostaneš se do sklepu, pokud doleva – najdeš východ. ");
        Place trap = new Place("Sklep", "Máš možnost poznat další obyvatele domu");
        Place finish = new Place("Východ", "Pomocí klíče, který ti dal duch, otevříš dveře ");
        

        hall.addNeighbor(library);
        hall.addNeighbor(bathroom);
        hall.addNeighbor(kitchen);
        
        
        kitchen.addNeighbor(hall);
        kitchen.addNeighbor(bathroom);
        
        bathroom.addNeighbor(kitchen);
        bathroom.addNeighbor(hall);
        
        library.addNeighbor(hall);
        library.addNeighbor(stairs);
        library.addNeighbor(attic);
        
        attic.addNeighbor(library);
        
        stairs.addNeighbor(library);
        stairs.addNeighbor(trap);
        stairs.addNeighbor(finish);
        
        trap.addNeighbor(stairs);
        
        finish.addNeighbor(stairs);
        System.out.println(finish);
        
        Item chokolate = new Item("čokoláda", "sněz ji a budeš mít víc síl.", true, true, false, true);
        Item lantern = new Item("baterka","pomocí ní rozsvítíš a najdeš knihu.", true, false, false, true);
        Item book = new Item("kniha","přečti ji duchu a on ti pomůže", true, false, false, false);
       
        
        Item ghost = new Item("duch","je to zlý duch, který rád poslouchá pohádky", false, false, true, true);
        
        attic.addItem(ghost);
        kitchen.addItem(chokolate);
        bathroom.addItem(lantern);
        library.addItem(book);
        currentPlace = hall;
    }

    /**
     * Metoda vrací odkaz na aktuální prostor, ve kterém se hráč právě
     * nachází.
     *
     * @return aktuální prostor
     */
    public Place getCurrentPlace()
    {
        return currentPlace;
    }

    /**
     * Metoda nastaví aktuální prostor, používá ji příkaz {@link ActionMove}
     * při přechodu mezi prostory.
     *
     * @param place prostor, který bude nastaven jako aktuální
     */
    public void setCurrentPlace(Place place)
    {
        currentPlace = place;
    }
}
