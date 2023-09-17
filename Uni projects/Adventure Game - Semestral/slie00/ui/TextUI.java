package ui;

import game.Game;

import java.util.Scanner;

/**
 * Třída představující uživatelské rozhraní aplikace. Zajišťuje načítání
 * příkazů z konzole a výpis reakcí hry.
 *
 * @author Jan Říha
 * @version ZS-2020, 2020-12-14
 */
public class TextUI
{
    private Scanner scanner;
    private Game game;

    /**
     * Konstruktor třídy, vytvoří uživatelské rozhraní pro danou hru.
     *
     * @param game hra
     */
    public TextUI(Game game)
    {
        scanner = new Scanner(System.in);
        this.game = game;
    }

    /**
     * Metoda zajišťuje hraní hry. Nejprve zahájí hru předáním prázdného
     * příkazu. Poté v cyklu načítá zadané příkazy z konzole, předává je
     * hře ke zpracování a vypisuje reakce hry. To se neustále opakuje,
     * dokud hra prostřednictvím metody {@link Game#isGameOver() isGameOver}
     * neoznámí, že skončila.
     */
    public void play()
    {
        System.out.println(game.executeAction(""));

        while (!game.isGameOver()) {
            String line = readLine();
            System.out.println(game.executeAction(line));
        }
    }

    private String readLine()
    {
        System.out.print("\n> ");
        return scanner.nextLine();
    }
}

