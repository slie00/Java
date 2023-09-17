package game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testovací třída pro komplexní otestování třídy {@link ListOfActions}.
 *
 * @author Jan Říha
 * @version ZS-2020, 2020-12-14
 */
public class ListOfActionsTest
{
    private ListOfActions listOfActions;

    
    private IAction aTerminate;
    private IAction aMove;

    @Before
    public void setUp()
    {
        Game game = new Game();
        aTerminate = new ActionTerminate(game);
        aMove = new ActionMove(game);
        
        listOfActions = new ListOfActions();
        listOfActions.addAction(aTerminate);
        listOfActions.addAction(aMove);
    }

    @Test
    public void testAddAndGetAction()
    {
        assertEquals(aTerminate, listOfActions.getAction(aTerminate.getName()));
        assertEquals(aMove, listOfActions.getAction(aMove.getName()));
        assertNull(listOfActions.getAction("nápověda"));
    }

    @Test
    public void testCheckAction()
    {
        assertTrue(listOfActions.checkAction(aTerminate.getName()));
        assertTrue(listOfActions.checkAction(aMove.getName()));

        if (aTerminate.getName().substring(0, 1).equals(aTerminate.getName().substring(0, 1).toLowerCase())) {
            assertFalse(listOfActions.checkAction(aTerminate.getName().toUpperCase()));
        } else {
            assertFalse(listOfActions.checkAction(aTerminate.getName().toLowerCase()));
        }

        assertFalse(listOfActions.checkAction("nápověda"));
    }

    @Test
    public void testGetAllNames()
    {
        String names = listOfActions.getAllNames();
        assertTrue(names.contains(aTerminate.getName()));
        assertTrue(names.contains(aMove.getName()));

        if (aTerminate.getName().substring(0, 1).equals(aTerminate.getName().substring(0, 1).toLowerCase())) {
            assertFalse(names.contains(aTerminate.getName().toUpperCase()));
        } else {
            assertFalse(names.contains(aTerminate.getName().toLowerCase()));
        }

        assertFalse(names.contains("nápověda"));

        assertEquals(2, names.split(", ").length);
    }

}


