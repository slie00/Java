/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */


/*******************************************************************************
 * Instance třídy Snehulak představují obrázek sněhuláka.
 *
 * @author    jméno autora
 * @version   yyyy-mm-dd
 */
public class Snehulak
{
    // Základní části sněhuláka - tři sněhové koule a hrnec na hlavě.
    private Kruh spodek;
    private Kruh prostredek;
    private Kruh hlava;
    private Ctverec hrnec;

    // Zbývající drobnosti - ucho hrnce a knoflíky.
    // Použití těchto atributů je nepovinné.
    private Kruh ucho;
    private Ctverec leveOko;
    private Ctverec praveOko;
    private Ctverec nos;
    private Ctverec spodniKnoflik;
    private Ctverec prostredniKnoflik;
    private Ctverec horniKnoflik;

    /**
     * Konstruktor pro vytvoření objektu s obrázkem sněhuláka.
     */
    public Snehulak()
    {
        // Objekt po vytvoření ihned nakreslíme.
        kresli();
    }

    /**
     * Metoda nakreslí sněhuláka na plátno.
     */
    public void kresli()
    {
        // V této metodě je potřeba vytvořit instance geometrických tvarů,
        // uložit je do datových atributů a nastavit jim vhodné vlastnosti
        // (velikost, pozici, barvu).

        //# ZDE JE TŘEBA DOPLNIT KÓD
    }

}
