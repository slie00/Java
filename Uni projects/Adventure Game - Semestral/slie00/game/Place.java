package game;

import java.util.*;

/**
 * Třída představující mapu místností herního světa. 
 * Obsahuje metody, které vyhodnocují jednotlivé příkazy zadané uživatelem.
 * 
 * @author Sliusareva Elizaveta
 * @version ZS-2020, 2021-01-10
 */

public class Place implements INamed, Comparable<Place>
{
    private String name;
    private String description;
    private Set<Place> neighbors;
    private Map<String, Item> items;
    
    /**
     * Konstruktor třídy, vytvoří prostor se zadaným názvem a popisem.
     *
     * @param name název prostoru <i>(jednoznačný identifikátor, musí se jednat o text bez mezer)</i>
     * @param description podrobnější popis prostoru
     */
    public Place(String name, String description)
    {
        this.name = name;
        this.description = description;

        neighbors = new TreeSet<>();
        
        items = new HashMap<>();
    }
    
    /**
     * Metoda vrací název prostoru, který byl zadán při vytváření instance jako
     * parametr konstruktoru. Jedná se o jednoznačný identifikátor prostoru
     * <i>(ve hře nemůže existovat více prostorů se stejným názvem)</i>. Aby
     * hra správně fungovala, název prostoru nesmí obsahovat mezery, v případě
     * potřeby můžete více slov oddělit pomlčkami, použít camel-case apod.
     *
     * @return název prostoru
     */
    @Override
    public String getName()
    {
        return name;
    }
    
    /**
     * Metoda vrací popis prostoru.
     *
     * @return popis prostoru
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Metoda vrací kompletní informace o prostoru. Výsledek volání obsahuje:
     * <ul>
     *     <li>název prostoru</li>
     *     <li>popis prostoru</li>
     *     <li>seznam sousedních prostorů, do kterých lze odejít</li>
     *     <li>seznam předmětů v prostoru, pokud jsou hned viditelné</li>
     * </ul>
     *
     * @return kompletní informace o prostoru
     */
    public String getFullDescription()
    {
        String neighborNames = "Východy:";
        for (Place neighbor : neighbors) {
            neighborNames += " " + neighbor.getName();
        }
       
        String itemNames = "Předměty:";
        for (String itemName : items.keySet()) {
            if(items.get(itemName).isVisible()) {
                itemNames += " " + itemName;
            }
        }
        

        return "Jsi v prostoru '" + name + "'.\n"
                + description + "\n\n"
                + neighborNames+ "\n"
                + itemNames;
    }
    
    /**
     * Metoda přidá další východ z tohoto prostoru do prostoru předaném v parametru.
     * <p>
     * Vzhledem k tomu, že pro uložení sousedních prostorů se používá {@linkplain Set},
     * může být přidán pouze jeden východ do každého prostoru <i>(tzn. nelze mít dvoje
     * 'dveře' do stejného sousedního prostoru)</i>. Druhé volání metody se stejným
     * prostorem proto nebude mít žádný efekt.
     * <p>
     * Lze zadat též cestu do sebe sama.
     *
     * @param place prostor, do kterého bude vytvořen východ z aktuálního prostoru
     */
    public void addNeighbor(Place place)
    {
        neighbors.add(place);
    }
    
    /**
     * Metoda mění stav neviditelného objektu na viditelný 
     */
    public void setItemsVisible() {
        for(Item item : items.values()) {
            item.setVisible();
        }
    }
    
    /**
     * Vrátí kolekci všech sousedních prostorů.
     *
     * @return kolekce sousedních prostorů
     */
    public Collection<Place> getNeighbors()
    {
        return new HashSet<>(neighbors);
    }
    
    /**
     * Metoda přidá předmět <i>(objekt třídy {@link Item})</i> do prostoru.
     *
     * @param item předmět, který bude do prostoru přidán 
     */
    public void addItem( Item item) {
        items.put(item.getName(), item);
    }
    
    /**
     * Metoda zkontroluje, zda prostor obsahuje předmět s daným názvem.
     *
     * @param itemName název předmětu
     * @return {@code true}, pokud prostor obsahuje předmět s daným názvem; jinak {@code false}
     */
    public boolean containsItem(String itemName) {
        return items.containsKey(itemName);
    }
    
    /**
     * Metoda vyhledá v prostoru předmět s daným názvem a vrátí na něj odkaz.
     *
     * @param itemName název předmětu
     * @return předmět s daným názvem; {@code null}, pokud v prostoru není
     */
    public Item getItem(String itemName) {
        return items.get(itemName);
    }
    
    /**
     * Vrátí kolekci všech předmětů v prostoru.
     *
     * @return kolekce předmětů v prostoru
     */
    public Collection<Item> getItems()
    {
        return items.values();
    }
    
    /**
      * Metoda vyhledá v prostoru předmět s daným názvem, odstraní ho z prostoru a vrátí na něj odkaz.
     *
     * @param itemName název předmětu
     * @return odstraněný předmět; {@code null}, pokud předmět v prostoru není
     */
    public Item removeItem(String itemName) {
        return items.remove(itemName);
    }

    /**
     * Metoda porovnává dva prostory <i>(objekty)</i>. Prostory jsou shodné,
     * pokud mají stejný název <i>(atribut {@link #name})</i>. Tato metoda
     * je důležitá pro správné fungování seznamu východů do sousedních
     * lokací.
     * <p>
     * Podrobnější popis metody najdete v dokumentaci třídy {@linkplain Object}.
     *
     * @param o objekt, který bude porovnán s aktuálním
     * @return {@code true}, pokud mají oba prostory stejný název; jinak {@code false}
     *
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(final Object object)
    {
        if (object == this) {
            return true;
        }

        if (object == null) {
            return false;
        }

        if (object instanceof Place) {
            Place place = (Place) object;

            return name.equals(place.getName());
        }

        return false;
    }
    
    /**
     * Metoda vrací číselný identifikátor instance, který se používá
     * pro optimalizaci ukládání v dynamických datových strukturách
     * <i>(např.&nbsp;{@linkplain HashSet})</i>. Při překrytí metody
     * {@link #equals(Object) equals} je vždy nutné překrýt i tuto
     * metodu.
     * <p>
     * Podrobnější popis pravidel pro implementaci metody najdete
     * v dokumentaci třídy {@linkplain Object}.
     *
     * @return číselný identifikátor instance
     *
     * @see Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return name.hashCode();
    }
    
    /**
     * Metoda porovná prostor s druhým prostorem předaným v parametru
     * abecedně dle jejich názvů a vrátí kladné číslo, nulu, nebo záporné
     * číslo v závislosti na tom, zda je název tohoto prostoru větší,
     * stejný, nebo menší než název druhého prostoru.
     * <p>
     * Metoda se používá pro řazení sousedních prostorů v atributu {@link #neighbors}.
     *
     * @param place prostor, jehož název bude porovnán s názvem tohoto prostoru
     * @return kladné číslo, nula, nebo záporné číslo v závislosti na porovnání názvů prostorů
     */
    @Override
    public int compareTo(Place place)
    {
        return name.compareTo(place.getName());
    }
}
