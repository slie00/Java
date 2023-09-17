package tests;

/**
 * Instance interfejsu {@code IScenario} představují scénáře,
 * podle nichž je možno simulovat běh hry nebo hru testovat.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2020-Winter
 */
public interface IScenario
{
    /**
     * Vrátí vektor (jednorozměrné pole) kroků daného scénáře.
     *
     * @return Vektor kroků daného scénáře
     */
    ScenarioStep[] steps();
}
