import java.awt.geom.*;

/**
 * Kruh, ktery se sam nakresli na Platno a se kterym je mozne manipulovat.
 * 
 * @author  Michael Kolling
 * @author  Lubos Pavlicek
 * @version 1.0  (15 July 2000)
 * @version 1.0cz (30 July 2004)
 */

public class Kruh
{
    private int prumer;
    private int xPozice;
    private int yPozice;
    private String barva;

    /*
     * Vytvoreni noveho kruhu na defaultni pozici (20,60) s defaultni barvou (modra)
     */
    public Kruh()
    {
        prumer = 30;
        xPozice = 20;
        yPozice = 60;
        barva = "modra";
        kresli();
    }

    /**
     * Posune kruh o nekolik bodu (o 20) doprava.
     */
    public void posunVpravo()
    {
        posunHorizontalne(20);
    }

    /**
     * Posune kruh o nekolik bodu (o 20) doleva.
     */
    public void posunVlevo()
    {
        posunHorizontalne(-20);
    }

    /**
     * Posune kruh o nekolik bodu (o 20) nahoru.
     */
    public void posunNahoru()
    {
        posunVertikalne(-20);
    }

    /**
     * Posune kruh o nekolik bodu (o 20) dolu.
     */
    public void posunDolu()
    {
        posunVertikalne(20);
    }

    /**
     * Posune kruh horizontalne o zadanou vzdalenost. Kladna hodnota znamena posun vpravo,
     * zaporna hodnota posun vlevo.
     */
    public void posunHorizontalne(int vzdalenost)
    {
        vymaz();
        xPozice += vzdalenost;
        kresli();
    }

    /**
     * Posune kruh vertikalne o zadanou vzdalenost. Kladna hodnota znamena posun dolu,
     * zaporna hodnota posun nahoru.
     */
    public void posunVertikalne(int vzdalenost)
    {
        vymaz();
        yPozice += vzdalenost;
        kresli();
    }

    /**
     * Pomalu (animovane) posune kruh horizontalne o zadanou vzdalenost. Kladna hodnota znamena
     * posun vpravo, zaporna hodnota posun vlevo.
     */
    public void pomaluPosunHorizontalne(int vzdalenost)
    {
        int delta;

        if(vzdalenost < 0) 
        {
            delta = -1;
            vzdalenost = -vzdalenost;
        }
        else 
        {
            delta = 1;
        }

        for(int i = 0; i < vzdalenost; i++)
        {
            vymaz();
            xPozice += delta;
            kresli();
        }
    }

    /**
     * Pomalu (animovane) posune kruh vertikalne o zadanou vzdalenost. Kladna hodnota znamena
     * posun dolu, zaporna hodnota posun nahoru.
     */
    public void pomaluPosunVertikalne(int vzdalenost)
    {
        int delta;

        if(vzdalenost < 0) 
        {
            delta = -1;
            vzdalenost = -vzdalenost;
        }
        else 
        {
            delta = 1;
        }

        for(int i = 0; i < vzdalenost; i++)
        {
            vymaz();
            yPozice += delta;
            kresli();
        }
    }

    /**
     * Zmeni velikost kruhu na novy prumer zadany v poctech bodu. Velikost musi byt >= 0.
     * @param novyPrumer  novy prumer kruhu v poctech bodu
     */
    public void zmenVelikost(int novyPrumer)
    {
        vymaz();
        prumer = novyPrumer;
        kresli();
    }

    /**
     * Zmeni barvu kruhu. Pripustne hodnoty jsou:
     * "cervena", "cerna", "modra", "zluta", "zelena", "fialova", "ruzova", "seda",
     * "bila", "oranzova" a "hneda". Pri zadani jine hodnoty se pouzije "cerna".
     */
    public void zmenBarvu(String novaBarva)
    {
        barva = novaBarva;
        kresli();
    }

    /*
     * Nakresli kruh s aktualnimi parametry na platne.
     */
    private void kresli()
    {
        Platno platno = Platno.getPlatno();
        platno.setBarvaPopredi(barva);
        platno.vybarvi(new Ellipse2D.Double(xPozice, yPozice, 
                                         prumer, prumer));
        platno.wait(10);
    }

    /*
     * Vymaze kruh na platne.
     */
    private void vymaz()
    {
        Platno platno = Platno.getPlatno();
        platno.vymaz(new Ellipse2D.Double(xPozice, yPozice, 
                                         prumer, prumer));
    }
}
