/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package game;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code ItemTest} slouží ke komplexnímu otestování
 * třídy {@link ItemTest}.
 *
 * @author  Elizaveta Sliusareva
 * @version 10.01.2020
 */
public class ItemTest
{
//\CC== CONSTANT CLASS (STATIC) ATTRIBUTES (FIELDS) ============================
//\CV== VARIABLE CLASS (STATIC) ATTRIBUTES (FIELDS) ============================



//##############################################################################
//\CI== STATIC INITIALIZER (CLASS CONSTRUCTOR) =================================
//\CG== CLASS (STATIC) GETTERS AND SETTERS =====================================
//\CM== OTHER NON-PRIVATE CLASS (STATIC) METHODS ===============================
//\CP== PRIVATE AND AUXILIARY CLASS (STATIC) METHODS ===========================



//##############################################################################
//\IC== CONSTANT INSTANCE ATTRIBUTES (FIELDS) ==================================
//\IV== VARIABLE INSTANCE ATTRIBUTES (FIELDS) ==================================



//##############################################################################
//\II== CONSTRUCTORS AND FACTORY METHODS =======================================
//----- Test class manages with empty default constructor ----------------------



//\IA== ABSTRACT METHODS =======================================================
//\IG== INSTANCE GETTERS AND SETTERS ===========================================
//\IM== OTHER NON-PRIVATE INSTANCE METHODS =====================================
//\IP== PRIVATE AND AUXILIARY INSTANCE METHODS =================================



//##############################################################################
//\NT== NESTED DATA TYPES ======================================================



//##########################################################################
//\TC== PREPARATION AND CLEAN UP OF THE CLASS FIXTURE ==========================
//\TI== PREPARATION AND CLEAN UP OF THE TEST FIXTURE ==========================

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
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
     * Test metody přenositelnosti
     */
    @Test
    public void isMoveable()
    {
        Item chokolate = new Item("čokoláda", "sněz ji a budeš mít víc síl.", true, true, false, true);
        Item lantern = new Item("baterka","pomocí ní rozsvítíš a najdeš knihu.", true, false, false, true);
        Item book = new Item("kniha","přečti ji duchu a on ti pomůže", true, false, false, false);
        Item ghost = new Item("duch","je to zlý duch, který rád poslouchá pohádky", false, false, true, true);
        assertEquals(true, chokolate.isMoveable());
        assertEquals(true, lantern.isMoveable());
        assertEquals(true, book.isMoveable());
        assertEquals(false, ghost.isMoveable());
        
    }
    
    /***************************************************************************
     * Test metody isEatable
     */
    @Test
    public void isEatable()
    {
        Item chokolate = new Item("čokoláda", "sněz ji a budeš mít víc síl.", true, true, false, true);
        Item lantern = new Item("baterka","pomocí ní rozsvítíš a najdeš knihu.", true, false, false, true);
        Item book = new Item("kniha","přečti ji duchu a on ti pomůže", true, false, false, false);
        Item ghost = new Item("duch","je to zlý duch, který rád poslouchá pohádky", false, false, true, true);
        assertEquals(true, chokolate.isEatable());
        assertEquals(false, lantern.isEatable());
        assertEquals(false, book.isEatable());
        assertEquals(false, ghost.isEatable());
        
    }
    
    /***************************************************************************
     * Test metody isPerson
     */
    @Test
    public void isPerson()
    {
        Item chokolate = new Item("čokoláda", "sněz ji a budeš mít víc síl.", true, true, false, true);
        Item lantern = new Item("baterka","pomocí ní rozsvítíš a najdeš knihu.", true, false, false, true);
        Item book = new Item("kniha","přečti ji duchu a on ti pomůže", true, false, false, false);
        Item ghost = new Item("duch","je to zlý duch, který rád poslouchá pohádky", false, false, true, true);
        assertEquals(false, chokolate.isPerson());
        assertEquals(false, lantern.isPerson());
        assertEquals(false, book.isPerson());
        assertEquals(true, ghost.isPerson());
        
    }
    
    /***************************************************************************
     * Test metody isVisible
     */
    @Test
    public void isVisible()
    {
        Item chokolate = new Item("čokoláda", "sněz ji a budeš mít víc síl.", true, true, false, true);
        Item lantern = new Item("baterka","pomocí ní rozsvítíš a najdeš knihu.", true, false, false, true);
        Item book = new Item("kniha","přečti ji duchu a on ti pomůže", true, false, false, false);
        Item ghost = new Item("duch","je to zlý duch, který rád poslouchá pohádky", false, false, true, true);
        assertEquals(true, chokolate.isVisible());
        assertEquals(true, lantern.isVisible());
        assertEquals(false, book.isVisible());
        assertEquals(true, ghost.isVisible());
        
    }

}
