package game;

/**
 * Rozhraní definující herní objekt, který je označený nějakým jménem
 * <i>(jednoslovným identifikátorem)</i>. Rozhraní implementují herní příkazy,
 * prostory, předměty a měly by ho implementovat i jakékoliv další herní objekty,
 * které má být možné zadat na konzoli jako parametry příkazů.
 *
 * @author Sliusareva Elizaveta
 * @version ZS-2020, 2021-01-10
 */
public interface INamed
{
    /**
     * Metoda vrací název herního objektu. Jedná se o jedno slovo, které hráč
     * používá pro identifikaci herního objektu na konzoli, např. 'nápověda',
     * 'domeček', 'víno' apod.
     *
     * @return název herního objektu
     */
    String getName();
}
