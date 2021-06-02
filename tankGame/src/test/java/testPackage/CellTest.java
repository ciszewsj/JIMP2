package testPackage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tankgame.Cell;
import tankgame.GunSide;
import tankgame.Player;

public class CellTest {

    private Cell cell;
    private Player player;

    private int ySize;

    public CellTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        cell = new Cell(9, 100, 10, 512, -50);
        player = new Player(0, 0, 0, GunSide.LEFT, 0, 0, 0, 0, 0, null, 0, 0);

        ySize = 1024;
    }

    @After
    public void tearDown() {
    }

    @Test
    public void moveTest() {
        cell.moveCell(1, ySize);
        assertEquals(-40, cell.getYPos());

        cell.moveCell(10, ySize);
        assertEquals(60, cell.getYPos());
    }

    @Test
    public void removeP1Test() {
        assertEquals(9, cell.getP1());

        cell.destroyCell(player);
        assertEquals(8, cell.getP1());

        cell.destroyCell(player);
        assertEquals(7, cell.getP1());

        cell.destroyCell(player);
        assertEquals(6, cell.getP1());

        cell.destroyCell(player);
        assertEquals(5, cell.getP1());

        cell.destroyCell(player);
        assertEquals(4, cell.getP1());

        cell.destroyCell(player);
        assertEquals(3, cell.getP1());

        cell.destroyCell(player);
        assertEquals(2, cell.getP1());

        cell.destroyCell(player);
        assertEquals(1, cell.getP1());

        cell.destroyCell(player);
        assertEquals(0, cell.getP1());

        assertTrue(!cell.isAlive());
    }

    @Test
    public void addP1Test() {
        for (int i = 0; i < 6; i++) {
            cell.destroyCell(player);
        }
        assertEquals(3, cell.getP1());

        for (int i = 0; i < 11; i++) {
            cell.addP1();
        }
        assertEquals(9, cell.getP1());

        cell.addP1();
        assertEquals(9, cell.getP1());

        for (int i = 0; i < 9; i++) {
            cell.destroyCell(player);
        }
        assertEquals(0, cell.getP1());

        cell.addP1();
        assertEquals(0, cell.getP1());

        assertTrue(!cell.isAlive());
    }

    @Test
    public void destroyCellOnDownWindowTest() {

        assertTrue(cell.getP1() != 0);

        int sec = (ySize - cell.getYPos()) / 10 + 10;
        cell.moveCell(sec, ySize);
        assertTrue(cell.getYPos() - cell.getH1() / 2 > ySize);

        assertEquals(0, cell.getP1());
        assertTrue(!cell.isAlive());

    }

    @Test
    public void destroyCellByPlayerTest() {
        assertEquals(0, player.getPoints());

        while (cell.isAlive()) {
            cell.destroyCell(player);
        }
        assertTrue(!cell.isAlive());
        assertEquals(cell.getPointsForDestroy(), player.getPoints());

    }

    @Test
    public void changeH1Test() {
        assertEquals(100, cell.getH1());

        cell.changeH1(70);
        assertEquals(70, cell.getH1());

    }
}
