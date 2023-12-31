package tests;

/**
 * Instance interfejsu {@code Scenario} představují scénáře, podle kterých
 * je možno hrát hru, pro kterou jsou určeny. Aby bylo možno jednotlivé
 * scénáře od sebe odlišit, je každý pojmenován.
 *
 * Později každému scénáři přiřadíme také typ, podle které lze blíže určit,
 * k čemu je možno daný scénář použít.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2020-Winter
 */
public enum Scenario implements IScenario {
   
    HAPPY (
        new ScenarioStep(0, "",
            "Vítejte!"
                + "\nProbudili jste se v opuštěném strašidelném domě"
                + "\nVaším cílem je najít cestu ven. Buďte opatrní a nenechte se chytit do pasti. Hodně štěstí!"
                + "\nNevíte-li, jak pokračovat, zadejte příkaz 'nápověda'.",
            
            "Sála",
            new String[] {"Biblioteka", "Koupelna", "Kuchyň"},
            new String[] { },
            new String[] { }
        ),
        
        new ScenarioStep("jdi Kuchyň",
            "Jsi v prostoru 'Kuchyň'."
            + "\nToto je kuchyň, tady najdeš něco k jídlu."
            + "\n"
            + "\nVýchody: Koupelna Sála"
            + "\nPředměty: čokoláda",
        
            "Kuchyň",
            new String[] {"Koupelna","Sála"},
            new String[] {"čokoláda"},
            new String[] { }
        ),
        
        new ScenarioStep("seber čokoláda",
            "Sebral(a) jsi předmět 'čokoláda' a uložil(a) ho do inventáře.",
        
            "Kuchyň",
            new String[] {"Koupelna","Sála"},
            new String[] { },
            new String[] {"čokoláda"}
        ),

    
        new ScenarioStep("jdi Koupelna",
            "Jsi v prostoru 'Koupelna'."
            + "\nTady najdeš baterku ."
            + "\n"
            + "\nVýchody: Kuchyň Sála"
            + "\nPředměty: baterka",

            "Koupelna",
            new String[] {"Kuchyň","Sála"},
            new String[] {"baterka"},
            new String[] {"čokoláda"}
        ),
        
        new ScenarioStep("seber baterka",
            "Sebral(a) jsi předmět 'baterka' a uložil(a) ho do inventáře.",

            "Koupelna",
            new String[] {"Sála","Kuchyň"},
            new String[] { },
            new String[] {"čokoláda","baterka"}
        ),

        new ScenarioStep("jdi Sála ",
            "Jsi v prostoru 'Sála'."
            + "\nTady jsi se probudil(a). Vidíš dveře do kuchyně, biblioteky a koupelny. "
            + "\n"
            + "\nVýchody: Biblioteka Koupelna Kuchyň"
            + "\nPředměty:",

            "Sála",
            new String[] {"Biblioteka", "Koupelna", "Kuchyň"},
            new String[] { },
            new String[] {"čokoláda","baterka"}
        ),

        new ScenarioStep("jdi Biblioteka",
            "Jsi v prostoru 'Biblioteka'."
            + "\nZde máš najít knihu, ve které je napsana pohádka, pomocí které uklidníš zlého ducha a on ti dá klíč."
            + "\n"
            + "\nVýchody: Podkroví Schody Sála"
            + "\nPředměty:",

            "Biblioteka",
            new String[] { "Sála", "Podkroví", "Schody" },
            new String[] { },
            new String[] { "čokoláda","baterka" }
        ),
        
        new ScenarioStep("rozsvěti",
            "Rozsvítil(a) jsi tenhle pokoj,teď můžeš najít skryté věci...",

            "Biblioteka",
            new String[] { "Sála", "Podkroví", "Schody" },
            new String[] {"kniha"},
            new String[] { "čokoláda"}
        ),
        
        new ScenarioStep("pokoj",
            "Věci v pokoje 'Biblioteka':  kniha",

            "Biblioteka",
            new String[] { "Sála", "Podkroví", "Schody" },
            new String[] {"kniha"},
            new String[] { "čokoláda"}
        ),

        new ScenarioStep("seber kniha",
            "Sebral(a) jsi předmět 'kniha' a uložil(a) ho do inventáře.",
            
            "Biblioteka",
            new String[] { "Sála", "Podkroví", "Schody" },
            new String[] { },
            new String[] {"čokoláda", "kniha" }
        ),
        
        new ScenarioStep("sněz čokoláda",
            "Snědl(a) jsi 'čokoláda' a teď máš více síl.",
            
            "Biblioteka",
            new String[] { "Sála", "Podkroví", "Schody" },
            new String[] {  },
            new String[] { "kniha" }
         ),   

        new ScenarioStep("jdi Podkroví ",
            "Jsi v prostoru 'Podkroví'."
            + "\nZde uvidíš zlého ducha, který má rád pohádky"
            + "\n"
            + "\nVýchody: Biblioteka"
            + "\nPředměty: duch",
            
            "Podkroví",
            new String[] { "Biblioteka" },
            new String[] {"duch"},
            new String[] { "kniha" }
        ),
        
        new ScenarioStep("přečti kniha duch",
            "Spřátelil ses s duchem, gratuluju! Klíč k východu už máš v batohu.",
            
            "Podkroví",
            new String[] { "Biblioteka" },
            new String[] { },
            new String[] { "klíč" }
            
        ),
            
         new ScenarioStep("jdi Biblioteka",
            "Jsi v prostoru 'Biblioteka'."
            + "\nZde máš najít knihu, ve které je napsana pohádka, pomocí které uklidníš zlého ducha a on ti dá klíč."
            + "\n"
            + "\nVýchody: Podkroví Schody Sála"
            + "\nPředměty:",

            "Biblioteka",
            new String[] { "Sála", "Podkroví", "Schody" },
            new String[] { },
            new String[] { "klíč" }
        ),
        
        new ScenarioStep("jdi Schody",
            "Jsi v prostoru 'Schody'."
            + "\nPřed tebou je rozcestí. Pokud půjedeš doprava, dostaneš se do sklepu, pokud doleva – najdeš východ. "
            + "\n"
            + "\nVýchody: Biblioteka Sklep Východ"
            + "\nPředměty:",

            "Schody",
            new String[] { "Biblioteka", "Sklep", "Východ"},
            new String[] { },
            new String[] { "klíč" }
        ),
        
        new ScenarioStep("jdi Východ",
            "Jsi v prostoru 'Východ'."
            + "\nPomocí klíče, který ti dal duch, otevříš dveře "
            + "\n"
            + "\nVýchody: Schody"
            + "\nPředměty:"
            + "\n"
            + "\nNašel jsi cestu ven a vyhrál jsi.",

            "Východ",
            new String[] { "Schody" },
            new String[] {},
            new String[] {"klíč"}
           
     )
  );

    /**
     * Jednotlivé kroky daného scénáře.
     */
    private final ScenarioStep[] steps;

    /**
     * Vytvoří nový scénář zadaného názvu určený pro zadanou hru. Konstruktor prověří,
     * jestli kroky scénáře alespoň formálně vyhovují požadavkům.
     *
     * @param steps jednotlivé kroky realizující daný scénář, příkaz v prvním <i>(přesněji
     *              nultém)</i> kroku musí být definována jako prázdný řetězec
     */
    Scenario(ScenarioStep... steps) {
        this.steps = steps;
    }

    /**
     * Vrátí vektor (jednorozměrné pole) kroků  daného scénáře.
     *
     * @return vektor kroků daného scénáře
     */
    public ScenarioStep[] steps() {
        return steps.clone();
    }

}
