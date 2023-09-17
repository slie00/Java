package game;

import java.util.List;
import java.util.ArrayList;

/**
 * Třída představující invěntář hlavní postavy. Datový atribut
 * {@link #capacity} ukázuje, že maximální počet věcí v batohu nemusí přesahovat 3.
 * Pomocí batohu hráč uchovává věci, které pak může používat v jinéch prostorech 
 * a nejen v těch, ve kterých věc dostal.
 *
 * @author Elizaveta Sliusareva
 * @version ZS-2020, 2021-01-10
 */

public class Bag
{
    List<Item> items = new ArrayList<>();
    int capacity = 3;
    /**
     * Vrátí kolekci objektů nacházejících se v daném kontejneru.
     *
     * @return kolekce objektů nacházejících se v daném kontejneru
     */
    public synchronized List<Item> getItems()
    {
       
        return items;
    }
    
    /**
     * Pomocí teto metody hráč může vložit věc do batohu
     */
    public void putItem(Item item) {
        items.add(item);
    }
    
    /**
     * Metoda odstraní věc z batohu (a nevrátí ji do pokoje) pokud ji hráč použil 
     * podle určeného účelu
     * @return Item vratí věc
     */
    public Item removeItem(String itemName) {
        Item item = null;
        for(Item tmpItem : items) {
            if(tmpItem.getName().equals(itemName)) {
                item = tmpItem;
                items.remove(tmpItem);
                return item;
            }
        }
        return item;
    }
    
    /**
     * Metoda kontroluje, zda batoh obsahuje určitou věc, kterou hráč chce použít
     * nebo ne
     * @return boolean true, jestli věc je v batohu
     */
    public boolean contains(String name) {
        for(Item tmpItem : items) {
            if(tmpItem.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metoda kontroluje zda počet věcí v batohu nepřesahuje nastavenou kapacitu 
     * (max počet věcí je 3)
     * @return boolean true, pokud přesahuje kapacitu
     */
    public boolean isFull() {
        return capacity == items.size();
    }
}
