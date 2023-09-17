package game;

/**
 * Rozhraní definující metody nutné pro implementaci herního příkazu. Pro každý
 * příkaz, který je možné ve hře použít, existuje třída zajišťující jeho obsluhu.
 * Třída musí implementovat metody definované tímto rozhraním.
 *
 * @author Sliusareva Elizaveta
 * @version ZS-2020, 2021-01-10
 */
public interface IAction extends INamed
{
    /**
     * Metoda zajišťující provedení herního příkazu. Počet parametrů <i>(očekávaných
     * prvků v poli)</i> je závislý na konkrétním příkazu, např. příkazy 'napoveda'
     * a 'konec' žádné parametry nemají, příkaz pro přesun mezi prostory patrně bude
     * mít jeden parametr <i>(cílový prostor)</i>, příkazy pro obchodování, používání
     * nebo kombinování předmětů mohou mít parametrů více.
     * <p>
     * Metoda musí zkontrolovat parametry <i>(počet, korektnost)</i>, provést změny
     * ve stavu hry a následně vrátit text, který se hráči vypíše na konzoli.
     *
     * @param parameters parametry příkazu <i>(počet závisí na konkrétním příkazu)</i>
     * @return výsledek zpracování <i>(informace pro hráče, které se vypíšou na konzoli)</i>
     */
    String execute(String... parameters);
}
