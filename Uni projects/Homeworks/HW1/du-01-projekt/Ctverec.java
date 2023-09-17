import java.awt.*;

/**
 * Ctverec, ktery se nakresli sam na Platno a se kterym je mozne manipulovat.
 * 
 * @author  Michael Kolling
 * @author  Lubos Pavlicek
 * @version 1.0  (15 July 2000)
 * @version 1.0cz (30 July 2004)
 */

public class Ctverec
{
    private int velikost;
    private int xPozice;
    private int yPozice;
    private String barva;

    /**
     * Vytvoreni noveho ctverce na defaltni pozici (60, 50) a s defaltni barvou (cervena).
     */
    public Ctverec()
    {
        velikost = 30;
        xPozice = 60;
        yPozice = 50;
        barva = "cervena";
        kresli();
    }

    /**
     * Posune ctverec o nekolik bodu (20) doprava.
     */
    public void posunVpravo()
    {
        posunHorizontalne(20);
    }

    /**
     * Posune ctverec o nekolik bodu (20) doleva.
     */
    public void posunVlevo()
    {
        posunHorizontalne(-20);
    }

    /**
     * Posune ctverec o nekolik bodu (20) nahoru.
     */
    public void posunNahoru()
    {
        posunVertikalne(-20);
    }

    /**
     * Posune ctverec o nekolik bodu (20) dolu.
     */
    public void posunDolu()
    {
        posunVertikalne(20);
    }

    /**
     * Posune ctverec horizontalne o zadanou vzdalenost. Kladna hodnota znamena posun vpravo,
     * zaporna hodnota posun vlevo.
     */
    public void posunHorizontalne(int vzdalenost)
    {
        vymaz();
        xPozice += vzdalenost;
        kresli();
    }

    /**
     * Posune ctverec vertikalne o zadanou vzdalenost. Kladna hodnota znamena posun dolu,
     * zaporna hodnota posun nahoru.
     */
    public void posunVertikalne(int vzdalenost)
    {
        vymaz();
        yPozice += vzdalenost;
        kresli();
    }

    /**
     * Pomalu (animovane) posune ctverec horizontalne o zadanou vzdalenost. Kladna hodnota znamena
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
     * Pomalu (animovane) posune ctverec vertikalne o zadanou vzdalenost. Kladna hodnota znamena
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
     * Zmeni velikost ctverce na novou velikost v bodech. Velikost musi byt >= 0.
     */
    public void zmenVelikost(int novaVelikost)
    {
        vymaz();
        velikost = novaVelikost;
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
     * Nakresli ctverec s aktualnimi parametry na platne.
     */
    private void kresli()
    {
        Platno platno = Platno.getPlatno();
        platno.setBarvaPopredi(barva);
        platno.vybarvi(new Rectangle(xPozice, yPozice, velikost, velikost));
        platno.wait(10);
    }

    /*
     * Vymaze ctverec na platne.
     */
    private void vymaz()
    {
        Platno platno = Platno.getPlatno();
        platno.vymaz(new Rectangle(xPozice, yPozice, velikost, velikost));
    }
}
