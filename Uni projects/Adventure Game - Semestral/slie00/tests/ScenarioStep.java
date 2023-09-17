package tests;

import java.util.Arrays;
import java.util.Objects;

/**
 * <p>Třída {@code ScenarioStep} slouží jako přepravka k uchovávání informací
 * o jednotlivých zadávaných příkazech scénáře a o očekávaných stavech
 * programu po jejich provedení.</p>
 * <p>Kroky scénáře obsahují informace sloužící k ověření správné reakce
 * hry na příkaz zadávaný v příslušném kroku scénáře.</p>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2020-Winter
 */
public class ScenarioStep {

    /**
     * Index posledního vytvořeného kroku scénáře - z něj se odvozuje index
     * příštího kroku.
     */
    private static  int lastIndex = 0;

    /**
     * Vrátí hodnotu, na níž bude navazovat index následně vytvořeného kroku.
     *
     * @return Požadovaná hodnota
     */
    public static int getLastIndex() {
        return lastIndex;
    }

    /**
     * Nastaví index příštího kroku a vrátí index posledního vytvořeného kroku.
     *
     * @param next index příštího kroku
     * @return index posledního vytvořeného kroku
     */
    public static int setIndex(int next) {
        int ret = lastIndex;
        lastIndex = next - 1;
        return ret;
    }


    /**
     * Index daného kroku scénáře - typicky jeho pořadí v rámci scénáře.
     */
    public final int index;

    /**
     * Zadávaný příkaz.
     */
    public final String command;

    /**
     * Zpráva vydaná v reakci na zadaný příkaz, obsahuje-li {@code null}, nepočítá
     * se s ověřováním reakce hry.
     */
    public final String message;

    /**
     * Prostor, v němž se bude hráč nacházet po vykonaní příkazu.
     */
    public final String place;

    /**
     * Názvy prostorů, které bezprostředně sousedí s prostorem, v němž se bude hráč
     * nacházet po vykonaní příkazu, a které jsou z tohoto prostoru přímo dostupné.
     */
    public final String[] neighbors;

    /**
     * Názvy objektů aktuálně se nacházejících v prostoru, v němž se bude hráč
     * nacházet po vykonaní příkazu.
     */
    public final String[] items;

    /**
     * Názvy objektů v batohu po vykonání příkazu.
     */
    public final String[] bag;

    /**
     * Vytvoří krok umožňující otestování správné funkce hry. Index tohoto kroku bude
     * o jedničku větší než index kroku vytvořeného před ním, přesněji než poslední
     * zapamatovaný index.
     *
     * @param command    zadaný příkaz
     * @param message    zpráva vrácená v reakci na zadaný příkaz
     * @param place      název prostoru, v němž bude hráč po vykonání příkazu
     * @param neighbors  názvy prostorů, kam se je odsud možno přesunout
     * @param items      názvy objektů v aktuálním prostoru
     * @param bag        názvy objektů v batohu
     */
    public ScenarioStep(String command, String message, String place,
                        String[] neighbors, String[] items, String[] bag) {
        this(++lastIndex, command, message, place, neighbors, items, bag);
    }

    /**
     * Vytvoří krok umožňující otestování správné funkce hry, přičemž tento krok bude
     * mít zadaný index, na nějž budou navazovat indexy následně vytvořených kroků bez
     * explicitně zadaného indexu.
     *
     * @param index      číslo, které by mělo určovat pořadí daného kroku v rámci jeho scénáře
     * @param command    zadaný příkaz
     * @param message    zpráva vrácená v reakci na zadaný příkaz
     * @param place      název prostoru, v němž bude hráč po vykonání příkazu
     * @param neighbors  názvy prostorů, kam se je odsud možno přesunout
     * @param items      názvy objektů v aktuálním prostoru
     * @param bag        názvy objektů v batohu
     */
    public ScenarioStep(int index, String command, String message, String place,
                       String[] neighbors, String[] items, String[] bag) {
        this.index     = lastIndex = index;
        this.command   = command;
        this.message   = message;
        this.place     = place;
        this.neighbors = neighbors;
        this.items     = items;
        this.bag       = bag;
    }

    /**
     * Vrátí hash kód daného kroku scénáře.
     *
     * @return hash kód daného kroku scénáře
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.index;
        hash = 97 * hash + Objects.hashCode(this.command);
        hash = 97 * hash + Objects.hashCode(this.message);
        hash = 97 * hash + Objects.hashCode(this.place);
        hash = 97 * hash + Arrays.deepHashCode(this.neighbors);
        hash = 97 * hash + Arrays.deepHashCode(this.items);
        hash = 97 * hash + Arrays.deepHashCode(this.bag);
        return hash;
    }

    /**
     * Podrobný popis daného kroku scénáře. obsahuje-li atribut {@link #message}
     * hodnotu {@code null}, vypíše se pouze index kroku a zadaný příkaz.
     *
     * @return textový popis dané instance
     */
    @Override
    public String toString() {
        String ret = index + ". krok:  " + command
                + "\n----------------------------"
                + "\n" + message
                + "\n-----------------------------------------------------"
                + "\nProstor:  " + place
                + "\nSousedé:  " + Arrays.toString(neighbors)
                + "\nPředměty: " + Arrays.toString(items)
                + "\nBatoh:    " + Arrays.toString(bag);

        return ret;
    }

}
