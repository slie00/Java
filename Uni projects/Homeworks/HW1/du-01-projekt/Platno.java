import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Trida Platno - trida umoznuje jednoduche kresleni grafickych objektu na platno.
 * Je to upravena verze obecne tridy java.awt.Canvas urcena pro demonstrace trid
 * v Bluej. Hlavni upravou je, ze trida Platno poskytuje pouze jednu instanci tridy
 * Canvas - je pouzit navrhovy vzor singleton.
 * 
 * @author: Bruce Quig
 * @author: Michael Kolling (mik)
 * @author: Lubos Pavlicek (lp)
 * @author: Jarmila Pavlickova (jp)
 * @author: Jan Riha (jr)
 *
 * @version: 1.6
 * @version: 1.6cz
 *
 * changes:
 *   19.11.99   mik     added proper buffering and screen update
 *   19.5.2000  mik     modified for "shapes" example (made singleton)
 *   14.7.2000  mik     modified to accept String colours
 *   29.7.2004  lp      translate to czech
 *   22.9.2008  jp      přidáno menu obsahující volbu "smaž plátno"
 *   19.9.2016  jr      doplneno zobrazeni pravitek na okraji platna
 */

public class Platno
{
    private static Platno platnoSingleton;

    protected static final int PRAVITKO_DELKA_ZNACEK = 300;
    protected static final int PRAVITKO_VZDALENOST_ZNACEK = 20;
    protected static final int PRAVITKO_POSUN_TEXTU_X = 3;
    protected static final int PRAVITKO_POSUN_TEXTU_Y = 10;
    protected static final float PRAVITKO_VELIKOST_PISMA = 9f;

    protected static final Color COLOR_BROWN = new Color(150, 75, 0);

    /**
     * Tovarni metoda pro ziskani instance tridy Platno - singleton.
     */
    public static Platno getPlatno()
    {
        if(platnoSingleton == null) {
            platnoSingleton = new Platno("Demonstrační plátno", 300, 300, 
                                         Color.WHITE, true);
        }
        platnoSingleton.setVisible(true);
        return platnoSingleton;
    }

    //  ----- datove atributy instance -----

    private JFrame frame;
    public  PlatnoPanel platno;
    private Graphics2D graphic;
    private Color barvaPozadi;
    private Image canvasImage;
    private JMenuBar listaMenu;
    private JMenuItem volbaVymaz;

    private boolean zobrazPravitko;

    /**
     * Vytvoreni Platna.
     * @param tittulek          titulek, ktery se objevi v okne s platnem
     * @param sirka             sirka platna
     * @param vyska             vyska platna
     * @param barvaPozadi       barva pozadi platna
     * @param zobrazPravitko    priznak, zda se ma na kraji platna zobrazit pravitko
     */
    private Platno(String titulek, int sirka, int vyska, Color barvaPozadi, boolean zobrazPravitko)
    {
        frame = new JFrame();
        platno = new PlatnoPanel();
        frame.setContentPane(platno);
        frame.setTitle(titulek);
        platno.setPreferredSize(new Dimension(sirka, vyska));
        this.barvaPozadi = barvaPozadi;
        listaMenu = new JMenuBar();
        volbaVymaz = new JMenuItem("Smaž plátno");
        listaMenu.add(volbaVymaz);
        volbaVymaz.addActionListener(new VymazPlatna());
        frame.setJMenuBar(listaMenu);
        frame.pack();

        this.zobrazPravitko = zobrazPravitko;
    }

    /**
     * Nastavi viditelnost platna a zobrazi platno na obrazovce. Tato metoda
     * muze byt tez pouzita pro presunuti okna do popredi.
     * 
     * @param visible  booleovska hodnota, ktera urcuje viditelnost platna
     *                   (true or false) 
     */
    public void setVisible(boolean visible)
    {
        if(graphic == null) {
            // pri prvnim zobrazeni platna se vyplni pozadi
            Dimension size = platno.getSize();
            canvasImage = platno.createImage(size.width, size.height);
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(barvaPozadi);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.BLACK);
        }
        frame.setVisible(visible);
    }

   /**
     * Poskytuje informaci o viditelnosti Platna.
     * @return  true, pokud je platno viditelne, jinak false
     */
    public boolean isVisible()
    {
        return frame.isVisible();
    }

    /**
     * Nakresli danny tvar na platno.
     * @param  tvar  tvar (instance rozhrani Shape), ktery se ma nakreslit na Platno
     */
    public void kresli(Shape tvar)
    {
        graphic.draw(tvar);

        if (zobrazPravitko) {
            nakresliPravitko();
        }

        platno.repaint();
    }
 
    /**
     * Vyplni vnitrek danneho tvaru aktualni barvou popredi
     * @param  tvar  tvar, ktery se ma vybarvit 
     */
    public void vybarvi(Shape tvar)
    {
        graphic.fill(tvar);

        if (zobrazPravitko) {
            nakresliPravitko();
        }

        platno.repaint();
    }

    /**
     * Vymaze zadany tvar na platne (vyplni se barvou pozadi).
     * @param  tvar  tvar, ktery se ma vymazat 
     */
    public void vymaz(Shape tvar)
    {
        Color original = graphic.getColor();
        graphic.setColor(barvaPozadi);
        graphic.fill(tvar);              // vymaz znamena obarveni na barvu pozadi
        graphic.setColor(original);
        platno.repaint();
    }

    /**
     * Erases a given shape's outline on the screen.
     * @param  shape  the shape object to be erased 
     */
    public void eraseOutline(Shape tvar)
    {
        Color original = graphic.getColor();
        graphic.setColor(barvaPozadi);
        graphic.draw(tvar);  // vymaz probehne prekreslenim barvou pozadi
        graphic.setColor(original);
        platno.repaint();
    }

    /**
     * Nakresli obrazek (Image) na platne.
     * @param  image   obrazek, ktery se ma vykreslit na platne 
     * @param  x       x pozice pro umisteni obrazku 
     * @param  y       y pozice pro umisteni obrazku 
     * @return         vraci true, pokud se obrazek cely nakreslil 
     */
    public boolean drawImage(Image image, int x, int y)
    {
        boolean result = graphic.drawImage(image, x, y, null);
        platno.repaint();
        return result;
    }

    /**
     * Nakresli/vypise textovy retezec na platno.
     * @param  text   text, ktery se ma nakreslit na platno 
     * @param  x      x pozice pro umisteni textu na platne 
     * @param  y      y pozice pro umisteni textu na platne
     */
    public void drawString(String text, int x, int y)
    {
        graphic.drawString(text, x, y);   
        platno.repaint();
    }

    /**
     * Vymaze textovy retezec napsany na platne (tj. znovu ho vypise s barvou pozadi).
     * Pozor, musi byt nastaven stejny font jako pri psani textu na platno.
     * @param  text     text, ktery se ma vymazat z platna 
     * @param  x        x pozice na platne, na ktere je umisten text 
     * @param  y        y pozice na platne, na ktere je umisten text
     */
    public void eraseString(String text, int x, int y)
    {
        Color original = graphic.getColor();
        graphic.setColor(barvaPozadi);
        graphic.drawString(text, x, y);   
        graphic.setColor(original);
        platno.repaint();
    }

    /**
     * Nakresli caru na platne.
     * @param  x1   x pozice zacatku cary 
     * @param  y1   y pozice zacatku cary 
     * @param  x2   x pozice konce cary 
     * @param  y2   y pozice konce cary 
     */
    public void drawLine(int x1, int y1, int x2, int y2)
    {
        graphic.drawLine(x1, y1, x2, y2);   
        platno.repaint();
    }

    /**
     * Nastavuje novou barvu popredi pro kresleni na platno. Pripustne hodnoty:
     * "cervena", "cerna", "modra", "zluta", "zelena", "fialova", "ruzova", "seda"
     * a "bila". Pri zadani jine hodnoty se pouzije "cerna".
     * 
     * @param  barvaText   nova barva popredi pro kresleni na Platno, zadava se jako retezec 
     */
    public void setBarvaPopredi(String barvaText)
    {
        if(barvaText.equals("cervena"))
            graphic.setColor(Color.RED);
        else if(barvaText.equals("cerna"))
            graphic.setColor(Color.BLACK);
        else if(barvaText.equals("modra"))
            graphic.setColor(Color.BLUE);
        else if(barvaText.equals("zluta"))
            graphic.setColor(Color.YELLOW);
        else if(barvaText.equals("zelena"))
            graphic.setColor(Color.GREEN);
        else if(barvaText.equals("fialova"))
            graphic.setColor(Color.MAGENTA);
        else if(barvaText.equals("ruzova"))
            graphic.setColor(Color.PINK);
        else if(barvaText.equals("seda"))
            graphic.setColor(Color.LIGHT_GRAY);
        else if(barvaText.equals("bila"))
            graphic.setColor(Color.WHITE);
        else if(barvaText.equals("hneda"))
            graphic.setColor(COLOR_BROWN);
        else if(barvaText.equals("oranzova"))
            graphic.setColor(Color.ORANGE);
        else
            graphic.setColor(Color.BLACK);
    }

    /**
     * Nastavi barvu popredi pro Platno. Pouzivaji se instance tridy java.awt.Color.
     * @param  novaBarva    nova barva popredi pro Platno 
     */
    public void setBarvaPopredi(Color novaBarva)
    {
        graphic.setColor(novaBarva);
    }

    /**
     * Vraci aktualni barvu popredi, kterou se kresli na Platno.
     * @return   barva popredi, kterou se aktualne kresli na platno 
     */
    public Color getBarvaPopredi()
    {
        return graphic.getColor();
    }

    /**
     * Nastavi novou barvu pozadi platna.
     * @param  novaBarva   nova barva pozadi pro Platno 
     */
    public void setBarvaPozadi(Color novaBarva)
    {
        barvaPozadi = novaBarva;   
        graphic.setBackground(novaBarva);
    }

    /**
     * Vrati aktualni barvu pozadi Platna
     * @return   barva pozadi Platna 
     */
    public Color getBarvaPozadi()
    {
        return barvaPozadi;
    }

    /**
     * Zmeni aktualni font, ktery se pouziva pro psani textu na platno
     * @param  novyFont   novy font, ktery se bude pouzit pro psani textu na platno
     */
    public void setFont(Font novyFont)
    {
        graphic.setFont(novyFont);
    }

    /**
     * Vraci aktualni font, ktery se pouziva pro psani textu na platno.
     * @return     font, ktery se aktualne pouziva
     **/
    public Font getFont()
    {
        return graphic.getFont();
    }

    /**
     * Nastavi velikost Platna (tzv. preferovanou velikost).
     * @param  sirka    nova sirka 
     * @param  vyska    nova vyska 
     */
    public void setSize(int sirka, int vyska)
    {
        platno.setPreferredSize(new Dimension(sirka, vyska));
        frame.pack();
    }

    /**
     * Vraci velikost platna.
     * @return     aktualni velikost platna
     */
    public Dimension getSize()
    {
        return platno.getSize();
    }

    /**
     * Cekani zadany pocet milisekund. Toto poskytuje jednoduduchy zpusob pro urceni
     *      zpozdeni, ktere lze vyuzit pri vytvareni animaci.
     * @param  milliseconds  počet milisekund 
     */
    public void wait(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        } 
        catch (Exception e)
        {
            // pripadna vyjimka se ignoruje
        }
    }

    /**
     * Nakresli pravitka na okraj platna.
     */
    private void nakresliPravitko() {
        Color color = graphic.getColor();
        Font font = graphic.getFont();

        graphic.setFont(graphic.getFont().deriveFont(PRAVITKO_VELIKOST_PISMA));

        Dimension rozmer = platno.getSize();

        for (int y = 0; y <= rozmer.height; y += PRAVITKO_VZDALENOST_ZNACEK)
        {
            graphic.setColor(Color.BLACK);
            graphic.drawString(String.valueOf(y), PRAVITKO_POSUN_TEXTU_X, y + PRAVITKO_POSUN_TEXTU_Y);
            graphic.setColor(Color.LIGHT_GRAY);

            for (int i = 0; i < PRAVITKO_DELKA_ZNACEK; i = i + 2)
            {
                graphic.drawLine(i, y, i, y);
            }
        }

        for (int x = 0; x <= rozmer.width; x += PRAVITKO_VZDALENOST_ZNACEK)
        {
            graphic.setColor(Color.BLACK);
            graphic.drawString(String.valueOf(x), x + PRAVITKO_POSUN_TEXTU_X, PRAVITKO_POSUN_TEXTU_Y);
            graphic.setColor(Color.LIGHT_GRAY);
            
            for (int i = 0; i < PRAVITKO_DELKA_ZNACEK; i = i + 2)
            {
                graphic.drawLine(x, i, x, i);
            }
        }

        graphic.setColor(color);
        graphic.setFont(font);
    }

    /************************************************************************
     * Nested class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class PlatnoPanel extends JPanel
    {
        private static final long serialVersionUID = -7839661456836069455L;

        public void paint(Graphics g)
        {
            g.drawImage(canvasImage, 0, 0, null);
        }
    }
    
     /************************************************************************
     * Vnitřní třída obsluhující volbu menu pro smazání plátna.
     */
    
    private class VymazPlatna implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Dimension rozmer = platno.getSize();
            graphic.setColor(barvaPozadi);
            graphic.fillRect(0, 0, rozmer.width, rozmer.height);
            platno.repaint();
            
        }
    }

}
