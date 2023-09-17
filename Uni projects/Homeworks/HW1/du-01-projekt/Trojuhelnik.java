import java.awt.*;

/**
 * Rovnoramenný trojuhelnik, ktery se sam nakresli na planto a se kterym je mozne manipulovat.
 * 
 * @author  Michael Kolling
 * @author  Lubos Pavlicek
 * @version 1.0  (15 July 2000)
 * @version 1.0cz (30 July 2004)
 */

public class Trojuhelnik
{
    private int vyska;
    private int sirka;
    private int xPozice;
    private int yPozice;
    private String barva;

    /**
     * Vytvoreni noveho trojuhelniku na defaultni pozici (50,15) s defaultni barvou (zelena).
     */
    public Trojuhelnik()
    {
        vyska = 30;
        sirka = 40;
        xPozice = 50;
        yPozice = 15;
        barva = "zelena";
        kresli();
    }

    /**
     * Posune trojuhelnik o nekolik bodu (o 20) doprava.
     */
    public void posunVpravo()
    {
        posunHorizontalne(20);
    }

    /**
     * Posune trojuhelnik o nekolik bodu (o 20) doleva.
     */
    public void posunVlevo()
    {
        posunHorizontalne(-20);
    }

    /**
     * Posune trojuhelnik o nekolik bodu (o 20) nahoru.
     */
    public void posunNahoru()
    {
        posunVertikalne(-20);
    }

    /**
     * Posune trojuhelnik o nekolik bodu (o 20) dolu.
     */
    public void posunDolu()
    {
        posunVertikalne(20);
    }

    /**
     * Posune trojuhelnik horizontalne o zadanou vzdalenost. Kladna hodnota znamena posun vpravo,
     * zaporna hodnota posun vlevo.
     */
    public void posunHorizontalne(int vzdalenost)
    {
        vymaz();
        xPozice += vzdalenost;
        kresli();
    }

    /**
     * Posune trojuhelnik vertikalne o zadanou vzdalenost. Kladna hodnota znamena posun dolu,
     * zaporna hodnota posun nahoru.
     */
    public void posunVertikalne(int vzdalenost)
    {
        vymaz();
        yPozice += vzdalenost;
        kresli();
    }

    /**
     * Pomalu (animovane) posune trojuhelnik horizontalne o zadanou vzdalenost. Kladna hodnota znamena
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
     * Pomalu (animovane) posune trojuhelnik vertikalne o zadanou vzdalenost. Kladna hodnota znamena
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
     * Zmeni velikost trojuhelniku na novou velikost. Hodnoty musi byt >= 0.
     * @param novaVyska  nova vyska rovnoramenneho trojuhelnika
     * @param novaSirka  nova sirka rovnoramenneho trojuhelnika
     */
    public void zmenVelikost(int novaVyska, int novaSirka)
    {
        vymaz();
        vyska = novaVyska;
        sirka = novaSirka;
        kresli();
    }

    /**
     * Zmeni barvu kruhu. Pripustne hodnoty jsou:
     * "cervena", "cerna", "modra", "zluta", "zelena", "fialova", "ruzova", "seda",
     * "bila", "oranzova" a "hneda". Pri zadani jine hodnoty se pouzije "cerna".
     */
    public void zmenBarvu(String newColor)
    {
        barva = newColor;
        kresli();
    }

    /*
     * Nakresli trojuhelnik s aktualnimi parametry na platne.
     */
    private void kresli()
    {
        Platno platno = Platno.getPlatno();
        platno.setBarvaPopredi(barva);
        int[] xpoints = { xPozice, xPozice + (sirka/2), xPozice - (sirka/2) };
        int[] ypoints = { yPozice, yPozice + vyska, yPozice + vyska };
        platno.vybarvi(new Polygon(xpoints, ypoints, 3));
        platno.wait(10);
    }

    /*
     * Vymaze trojuhelnik na platne.
     */
    private void vymaz()
    {
        Platno platno = Platno.getPlatno();
        int[] xpoints = { xPozice, xPozice + (sirka/2), xPozice - (sirka/2) };
        int[] ypoints = { yPozice, yPozice + vyska, yPozice + vyska };
        platno.vymaz(new Polygon(xpoints, ypoints, 3));
    }
}
