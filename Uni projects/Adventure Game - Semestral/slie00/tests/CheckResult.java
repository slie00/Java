package tests;

/**
 * Pomocná třída pro uchovávání výsledků dílčí kontroly, která je
 * součástí vyhodnocení jednoho kroku v testovacím scénáři <i>(např.
 * kontrola aktuálního prostoru, dostupných sousedních prostorů apod.)</i>.
 */
public class CheckResult
{
    /** Výsledek indikující, že tato kontrola se v daném kroku neprovádí. */
    public static final CheckResult IGNORED_RESULT = new CheckResult(true, "<nevyhodnocuje se>");

    private boolean success;
    private String message;

    /**
     * Konstruktor třídy, vytvoří záznam s daným výsledkem a zprávou pro uživatele.
     *
     * @param success příznak, zda kontrola byla úspěšná
     * @param message zpráva pro uživatele
     */
    public CheckResult(boolean success, String message)
    {
        this.success = success;
        this.message = message;
    }

    /**
     * Vrací příznak, zda kontrola byla úspěšná.
     *
     * @return {@code true}, pokud kontrola byla úspěšná; jinak {@code false}
     */
    public boolean isSuccess()
    {
        return success;
    }

    /**
     * Vrací zprávu pro uživatele.
     *
     * @return zpráva pro uživatele
     */
    public String getMessage()
    {
        return message;
    }
}

