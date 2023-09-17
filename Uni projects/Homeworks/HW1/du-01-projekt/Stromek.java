/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */


/*******************************************************************************
 * Instance třídy Stromek představují obrázek vánočního stromku.
 *
 * @author    Elizaveta Sliusareva
 * @version   2020-10-26
 */
public class Stromek
{
    // Základní části stromku - tři patra větví a kmen.
    private Ctverec kmen;
    private Trojuhelnik spodek;
    private Trojuhelnik prostredek;
    private Trojuhelnik spicka;

    // Zbývající drobnosti - vánoční ozdoby.
    // Použití těchto atributů je nepovinné.
    private Kruh ozdoba1;
    private Kruh ozdoba2;
    private Kruh ozdoba3;
    private Kruh ozdoba4;

    /**
     * Konstruktor pro vytvoření objektu s obrázkem vánočního stromku.
     */
    public Stromek()
    {
        // Objekt po vytvoření ihned nakreslíme.
        kresli();
    }

    /**
     * Metoda nakreslí vánoční stromek na plátno.
     */
    public void kresli()
    {
        // V této metodě je potřeba vytvořit instance geometrických tvarů,
        // uložit je do datových atributů a nastavit jim vhodné vlastnosti
        // (velikost, pozici, barvu).
        
       
        spicka = new Trojuhelnik();
        spicka.posunVertikalne(70);
        spicka.posunHorizontalne(60);
        spicka.zmenBarvu("zelena");
        spicka.zmenVelikost(30,60);
        
        prostredek = new Trojuhelnik();
        prostredek.posunVertikalne(85);
        prostredek.posunHorizontalne(60);
        prostredek.zmenBarvu("zelena");
        prostredek.zmenVelikost(45,75);
        
        spodek = new Trojuhelnik();
        spodek.posunVertikalne(100);
        spodek.posunHorizontalne(60);
        spodek.zmenBarvu("zelena");
        spodek.zmenVelikost(60,100);
        
        kmen = new Ctverec();
        kmen.zmenBarvu("hneda");
        kmen.posunVertikalne(125);
        kmen.posunHorizontalne(40);
        kmen.zmenVelikost(20);
        
        ozdoba1 = new Kruh();
        ozdoba1.zmenBarvu("cervena");
        ozdoba1.zmenVelikost(10);
        ozdoba1.posunVertikalne(95);
        ozdoba1.posunHorizontalne(100);
        
        
        ozdoba2 = new Kruh();
        ozdoba2.zmenBarvu("oranzova");
        ozdoba2.zmenVelikost(10);
        ozdoba2.posunVertikalne(85);
        ozdoba2.posunHorizontalne(70);
        
        ozdoba3 = new Kruh();
        ozdoba3.zmenBarvu("fialova");
        ozdoba3.zmenVelikost(10);
        ozdoba3.posunVertikalne(65);
        ozdoba3.posunHorizontalne(94);
        
        ozdoba4 = new Kruh();
        ozdoba4.zmenBarvu("zluta");
        ozdoba4.zmenVelikost(9);
        ozdoba4.posunVertikalne(42);
        ozdoba4.posunHorizontalne(82);
        
        
             
    }

}
