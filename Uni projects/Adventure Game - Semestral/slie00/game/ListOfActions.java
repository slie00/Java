package game;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * Třída představující seznam přípustných příkazů, které lze ve hře používat.
 * Používá se pro rozpoznávání příkazů dle jejich názvů a vrácení odkazu
 * na instanci třídy implementující konkrétní příkaz. Každý nový příkaz
 * <i>(instance implementující rozhraní {@link IAction})</i> se musí
 * do seznamu přidat pomocí metody {@link #addAction(IAction) addAction}.
 * 
 * @author Sliusareva Elizaveta
 * @version ZS-2020, 2021-01-10
 */
public class ListOfActions
{
    private Set<IAction> actions;
    private String allNames;
    
     /**
      * Konstruktor třídy.
      */
    public ListOfActions()
    {
        actions = new HashSet<>();
        allNames = "";
    }

    /**
     * Metoda vkládá nový příkaz do seznamu.
     *
     * @param action instance třídy implementující rozhraní {@link IAction}, která představuje herní příkaz
     */
    public void addAction(IAction action)
    {
        actions.add(action);

        allNames = actions.stream()
                          .map(IAction::getName)
                          .sorted()
                          .collect(Collectors.joining(", "));
    }
    
    /**
     * Metoda zkontroluje, zda je možné ve hře použít příkaz se zadaným názvem.
     *
     * @param name název příkazu
     * @return {@code true}, pokud je možné příkaz ve hře použít; jinak {@code false}
     */
    public boolean checkAction(String name)
    {
        
        for (IAction action : actions) {
            if (name.equals(action.getName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Metoda vrací odkaz na instanci třídy implementující příkaz s daným názvem.
     *
     * @param name název příkazu, který chce hráč zavolat
     * @return instance třídy představující implementaci daného příkazu; {@code null}, pokud takový příkaz neexistuje
     */
    public IAction getAction(String name)
    {
        for (IAction action : actions) {
            if (name.equals(action.getName())) {
                return action;
            }
        }

        return null;
    }

    /**
     * Metoda vrací seznam názvů všech přípustných příkazů ve hře. Jednotlivé názvy jsou oddělené čárkou.
     *
     * @return seznam názvů přípustných příkazů
     */
    public String getAllNames()
    {
        return allNames;
    }
}
