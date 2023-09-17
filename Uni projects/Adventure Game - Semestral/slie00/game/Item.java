package game;
/**
 * Třída představující seznam objektů ve hře.
 * Metody v teto třídě přidávají nové objekty, provadějí kontroly, zda je objekt přenositelný,jedlý, živý nebo ne. Poskytují podmínky pro viditelnost objektu hráčem.
 * 
 * @author Sliusareva Elizaveta
 * @version ZS-2020, 2021-01-10
 */
public class Item implements INamed
{
   
    private String name;
    private String description;
    private boolean moveable;
    private boolean eatable;
    private boolean person;
    private boolean visible;
    
    /**
     * Metoda kontroluje, zda je objekt jedlý
     * 
     * return true  pokud je jedlý, jinak false 
     */
    public boolean isEatable() {
        return eatable;
    }
    
    /**
     * Metoda kontroluje, zda je objekt viditelný
     * 
     * return true  pokud je viditelný, jinak false
     */
    public boolean isVisible() {
        return visible;
    }
    
    /**
     * Metoda kontroluje, zda je objekt živý
     * 
     * return true  pokud je živý, jinak false 
     */
    public boolean isPerson() {
        return person;
    }
    
    /**
     * Metoda mění stav neviditelného objektu na viditelný
     */
    public void setVisible() {
        visible = true;
    }
    
    /**
     * Defaultní konstruktor
     */
    public Item(String name, String description) {
        this(name, description, true, false, false, true);
    }
    
    /**
     * Konstruktor s určítými parametry
     */
    public Item(String name, String description, boolean moveable, boolean eatable, boolean person, boolean visible) {
        this.name = name;
        this.description = description;
        this.moveable = moveable;
        this.eatable = eatable;
        this.person = person;
        this.visible = visible;
    }
    
   
    @Override
    public String toString() {
        return name;
    }
    
    /**
     * Metodá vrátí název objektu
     * @return název věci 
     */
    @Override
    public String getName()
    {
       
        return name;
    }
    
    /**
     * Metodá vrátí popis objektu
     * @return popis věci
     */
    public String getDescription()
    {
       
        return description;
    }
    
    /**
     * Metodá mění popis objektu
     */
    public void setDescription(String description)
    {
       
        this.description = description;
    }
    
    /**
     * Metoda kontroluje, zda je objekt přenositelný
     * 
     * return true, pokud je přenositelný, jinak false
     */
    public boolean isMoveable()
    {
       
        return moveable;
    }
    
}
