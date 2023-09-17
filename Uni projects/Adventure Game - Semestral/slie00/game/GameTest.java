/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package game;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code GameTest} slouží ke komplexnímu otestování
 * třídy {@link GameTest}.
 *
  * @author  Elizaveta Sliusareva
 * @version 10.01.2020
 */
public class GameTest
{
    
    private Game game1;

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        game1 = new Game();
    }


    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }



//\TT== TESTS PROPER ===========================================================

    /***************************************************************************
     * Test prohry, proherní scénář.
     */
    @Test
    public void testWithoutBook()
    {
       game1.executeAction("jdi Kuchyň");
       game1.executeAction("seber čokoláda");
       game1.executeAction("sněz čokoláda");
       game1.executeAction("jdi Sála");
        game1.executeAction("jdi Biblioteka");
        game1.executeAction("jdi Podkroví");
        game1.executeAction("jdi Biblioteka");
        assertEquals(true, game1.isGameOver());
    }
    
    @Test
    public void testWithBook()
    {
       game1.executeAction("jdi Kuchyň");
       game1.executeAction("seber čokoláda");
       game1.executeAction("jdi Koupelna");
       game1.executeAction("seber baterka");
       game1.executeAction("jdi Sála");
        game1.executeAction("jdi Biblioteka");
        game1.executeAction("rozsvěti");
        game1.executeAction("seber kniha");
        game1.executeAction("sněz čokoláda");
        game1.executeAction("jdi Podkroví");
        game1.executeAction("přečti kniha duch");
        game1.executeAction("jdi Biblioteka");
        assertEquals(true, game1.isGameOver());
    }
    
    /***************************************************************************
     * Test metody isStrong
     */
    @Test
    public void isStrong()
    {
       game1.executeAction("jdi Kuchyň");
       game1.executeAction("seber čokoláda");
       game1.executeAction("jdi Sála");
       game1.executeAction("jdi Biblioteka");
       assertEquals(false, game1.isStrong());
       game1.executeAction("sněz čokoláda");
       assertEquals(true, game1.isStrong());
       game1.executeAction("jdi Podkroví");
       
} 
}