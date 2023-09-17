
import java.util.Random;
/**
 * @author: Sliusareva Elizaveta
 * @version: 2020-11-16
 */

public class Game implements IGame
{
    public static final String GAME_OVER = "Hra již neběží.";
    public static final String INVALID_GUESS = "Tip mimo povolený interval.";
    public static final String GREATER = "Tajné číslo je větší";
    public static final String LOWER = "Tajné číslo je menší";
    public static final String WON = "Vyhrál(a) jsi.";
    public static final String MAX_GUESSES_REACHED = "Překročil jsi maximalní počet pokusů";
    public static final String FORMER_GUESS_WAS_ENTERED_AGAIN = "Toto číslo jsi již tipoval(a)";
    private boolean[] guessedNumbers;
    
    public static final int MAX_GUESS_COUNT = 8;
    
    private static final Random GENERATOR = new Random();   
    
    private int secretNumber;
    private int guessCount;
    private boolean gameOver;
    
    
    public Game() {
        startNewGame();
    }
    
    public Game(int startingSecretNumber) {
        this();
        
        secretNumber = startingSecretNumber;
        guessCount = 0;
        gameOver = false;
 
    }

    public int startNewGame() {
        secretNumber = GENERATOR.nextInt(100);
        guessedNumbers = new boolean[100];
        guessCount = 0;
        gameOver = false;
        
        return secretNumber;
    }

    public String guess(int guess) {
        if (gameOver == true) {
            return GAME_OVER;
        }
        
        if (isLost()){
            gameOver = true;
            return MAX_GUESSES_REACHED;
        }

        if (guess < 0 || guess > 99) {
            return INVALID_GUESS;
        }
        
        if (guessedNumbers[guess]) {
            return FORMER_GUESS_WAS_ENTERED_AGAIN;
        } else {
            guessCount++;
            guessedNumbers[guess] = true;
        }
        
        if (guess < secretNumber) {
            return GREATER;
        }
        
        if (guess > secretNumber) {
            return LOWER;
        }
        
       gameOver = true;
       return WON;
    }

    public int getGuessCount() {
        return guessCount;
    }

    public boolean isGameOver() {
        
        return gameOver;
    }  

public boolean isLost() {
   /*if (guessCount >= MAX_GUESS_COUNT) {
            return true;
        } else {
     return false;
    */
   return guessCount >= MAX_GUESS_COUNT;
}
}
