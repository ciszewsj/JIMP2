package testPackage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tankgame.ErrorWindowController;
import tankgame.GameOption;
import tankgame.GameRules;

public class GameRulesTest {

    private GameRules gameRules;
    private ErrorWindowController error;
    
    public GameRulesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        gameRules = new GameRules();
        error = new ErrorWindowController(0, 0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void gameRulesTest1() {
        GameOption.ReadFromFile("testData\\test1.txt", gameRules, error);
        
        assertEquals(1, gameRules.getDH1());
        assertEquals(1, gameRules.getDR1());
        assertEquals(1, gameRules.getDV1());
        assertEquals(1, gameRules.getDV2());
        assertEquals(1, gameRules.getH1());
        assertEquals(1, gameRules.getPC());
        assertEquals(1, gameRules.getPD());
        assertEquals(1, gameRules.getPKB());
        assertEquals(1, gameRules.getPW());
        assertEquals(1, gameRules.getR1());
        assertEquals(1, gameRules.getT1());
        assertEquals(1, gameRules.getT2());
        assertEquals(1, gameRules.getT3());
        assertEquals(1, gameRules.getV1());
        assertEquals(1, gameRules.getV2());
        assertEquals(1, gameRules.getX1());
    }

    @Test
    public void gameRulesTest2() {
        GameOption.ReadFromFile("testData\\test2.txt", gameRules, error);
        
        assertEquals(1, gameRules.getDH1());
        assertEquals(1, gameRules.getDR1());
        assertEquals(1, gameRules.getDV1());
        assertEquals(1, gameRules.getDV2());
        assertEquals(1, gameRules.getH1());
        assertEquals(1, gameRules.getPC());
        assertEquals(1, gameRules.getPD());
        assertEquals(1, gameRules.getPKB());
        assertEquals(1, gameRules.getPW());
        assertEquals(1, gameRules.getR1());
        assertEquals(1, gameRules.getT1());
        assertEquals(1, gameRules.getT2());
        assertEquals(1, gameRules.getT3());
        assertEquals(1, gameRules.getV1());
        assertEquals(1, gameRules.getV2());
        assertEquals(1, gameRules.getX1());
    }

    @Test
    public void gameRulesTest3() {
        GameOption.ReadFromFile("testData\\test3.txt", gameRules, error);
        
        assertEquals(1, gameRules.getDH1());
        assertEquals(1, gameRules.getDR1());
        assertEquals(1, gameRules.getDV1());
        assertEquals(1, gameRules.getDV2());
        assertEquals(1, gameRules.getH1());
        assertEquals(1, gameRules.getPC());
        assertEquals(1, gameRules.getPD());
        assertEquals(1, gameRules.getPKB());
        assertEquals(1, gameRules.getPW());
        assertEquals(1, gameRules.getR1());
        assertEquals(1, gameRules.getT1());
        assertEquals(1, gameRules.getT2());
        assertEquals(1, gameRules.getT3());
        assertEquals(1, gameRules.getV1());
        assertEquals(1, gameRules.getV2());
        assertEquals(1, gameRules.getX1());
    }

    @Test
    public void gameRulesTest4() {
        GameOption.ReadFromFile("testData\\test4.txt", gameRules, error);
        
        assertEquals(1, gameRules.getDH1());
        assertEquals(1, gameRules.getDR1());
        assertEquals(1, gameRules.getDV1());
        assertEquals(1, gameRules.getDV2());
        assertEquals(1, gameRules.getH1());
        assertEquals(1, gameRules.getPC());
        assertEquals(1, gameRules.getPD());
        assertEquals(1, gameRules.getPKB());
        assertEquals(1, gameRules.getPW());
        assertEquals(1, gameRules.getR1());
        assertEquals(1, gameRules.getT1());
        assertEquals(1, gameRules.getT2());
        assertEquals(1, gameRules.getT3());
        assertEquals(1, gameRules.getV1());
        assertEquals(1, gameRules.getV2());
        assertEquals(1, gameRules.getX1());
    }
}
