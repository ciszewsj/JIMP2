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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {

        }
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

        assertEquals(9, gameRules.getDH1());
        assertEquals(13, gameRules.getDR1());
        assertEquals(14, gameRules.getDV1());
        assertEquals(13, gameRules.getDV2());
        assertEquals(40, gameRules.getH1());
        assertEquals(45, gameRules.getPC());
        assertEquals(30, gameRules.getPD());
        assertEquals(90, gameRules.getPKB());
        assertEquals(100, gameRules.getPW());
        assertEquals(50, gameRules.getR1());
        assertEquals(10, gameRules.getT1());
        assertEquals(5, gameRules.getT2());
        assertEquals(100, gameRules.getT3());
        assertEquals(100, gameRules.getV1());
        assertEquals(10, gameRules.getV2());
        assertEquals(15, gameRules.getX1());
    }

    @Test
    public void gameRulesTest3() {

        int DH1 = gameRules.getDH1();
        int DR1 = gameRules.getDR1();
        int DV1 = gameRules.getDV1();
        int DV2 = gameRules.getDV2();
        int H1 = gameRules.getH1();
        int PC = gameRules.getPC();
        int PD = gameRules.getPD();
        int PKB = gameRules.getPKB();
        int PW = gameRules.getPW();
        int R1 = gameRules.getR1();
        int T1 = gameRules.getT1();
        int T2 = gameRules.getT2();
        int T3 = gameRules.getT3();
        int V1 = gameRules.getV1();
        int V2 = gameRules.getV2();
        int X1 = gameRules.getX1();

        GameOption.ReadFromFile("testData\\test3.txt", gameRules, error);

        assertEquals(DH1, gameRules.getDH1());
        assertEquals(DR1, gameRules.getDR1());
        assertEquals(DV1, gameRules.getDV1());
        assertEquals(DV2, gameRules.getDV2());
        assertEquals(H1, gameRules.getH1());
        assertEquals(PC, gameRules.getPC());
        assertEquals(PD, gameRules.getPD());
        assertEquals(PKB, gameRules.getPKB());
        assertEquals(PW, gameRules.getPW());
        assertEquals(R1, gameRules.getR1());
        assertEquals(T1, gameRules.getT1());
        assertEquals(T2, gameRules.getT2());
        assertEquals(T3, gameRules.getT3());
        assertEquals(V1, gameRules.getV1());
        assertEquals(V2, gameRules.getV2());
        assertEquals(X1, gameRules.getX1());
    }

    @Test
    public void gameRulesTest4() {
        GameOption.ReadFromFile("testData\\test4.txt", gameRules, error);

        int DH1 = gameRules.getDH1();
        int DR1 = gameRules.getDR1();
        int DV1 = gameRules.getDV1();
        int DV2 = gameRules.getDV2();
        int H1 = gameRules.getH1();
        int PC = gameRules.getPC();
        int PD = gameRules.getPD();
        int PKB = gameRules.getPKB();
        int PW = gameRules.getPW();
        int R1 = gameRules.getR1();
        int T1 = gameRules.getT1();
        int T2 = gameRules.getT2();
        int T3 = gameRules.getT3();
        int V1 = gameRules.getV1();
        int V2 = gameRules.getV2();
        int X1 = gameRules.getX1();

        GameOption.ReadFromFile("testData\\test3.txt", gameRules, error);

        assertEquals(DH1, gameRules.getDH1());
        assertEquals(DR1, gameRules.getDR1());
        assertEquals(DV1, gameRules.getDV1());
        assertEquals(DV2, gameRules.getDV2());
        assertEquals(H1, gameRules.getH1());
        assertEquals(PC, gameRules.getPC());
        assertEquals(PD, gameRules.getPD());
        assertEquals(PKB, gameRules.getPKB());
        assertEquals(PW, gameRules.getPW());
        assertEquals(R1, gameRules.getR1());
        assertEquals(T1, gameRules.getT1());
        assertEquals(T2, gameRules.getT2());
        assertEquals(T3, gameRules.getT3());
        assertEquals(V1, gameRules.getV1());
        assertEquals(V2, gameRules.getV2());
        assertEquals(X1, gameRules.getX1());
    }

    @Test
    public void gameRulesT1ActionTest() {
        int V1 = gameRules.getV1();
        int DV1 = gameRules.getDV1();
        int V2 = gameRules.getV2();
        int DV2 = gameRules.getDV2();
        int R1 = gameRules.getR1();
        int DR1 = gameRules.getDR1();
        int H1 = gameRules.getH1();
        int DH1 = gameRules.getDH1();

        gameRules.makeT1();

        assertEquals(V1 + DV1, gameRules.getV1());
        assertEquals(V2 + DV2, gameRules.getV2());
        assertEquals(R1 - DR1, gameRules.getR1());
        assertEquals(H1 - DH1, gameRules.getH1());

        for (int i = 0; i < 32678; i++) {
            gameRules.makeT1();
        }
        assertTrue(V1 < 32678);
        assertTrue(V2 < 32678);
        assertTrue(R1 > 0);
        assertTrue(H1 > 0);
    }
}
