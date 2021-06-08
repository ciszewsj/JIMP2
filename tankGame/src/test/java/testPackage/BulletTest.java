package testPackage;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tankgame.Bullet;
import tankgame.Cell;
import tankgame.CellBomb;
import tankgame.GunSide;
import tankgame.Player;

public class BulletTest {

    private int xWindowSize = 1024;
    private int yWindowSize = 1024;

    private Player player;

    public BulletTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        player = new Player(0, 0, 0, GunSide.LEFT, 0, 0, 0, 0, 0, null, 0, 0);
        xWindowSize = 1024;
        yWindowSize = 1024;
    }

    @After
    public void tearDown() {
    }

    @Test
    public void moveBulletTest() {
        Bullet leftBullet = new Bullet(0, 10, 0, 0, 0, player, GunSide.LEFT);

        assertEquals(leftBullet.getXPos(), 0);
        assertEquals(leftBullet.getYPos(), 0);

        leftBullet.makeMove(1, 1024, 1024);

        assertEquals(leftBullet.getXPos(), 10);
        assertEquals(leftBullet.getYPos(), 0);

        Bullet rightBullet = new Bullet(0, 10, 0, 0, 0, player, GunSide.RIGHT);

        rightBullet.makeMove(1, 1024, 1024);

        assertEquals(rightBullet.getXPos(), -10);
        assertEquals(rightBullet.getYPos(), 0);

        Bullet leftObliqueDownBullet = new Bullet(0, 10, 3.14 / 180 * 10, 0, 0, player, GunSide.LEFT);

        leftObliqueDownBullet.makeMove(1, 1024, 1024);

        assertTrue(leftObliqueDownBullet.getXPos() < 10);
        assertTrue(leftObliqueDownBullet.getYPos() != 0);

        Bullet leftObliqueUpBullet = new Bullet(0, 10, -3.14 / 180 * 10, 0, 0, player, GunSide.LEFT);

        leftObliqueUpBullet.makeMove(1, 1024, 1024);

        assertTrue(leftObliqueUpBullet.getXPos() < 10);
        assertTrue(leftObliqueUpBullet.getYPos() != 0);

        Bullet rightObliqueDownBullet = new Bullet(0, 10, 3.14 / 180 * 10, 0, 0, player, GunSide.RIGHT);

        rightObliqueDownBullet.makeMove(1, 1024, 1024);

        assertTrue(rightObliqueDownBullet.getXPos() > -10);
        assertTrue(rightObliqueDownBullet.getYPos() != 0);

        Bullet rightObliqueUpBullet = new Bullet(0, 10, -3.14 / 180 * 10, 0, 0, player, GunSide.RIGHT);

        rightObliqueUpBullet.makeMove(1, 1024, 1024);

        assertTrue(rightObliqueUpBullet.getXPos() > -10);
        assertTrue(rightObliqueUpBullet.getYPos() != 0);

    }

    @Test
    public void destroyBulletOnScreenBorderTest() {

        int R1 = 1;

        Bullet leftSideBulletIn = new Bullet(R1, 10, 0, -R1, yWindowSize / 2, player, GunSide.LEFT);
        leftSideBulletIn.checkIfBulletIsInGameWindow(xWindowSize, yWindowSize);

        assertTrue(leftSideBulletIn.isInGameWindow());

        Bullet leftSideBulletOut = new Bullet(R1, 10, 0, -R1 - 1, yWindowSize / 2, player, GunSide.LEFT);
        leftSideBulletOut.checkIfBulletIsInGameWindow(xWindowSize, yWindowSize);

        assertTrue(!leftSideBulletOut.isInGameWindow());

        Bullet rightSideBulletIn = new Bullet(R1, 10, 0, xWindowSize + R1, yWindowSize / 2, player, GunSide.LEFT);
        rightSideBulletIn.checkIfBulletIsInGameWindow(xWindowSize, yWindowSize);

        assertTrue(rightSideBulletIn.isInGameWindow());

        Bullet rightSideBulletOut = new Bullet(R1, 10, 0, xWindowSize + R1 + 1, yWindowSize / 2, player, GunSide.LEFT);
        rightSideBulletOut.checkIfBulletIsInGameWindow(xWindowSize, yWindowSize);

        assertTrue(!rightSideBulletOut.isInGameWindow());

        Bullet upSideBulletIn = new Bullet(R1, 10, 0, xWindowSize / 2, -R1, player, GunSide.LEFT);
        upSideBulletIn.checkIfBulletIsInGameWindow(xWindowSize, yWindowSize);

        assertTrue(upSideBulletIn.isInGameWindow());

        Bullet upSideBulletOut = new Bullet(R1, 10, 0, xWindowSize / 2, -R1 - 1, player, GunSide.LEFT);
        upSideBulletOut.checkIfBulletIsInGameWindow(xWindowSize, yWindowSize);

        assertTrue(!upSideBulletOut.isInGameWindow());

        Bullet downSideBulletIn = new Bullet(R1, 10, 0, xWindowSize / 2, yWindowSize + R1, player, GunSide.LEFT);
        downSideBulletIn.checkIfBulletIsInGameWindow(xWindowSize, yWindowSize);

        assertTrue(downSideBulletIn.isInGameWindow());

        Bullet downSideBulletOut = new Bullet(R1, 10, 0, xWindowSize / 2, yWindowSize + R1 + 1, player, GunSide.LEFT);
        downSideBulletOut.checkIfBulletIsInGameWindow(xWindowSize, yWindowSize);

        assertTrue(!downSideBulletOut.isInGameWindow());
    }

    @Test
    public void hitBulletOnCellTest() {
        Bullet bullet = new Bullet(10, 10, 0, xWindowSize / 2, yWindowSize / 2, player, GunSide.LEFT);
        List<Cell> cellList = new ArrayList<>();

        Cell cell = new Cell(9, 10, 0, xWindowSize / 2, yWindowSize / 2);
        cellList.add(cell);
        bullet.hitCell(cellList);

        assertEquals(8, cell.getP1());
        assertTrue(!bullet.isInGameWindow());

        cell.addP1();
        bullet = new Bullet(10, 10, 0, xWindowSize / 2 - 14, yWindowSize / 2, player, GunSide.LEFT);

        bullet.hitCell(cellList);

        assertEquals(8, cell.getP1());
        assertTrue(!bullet.isInGameWindow());

        cell.addP1();
        bullet = new Bullet(10, 10, 0, xWindowSize / 2 - 15, yWindowSize / 2, player, GunSide.LEFT);

        bullet.hitCell(cellList);

        assertEquals(9, cell.getP1());
        assertTrue(bullet.isInGameWindow());

        cell.addP1();
        bullet = new Bullet(10, 10, 0, xWindowSize / 2 + 14, yWindowSize / 2, player, GunSide.LEFT);

        bullet.hitCell(cellList);

        assertEquals(8, cell.getP1());
        assertTrue(!bullet.isInGameWindow());

        cell.addP1();
        bullet = new Bullet(10, 10, 0, xWindowSize / 2 + 15, yWindowSize / 2, player, GunSide.LEFT);

        bullet.hitCell(cellList);

        assertEquals(9, cell.getP1());
        assertTrue(bullet.isInGameWindow());

        cell.addP1();
        bullet = new Bullet(10, 10, 0, xWindowSize / 2, yWindowSize / 2 - 14, player, GunSide.LEFT);

        bullet.hitCell(cellList);

        assertEquals(8, cell.getP1());
        assertTrue(!bullet.isInGameWindow());

        cell.addP1();
        bullet = new Bullet(10, 10, 0, xWindowSize / 2, yWindowSize / 2 - 15, player, GunSide.LEFT);

        bullet.hitCell(cellList);

        assertEquals(9, cell.getP1());
        assertTrue(bullet.isInGameWindow());

        cell.addP1();
        bullet = new Bullet(10, 10, 0, xWindowSize / 2, yWindowSize / 2 + 14, player, GunSide.LEFT);

        bullet.hitCell(cellList);

        assertEquals(8, cell.getP1());
        assertTrue(!bullet.isInGameWindow());

        cell.addP1();
        bullet = new Bullet(10, 10, 0, xWindowSize / 2, yWindowSize / 2 + 15, player, GunSide.LEFT);

        bullet.hitCell(cellList);

        assertEquals(9, cell.getP1());
        assertTrue(bullet.isInGameWindow());

        cell.addP1();
        bullet = new Bullet(10, 10, 0, xWindowSize / 2 - 14, yWindowSize / 2 - 14, player, GunSide.LEFT);

        bullet.hitCell(cellList);

        assertEquals(9, cell.getP1());
        assertTrue(bullet.isInGameWindow());

        cell.addP1();
        bullet = new Bullet(10, 10, 0, xWindowSize / 2 - 14, yWindowSize / 2 + 14, player, GunSide.LEFT);

        bullet.hitCell(cellList);

        assertEquals(9, cell.getP1());
        assertTrue(bullet.isInGameWindow());

        cell.addP1();
        bullet = new Bullet(10, 10, 0, xWindowSize / 2 + 14, yWindowSize / 2 + 14, player, GunSide.LEFT);

        bullet.hitCell(cellList);

        assertEquals(9, cell.getP1());
        assertTrue(bullet.isInGameWindow());

        cell.addP1();
        bullet = new Bullet(10, 10, 0, xWindowSize / 2 + 14, yWindowSize / 2 - 14, player, GunSide.LEFT);

        bullet.hitCell(cellList);

        assertEquals(9, cell.getP1());
        assertTrue(bullet.isInGameWindow());

        while (cell.isAlive()) {
            bullet = new Bullet(10, 10, 0, xWindowSize / 2, yWindowSize / 2, player, GunSide.LEFT);
            bullet.hitCell(cellList);
        }

        assertTrue(!cell.isAlive());
        assertEquals(cell.getPointsForDestroy(), player.getPoints());
    }

    @Test
    public void hitBulletOnCellBombTest() {
        CellBomb cellBomb = new CellBomb(9, 9, 100, 9);

        Bullet bullet = new Bullet(10, 10, 0, xWindowSize / 2, yWindowSize - cellBomb.getYSize() + 10 / 2, player, GunSide.LEFT);
        bullet.hitCell(cellBomb, xWindowSize, yWindowSize);

        assertEquals(8, cellBomb.getP1());
        assertTrue(!bullet.isInGameWindow());

        cellBomb.addP1();

        bullet = new Bullet(10, 10, 0, xWindowSize / 2, yWindowSize - cellBomb.getYSize() + 11, player, GunSide.LEFT);
        bullet.hitCell(cellBomb, xWindowSize, yWindowSize);

        assertEquals(9, cellBomb.getP1());
        assertTrue(!bullet.isInGameWindow());

        cellBomb.addP1();

        bullet = new Bullet(10, 10, 0, xWindowSize / 2 - 59, yWindowSize - cellBomb.getYSize() + 9 / 2, player, GunSide.LEFT);
        bullet.hitCell(cellBomb, xWindowSize, yWindowSize);

        assertEquals(8, cellBomb.getP1());
        assertTrue(!bullet.isInGameWindow());

        cellBomb.addP1();

        bullet = new Bullet(10, 10, 0, xWindowSize / 2 - 60, yWindowSize - cellBomb.getYSize() + 9 / 2, player, GunSide.LEFT);
        bullet.hitCell(cellBomb, xWindowSize, yWindowSize);

        assertEquals(9, cellBomb.getP1());
        assertTrue(!bullet.isInGameWindow());

        cellBomb.addP1();

        bullet = new Bullet(10, 10, 0, xWindowSize / 2 + 58, yWindowSize - cellBomb.getYSize() + 9 / 2, player, GunSide.LEFT);
        bullet.hitCell(cellBomb, xWindowSize, yWindowSize);

        assertEquals(8, cellBomb.getP1());
        assertTrue(!bullet.isInGameWindow());

        cellBomb.addP1();

        bullet = new Bullet(10, 10, 0, xWindowSize / 2 + 59, yWindowSize - cellBomb.getYSize() + 9 / 2, player, GunSide.LEFT);
        bullet.hitCell(cellBomb, xWindowSize, yWindowSize);

        assertEquals(8, cellBomb.getP1());
        assertTrue(!bullet.isInGameWindow());

        cellBomb.addP1();

        bullet = new Bullet(10, 10, 0, xWindowSize / 2 - 59, yWindowSize - cellBomb.getYSize() + 10, player, GunSide.LEFT);
        bullet.hitCell(cellBomb, xWindowSize, yWindowSize);

        assertEquals(9, cellBomb.getP1());
        assertTrue(!bullet.isInGameWindow());

        cellBomb.addP1();

        bullet = new Bullet(10, 10, 0, xWindowSize / 2 - 60, yWindowSize - cellBomb.getYSize() + 10, player, GunSide.LEFT);
        bullet.hitCell(cellBomb, xWindowSize, yWindowSize);

        assertEquals(9, cellBomb.getP1());
        assertTrue(!bullet.isInGameWindow());

        cellBomb.addP1();

        bullet = new Bullet(10, 10, 0, xWindowSize / 2 + 59, yWindowSize - cellBomb.getYSize() + 11, player, GunSide.LEFT);
        bullet.hitCell(cellBomb, xWindowSize, yWindowSize);

        assertEquals(9, cellBomb.getP1());
        assertTrue(!bullet.isInGameWindow());

        cellBomb.addP1();

        bullet = new Bullet(10, 10, 0, xWindowSize / 2 + 60, yWindowSize - cellBomb.getYSize() + 11, player, GunSide.LEFT);
        bullet.hitCell(cellBomb, xWindowSize, yWindowSize);

        assertEquals(9, cellBomb.getP1());
        assertTrue(!bullet.isInGameWindow());

        cellBomb.addP1();

        while (cellBomb.isAlive()) {
            bullet = new Bullet(10, 10, 0, xWindowSize / 2, yWindowSize - cellBomb.getYSize() + 10 / 2, player, GunSide.LEFT);
            bullet.hitCell(cellBomb, xWindowSize, yWindowSize);
        }
        assertTrue(!cellBomb.isAlive());
        assertTrue(!bullet.isInGameWindow());

        assertEquals(cellBomb.getPointsForDestroy(), player.getPoints());

    }

    @Test
    public void changeR1Test() {
        Bullet bullet = new Bullet(10, 10, 0, 0, 0, player, GunSide.LEFT);

        assertEquals(10, bullet.getSize());

        bullet.changeSize(100);
        assertEquals(100, bullet.getSize());

    }

    @Test
    public void changeV1Test() {
        Bullet bullet = new Bullet(10, 10, 0, 0, 0, player, GunSide.LEFT);

        assertEquals(0, bullet.getXPos());

        bullet.makeMove(1, xWindowSize, yWindowSize);

        assertEquals(10, bullet.getXPos());

        bullet.changeV1(20);

        bullet.makeMove(1, xWindowSize, yWindowSize);

        assertEquals(30, bullet.getXPos());
    }
}
