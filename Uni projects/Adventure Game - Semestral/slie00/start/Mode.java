package start;

/**
 * Enumerace obsahující módy, ve kterých lze spustit aplikaci.
 *
 * @author Sliusareva Elizaveta
 * @version ZS-2020, 2021-01-10
 */
public enum Mode
{
    /** Základní výpis testovacích scénářů. */
    SIMULATE_SIMPLE,

    /** Podrobný výpis testovacích scénářů. */
    SIMULATE_COMPLEX,

    /** Spuštění testovacích scénářů. */
    RUN_TESTS;

    /**
     * Metoda zkontroluje, zda existuje spouštěcí mód aplikace s daným názvem.
     *
     * @param modeName název spouštěcího módu aplikace
     * @return {@code true}, pokud existuje spouštěcí mód aplikace s daným názvem; jinak {@code false}
     */
    public static boolean checkMode(String modeName) {
        for (Mode m : values()) {
            if (m.name().equalsIgnoreCase(modeName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Metoda vyhledá spouštěcí mód aplikace s daným názvem a vrátí na něj odkaz.
     *
     * @param modeName název spouštěcího módu aplikace
     * @return spouštěcí mód aplikace s daným názvem; {@code null}, pokud neexistuje
     */
    public static Mode getMode(String modeName) {
        for (Mode m : values()) {
            if (m.name().equalsIgnoreCase(modeName)) {
                return m;
            }
        }

        throw new IllegalArgumentException("Mód s názvem '" + modeName + "' neexistuje.");
    }
}
