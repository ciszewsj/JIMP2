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
import tankgame.GunSide;
import tankgame.Player;

public class PlayerTest {

    private Player player;
    private List<Bullet> bulletList;

    private int xSize;
    private int ySize;

    public PlayerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        bulletList = new ArrayList<>();
        xSize = 1024;
        ySize = 1024;
        player = new Player(xSize / 2, ySize / 2, 0, GunSide.LEFT, 10, 10, 10, 100, 0, bulletList, ySize, 100);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void movePlayerTest() {

        assertEquals(xSize / 2, player.getXPos());
        assertEquals(ySize / 2, player.getYPos());

        player.movePlayer(true, 1);

        assertEquals(xSize / 2, player.getXPos());
        assertEquals(ySize / 2 - 10, player.getYPos());

        player.movePlayer(false, 1);

        assertEquals(xSize / 2, player.getXPos());
        assertEquals(ySize / 2, player.getYPos());

        player.movePlayer(true, 1000);
        assertEquals(xSize / 2, player.getXPos());
        assertEquals(150, player.getYPos());

        player.movePlayer(false, 1000);
        assertEquals(xSize / 2, player.getXPos());
        assertEquals(ySize - 150, player.getYPos());
    }

    @Test
    public void rotateGunTest() {
        assertEquals(0, player.getGunRotation(), 0);

        player.elevateGun(true, 1);

        assertTrue(player.getGunRotation() < 0);

        player.elevateGun(false, 2);

        assertTrue(player.getGunRotation() > 0);

        player.elevateGun(true, 100);

        assertEquals(-(double) 30 / (double) 180 * Math.PI, player.getGunRotation(), 0);

        player.elevateGun(false, 100);

        assertEquals((double) 30 / (double) 180 * Math.PI, player.getGunRotation(), 0);
    }

    @Test
    public void shotBulletTest() {
        player.shotBullet(10, 10);
        assertEquals(1, bulletList.size());

        assertEquals(10, bulletList.get(0).getSize());

        for (int i = 0; i < 100; i++) {
            player.shotBullet(10, 10);
        }

        assertEquals(10, Bullet.countOfPlayerBullet(player, bulletList));
    }

    @Test
    public void addPointsTest() {
        assertEquals(0, player.getPoints());

        player.addPoint(10);

        assertEquals(10, player.getPoints());

        player.addPoint(30);

        assertEquals(40, player.getPoints());
    }
}
