

/**
 * Rozhraní IGame definuje základní vlastnosti hry "Hádání čísel".
 *
 * Počítač si bude "myslet" číslo od 0 do 99, úkolem hráče je uhodnout toto číslo.
 * Počítač bude hráči oznamovat, zda jeho typ je větší nebo menší než hledané číslo.
 * Hra bude počítat počet pokusů. Pokud hráč zadá tip, který je mimo interval 0 - 99,
 * bude upozorněn a pokus se nebude počítat.
 *
 * Hra může být omezená jen na určitý počet pokusů, po jejichž vyčerpání skončí prohrou,
 * nebo může nechat hráče hádat bez omezení, dokud neuhodne správné číslo.
 *
 * @author Jarmila Pavlíčková
 * @author Jan Říha
 */
public interface IGame
{

    /**
     * Metoda, která nastartuje novou hru. Bude vygenerováno nové číslo
     * k hádání, počet pokusů nastaven na 0, atd...
     *
     * @return vygenerované číslo k hádání (využívá se při testování)
     */
    public int startNewGame();

    /**
     * Metoda pro předání tipu od hráče. Metoda vrací text, který hráči řekne, zda
     * jeho tip je menší nebo větší než hádané číslo, případně že uhodl nebo je
     * jeho tip mimo interval 0 - 99.
     *
     * @param guess tipované číslo
     * @return odpoveď počítače např. "Hádané číslo je menší než tvůj tip"
     */
    public String guess(int guess);

    /**
     * Metoda vrací počet kolikrát již hráč zadal svůj tip (pokusy mimo interval
     * se nepočítají).
     *
     * @return počet pokusů
     */
    public int getGuessCount();

    /**
     * Metoda, která říká zda hra již skončila.
     *
     * @return true v případě, že hráč již číslo uhodl; jinak false
     */
    public boolean isGameOver();

    /**
     * Metoda, která říká zda hráč prohrál.
     *
     * @return true v případě, že hráč prohrál - neuhodl hádané číslo v rámci povoleného
     * počtu pokusů; jinak false
     */
    public boolean isLost();
    
}
