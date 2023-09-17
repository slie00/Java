package game;

/**
 * Hlavní třída hry. Vytváří a uchovává odkazy na instance tříd {@link Bag}
 * a {@link World} a také vytváří seznam platných příkazů a instance tříd
 * provádějících jednotlivé příkazy.
 * <p>
 * Veškeré informace o stavu hry <i>(mapa prostorů, inventář, vlastnosti
 * hlavní postavy, informace o plnění úkolů apod.)</i> by měly být uložené
 * zde v podobě datových atributů.
 * <p>
 * Během hry třída vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author Elizaveta Sliusareva
 * @version ZS-2020, 2021-01-10
 */
public class Game
{
    private ListOfActions allActions;
    private boolean gameOver;
    private Bag bag;
    private World world;
    private boolean isStrong;
    
    /**
     * testuje jestli hrač snědl čokoládu 
     * 
     * @return boolean "true" pokud ano, pokud ne - "false"
     */
    public boolean isStrong() {
        return this.isStrong;
    }
    
    /**
     * změní se na true pokud hrač sní čokoládu
     * 
     */
    public void setStrong() {
        
        this.isStrong = true;
    }
    
    /**
     * Konstruktor třídy, vytvoří hru a seznam platných příkazů. Hra po
     * vytvoření neběží, je nutné ji zahájit zadáním 'prázdného příkazu'.
     */
    public Game()
    {
        allActions = new ListOfActions();
        allActions.addAction(new ActionHelp(this));
        allActions.addAction(new ActionTerminate(this));
        allActions.addAction(new ActionMove(this));
        allActions.addAction(new ActionPick(this));
        allActions.addAction(new ActionPut(this));
        allActions.addAction(new ActionBag(this));
        allActions.addAction(new ActionEat(this));
        allActions.addAction(new ActionRoomItems(this));
        allActions.addAction(new ActionLight(this));
        allActions.addAction(new ActionRead(this));
        this.bag = new Bag();

        isStrong = false; //todo
        gameOver = true;
    }

    /**
     * Metoda vrací odkaz na katalog všech platných herních příkazů.
     *
     * @return katalog herních příkazů
     */
    public ListOfActions getAllActions()
    {
        return allActions;
    }

    /**
     * Metoda vrací informaci, zda hra již skončila <i>(je jedno, jestli
     * výhrou, prohrou nebo příkazem 'konec')</i>.
     *
     * @return {@code true}, pokud hra již skončila; jinak {@code false}
     */
    public boolean isGameOver()
    {
        return gameOver;
    }

    /**
     * Metoda nastaví příznak indikující, zda hra skončila. Metodu
     * využívá třída {@link ActionTerminate}, mohou ji ale použít
     * i další implementace rozhraní {@link IAction}.
     *
     * @param gameOver příznak indikující, zda hra již skončila
     */
    public void setGameOver(boolean gameOver)
    {
        this.gameOver = gameOver;
    }

    /**
     * Metoda vrací odkaz na inventář hráče.
     *
     * @return inventář hráče.
     */
    public synchronized Bag getBag()
    {
        return bag;
    }

    /**
     * Metoda vrací odkaz na mapu prostorů herního světa.
     *
     * @return mapa prostorů herního světa
     */
    public World getWorld()
    {
        return world;
    }

    /**
     * Metoda zpracuje herní příkaz <i>(jeden řádek textu zadaný na konzoli)</i>.
     * Řetězec uvedený jako parametr se rozdělí na slova. První slovo je považováno
     * za název příkazu, další slova za jeho parametry.
     * <p>
     * Metoda nejprve ověří, zda první slovo odpovídá hernímu příkazu <i>(např.
     * 'napoveda', 'konec', 'jdi' apod.)</i>. Pokud ano, spustí obsluhu tohoto
     * příkazu a zbývající slova předá jako parametry.
     * <p>
     * Pokud hra aktuálně neběží, metoda přijme prázdný textový řetězec jako pokyn
     * pro zahájení nové hry.
     *
     * @param input text, který hráč zadal na konzoli jako příkaz pro hru
     * @return výsledek zpracování <i>(informace pro hráče, které se vypíšou na konzoli)</i>
     */
    public synchronized String executeAction(String input)
    {
        if (gameOver && input.equals("")) {
            return startGame();
        } else if (gameOver) {
            return "Hra aktuálně neběží, pro její zahájení stiskni klávesu 'Enter'.";
        } else {
            String[] words = input.split("[ \t]+");

            String actionName = words[0];
            String[] actionParameters = new String[words.length - 1];

            for (int i = 0; i < actionParameters.length; i++) {
                actionParameters[i] = words[i + 1];
            }

            if (allActions.checkAction(actionName)) {
                IAction action = allActions.getAction(actionName);
                if(action == null) {
                    return "Tomu nerozumím";
                }
                String actionResult = action.execute(actionParameters);
                String gameStateCheck = checkGameEnd();
                
                String result = actionResult;
                if (gameStateCheck != null) {
                    result += "\n\n" + gameStateCheck;
                    
                }
                return result;
            } else {
                return "Tomu nerozumím, příkaz '" + actionName + "' neznám.";
            }
        }
    }
    
    private String checkGameEnd() {
        if (World.VICTORY_PLACE_NAME.equals(world.getCurrentPlace().getName())) {
         gameOver = true;
         return "Našel jsi cestu ven a vyhrál jsi.";
        
    }
    return null;
}

 private String startGame()
    {
        gameOver = false;
        world = new World();

        return "Vítejte!"
                + "\nProbudili jste se v opuštěném strašidelném domě"
                + "\nVaším cílem je najít cestu ven. Buďte opatrní a nenechte se chytit do pasti. Hodně štěstí!"
                + "\nNevíte-li, jak pokračovat, zadejte příkaz 'nápověda'.";
    }
}
