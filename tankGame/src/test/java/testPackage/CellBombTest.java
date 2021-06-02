package testPackage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tankgame.CellBomb;
import tankgame.GunSide;
import tankgame.Player;

public class CellBombTest {

    private CellBomb cellBomb;
    private Player player;

    public CellBombTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        cellBomb = new CellBomb(9, 9, 100);
        player = new Player(0, 0, 0, GunSide.LEFT, 0, 0, 0, 0, 0, null, 0, 0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void removeP1Test() {
        assertEquals(9, cellBomb.getP1());

        cellBomb.destroyCell(player);
        assertEquals(8, cellBomb.getP1());

        cellBomb.destroyCell(player);
        assertEquals(7, cellBomb.getP1());

        cellBomb.destroyCell(player);
        assertEquals(6, cellBomb.getP1());

        cellBomb.destroyCell(player);
        assertEquals(5, cellBomb.getP1());

        cellBomb.destroyCell(player);
        assertEquals(4, cellBomb.getP1());

        cellBomb.destroyCell(player);
        assertEquals(3, cellBomb.getP1());

        cellBomb.destroyCell(player);
        assertEquals(2, cellBomb.getP1());

        cellBomb.destroyCell(player);
        assertEquals(1, cellBomb.getP1());

        cellBomb.destroyCell(player);
        assertEquals(0, cellBomb.getP1());

        assertTrue(!cellBomb.isAlive());
    }

    @Test
    public void addP1Test() {
        for (int i = 0; i < 6; i++) {
            cellBomb.destroyCell(player);
        }
        assertEquals(3, cellBomb.getP1());

        for (int i = 0; i < 11; i++) {
            cellBomb.addP1();
        }
        assertEquals(9, cellBomb.getP1());

        cellBomb.addP1();
        assertEquals(9, cellBomb.getP1());

        for (int i = 0; i < 9; i++) {
            cellBomb.destroyCell(player);
        }
        assertEquals(0, cellBomb.getP1());

        cellBomb.addP1();
        assertEquals(0, cellBomb.getP1());

        assertTrue(!cellBomb.isAlive());
    }

    @Test
    public void destroyCellBombByPlayerTest() {
        assertEquals(0, player.getPoints());

        while (cellBomb.isAlive()) {
            cellBomb.destroyCell(player);
        }
        assertTrue(!cellBomb.isAlive());
        assertEquals(cellBomb.getPointsForDestroy(), player.getPoints());

    }

    @Test
    public void changeH1Test() {
        assertEquals(100, cellBomb.getH1());

        cellBomb.changeH1(70);
        assertEquals(70, cellBomb.getH1());
    }
}
