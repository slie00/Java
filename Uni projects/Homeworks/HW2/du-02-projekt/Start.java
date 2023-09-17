/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */


import java.io.*;

/*******************************************************************************
 * Třída Start slouží ke spouštění aplikace Guess Number.
 *
 * @author    Jan Říha
 * @version   1.1
 */
public class Start
{
    private static BufferedReader br;

    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args) {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));

            printLine("Vítejte ve hře hádání čísel.");
            printLine("Pravidla jsou snad jasná - sami jste je programovali. :)");
            newLine();

            IGame game = new Game();
            boolean playAgain = true;

            while (playAgain) {
                play(game);

                playAgain = readYesNo("Chceš hrát znovu?");
            }

            printLine("Hra byla ukončena.");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception ex) {}
            }
        }
    }

    private static void newLine() {
        System.out.println();
    }

    private static void printLine(String text) {
        printLine(text, true);
    }

    private static void printLine(String text, boolean newLine) {
        System.out.print(text);

        if (newLine) {
            newLine();
        }
    }

    private static String readString(String question) throws IOException {
        printLine(question + " ", false);
        return br.readLine();
    }

    private static int readInt(String question) throws IOException {
        int number = 0;
        boolean valid = false;

        while (!valid) {
            String text = readString(question);

            try {
                number = Integer.parseInt(text);
                valid = true;
            } catch(NumberFormatException e) {
                printLine("Neplatný vstup, musíš zadat celé číslo.");
                newLine();
            }
        }

        return number;
    }

    private static boolean readYesNo(String question) throws IOException {
        boolean result = false;
        boolean valid = false;

        while (!valid) {
            String text = readString(question + " (a/n)");

            if (text.trim().equalsIgnoreCase("A")) {
                result = true;
                valid = true;
            } else if (text.trim().equalsIgnoreCase("N")) {
                result = false;
                valid = true;
            } else {
                printLine("Neplatný vstup, musíš zadat znak 'A' nebo 'N'.");
                newLine();
            }
        }

        newLine();
        return result;
    }

    private static void play(IGame game) throws IOException {
        game.startNewGame();

        while(!game.isGameOver()) {
            int tip = readInt("Zadej svůj tip:");
            String result = game.guess(tip);

            if (result == null) {
                throw new NullPointerException("Hra není správně naimplementovaná.");
            }

            printLine(result);
            newLine();
        }

        if (game.isLost()) {
            printLine("Prohrál(a) jsi, během " + game.getGuessCount() + " pokusů se Ti nepodařilo číslo uhodnout.");
        } else {
            printLine("Vyhrál(a) jsi, na uhodnutí čísla jsi potřeboval(a) " + game.getGuessCount() + " pokusů.");
        }

        printLine("Děkuji Ti za hru.");
        newLine();
    }
}
