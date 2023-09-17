
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testovací třída {@code GameTest} slouží ke komplexnímu otestování
 * logiky hry ve třídě {@link Game}. V rámci testů se předpokládá, že
 * tajné číslo, které se hráč pokouší uhodnout, je <b>50</b>.
 *
 * @author Jan Říha
 * @version 2020-11-16
 * 
 * *@red Elizaveta Sliusareva
 * @version 2020-10-26
 */
public class GameTest
{
    private Game gameLogic;

    /**
     * Tato metoda představuje inicializaci pro jednotlivé testy. Její kód se
     * spustí před každým testem a měl by zajistit přípravu tzv. testovacího
     * přípravku <i>(fixture)</i>, což je sada objektů, s nimiž budou testy
     * pracovat.
     */
    @Before
    public void setUp()
    {
        gameLogic = new Game(50);
    }

    /**
     * Tato metoda představuje test pro metodu {@link Game#guess(int) guess()}
     * třídy {@link Game}. Metoda se v rámci testu volá s různými parametry
     * a ověřuje se, že vrací očekávané výsledky.
     */
    @Test
    public void testGuess()
    {
        assertEquals(Game.GREATER, gameLogic.guess(25));
        assertEquals(Game.LOWER, gameLogic.guess(75));

        assertEquals(Game.GREATER, gameLogic.guess(35));
        assertEquals(Game.LOWER, gameLogic.guess(65));

        assertEquals(Game.FORMER_GUESS_WAS_ENTERED_AGAIN, gameLogic.guess(25));

        assertEquals(Game.INVALID_GUESS, gameLogic.guess(-1));
        assertEquals(Game.INVALID_GUESS, gameLogic.guess(100));

        assertEquals(Game.GREATER, gameLogic.guess(0));
        assertEquals(Game.LOWER, gameLogic.guess(99));

        assertEquals(Game.WON, gameLogic.guess(50));

        assertEquals(Game.GAME_OVER, gameLogic.guess(40));
        assertEquals(Game.GAME_OVER, gameLogic.guess(50));
    }

    @Test
    public void testGetGuessCount()
    {
        assertEquals(0, gameLogic.getGuessCount());
        
        gameLogic.guess(25);
        assertEquals(1, gameLogic.getGuessCount());
        
        gameLogic.guess(75);
        assertEquals(2, gameLogic.getGuessCount());

        gameLogic.guess(35);
        assertEquals(3, gameLogic.getGuessCount());
        
        gameLogic.guess(65);
        assertEquals(4, gameLogic.getGuessCount());
        
        gameLogic.guess(25);
        assertEquals(4, gameLogic.getGuessCount()); 
        
        gameLogic.guess(-1);
        assertEquals(4, gameLogic.getGuessCount());
        
        gameLogic.guess(100);
        assertEquals(4, gameLogic.getGuessCount());
        
        gameLogic.guess(25);
        assertEquals(4, gameLogic.getGuessCount());
        
        gameLogic.guess(30);
        assertEquals(5, gameLogic.getGuessCount());
        
        gameLogic.guess(99);
        assertEquals(6, gameLogic.getGuessCount());
     
        gameLogic.guess(50);
        assertEquals(7, gameLogic.getGuessCount());
        
        gameLogic.guess(40);
        assertEquals(7, gameLogic.getGuessCount());
        
        gameLogic.guess(50);
        assertEquals(7, gameLogic.getGuessCount());
        
        gameLogic.startNewGame();
        assertEquals(0, gameLogic.getGuessCount());
    }
    
    @Test
    public void testIsGameOver() {
        assertFalse(gameLogic.isGameOver());
        
        gameLogic.guess(25);
        assertFalse(gameLogic.isGameOver());
        
        gameLogic.guess(75);
        assertFalse(gameLogic.isGameOver());
        
        gameLogic.guess(35);
        assertFalse(gameLogic.isGameOver());
        
        gameLogic.guess(65);
        assertFalse(gameLogic.isGameOver());
   
        gameLogic.guess(-1);
        assertFalse(gameLogic.isGameOver());
        
        gameLogic.guess(100);
        assertFalse(gameLogic.isGameOver());
        
        gameLogic.guess(30);
        assertFalse(gameLogic.isGameOver());
        
        gameLogic.guess(99);
        assertFalse(gameLogic.isGameOver());
        
        gameLogic.guess(50);
        assertTrue(gameLogic.isGameOver());
        
        gameLogic.guess(40);
        assertTrue(gameLogic.isGameOver());
        
        gameLogic.guess(50);
        assertTrue(gameLogic.isGameOver());
        
        gameLogic.startNewGame();
        assertFalse(gameLogic.isGameOver());
    }

    @Test
    public void testIsLost() {
        assertFalse(gameLogic.isLost());
        
        gameLogic.guess(25);
        assertFalse(gameLogic.isLost());
        
        gameLogic.guess(75);
        assertFalse(gameLogic.isLost());
        
        gameLogic.guess(35);
        assertFalse(gameLogic.isLost());
        
        gameLogic.guess(65);
        assertFalse(gameLogic.isLost());
        
        gameLogic.guess(-1);
        assertFalse(gameLogic.isLost());
        
        gameLogic.guess(100);
        assertFalse(gameLogic.isLost());
        
        gameLogic.guess(30);
        assertFalse(gameLogic.isLost());
        
        gameLogic.guess(99);
        assertFalse(gameLogic.isLost());
        
        gameLogic.guess(50);
        assertFalse(gameLogic.isLost());
        
        gameLogic.guess(50);
        assertFalse(gameLogic.isLost());
        
    }

    @Test
    public void testStartNewGame() {
        int[] secretNumbers = new int[100];
        
        for (int i = 0; i < 1000; i++) {
            int newNumber = gameLogic.startNewGame();

            assertTrue(newNumber >= 0 && newNumber <= 99);            
            
            secretNumbers[newNumber] = secretNumbers[newNumber] + 1;
        }
        
        int countOfDifferentNums = 0;
        
        for (int value : secretNumbers) {
            if (value > 0) {
                countOfDifferentNums++;
            }
        }
        
        assertTrue(countOfDifferentNums > 15);
    }   

    @Test
    public void testisGameOver()
    {
        assertEquals(false, gameLogic.isGameOver());
        assertEquals(false, gameLogic.isGameOver());
    }
}

