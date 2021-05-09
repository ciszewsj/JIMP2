package tankgame;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameController extends KeyAdapter {

    private final double FPS;

    private Player rightPlayer;
    private Player leftPlayer;

    private CellBomb cellBomb;

    private List<Cell> cellList;

    private List<Bullet> bulletList;

    private KeyController rightPlayerUp;
    private KeyController rightPlayerDown;
    private KeyController rightPlayerGunUp;
    private KeyController rightPlayerGunDown;

    private ShootKeyController leftPlayerShootController;

    private KeyController leftPlayerUp;
    private KeyController leftPlayerDown;
    private KeyController leftPlayerGunUp;
    private KeyController leftPlayerGunDown;

    private ShootKeyController rightPlayerShootController;

    private GameWindow gameWindow;

    public GameController() {
        FPS = (double) 1 / (double) 30;
        cellBomb = new CellBomb(9, 9, 100);
        cellList = new ArrayList<>();
        bulletList = new ArrayList<>();

        rightPlayer = new Player(1024 - 100, 1024 / 2, 0, GunSide.RIGHT, 10, 10, 10, bulletList);

        leftPlayer = new Player(100, 1024 / 2, 0, GunSide.LEFT, 10, 10, 10, bulletList);

        leftPlayerUp = new KeyController(87);
        leftPlayerDown = new KeyController(83);
        leftPlayerGunUp = new KeyController(65);
        leftPlayerGunDown = new KeyController(68);

        leftPlayerShootController = new ShootKeyController(88);

        rightPlayerUp = new KeyController(38);
        rightPlayerDown = new KeyController(40);
        rightPlayerGunUp = new KeyController(37);
        rightPlayerGunDown = new KeyController(39);

        rightPlayerShootController = new ShootKeyController(17);

        gameWindow = new GameWindow(rightPlayer, leftPlayer, cellBomb, cellList, bulletList, rightPlayerUp, rightPlayerDown, rightPlayerGunUp, rightPlayerGunDown, leftPlayerUp, leftPlayerDown, leftPlayerGunUp, leftPlayerGunDown, rightPlayerShootController, leftPlayerShootController);

    }

    public void initGame() {
        EventQueue.invokeLater(()
                -> {
            gameWindow.setVisible(true);
        });
    }

    public void endGame() {

    }

    public void makeMove() {

        int timeToSleep = (int) ((double) 100 * FPS);
        while (true) {
            try {
                gameWindow.refreshWindow();

                for (Cell c : cellList) {
                    c.moveCell(FPS);
                }

                for (Iterator<Bullet> it = bulletList.iterator(); it.hasNext();) {
                    Bullet b = it.next();
                    b.hitCell(cellList);
                    b.hitCell(cellBomb, 1024, 1024);
                    b.makeMove(FPS, 1024, 1024);
                    if (b.isInGameWindow() == false) {
                        it.remove(); //metoda remove() iteratora
                    }
                }

                moveTanks();
                gameWindow.refreshWindow();
                sleep(timeToSleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void moveTanks() {

        if (leftPlayerUp.isPressed() == true) {
            leftPlayer.movePlayer(true, FPS);
        }

        if (leftPlayerDown.isPressed() == true) {
            leftPlayer.movePlayer(false, FPS);
        }

        if (leftPlayerGunUp.isPressed() == true) {
            leftPlayer.elevateGun(true, FPS);
        }

        if (leftPlayerGunDown.isPressed() == true) {
            leftPlayer.elevateGun(false, FPS);
        }

        if (leftPlayerShootController.isReadyToShoot() == true) {
            leftPlayer.shotBullet();
        }

        if (rightPlayerUp.isPressed() == true) {
            rightPlayer.movePlayer(true, FPS);
        }

        if (rightPlayerDown.isPressed() == true) {
            rightPlayer.movePlayer(false, FPS);
        }

        if (rightPlayerGunUp.isPressed() == true) {
            rightPlayer.elevateGun(true, FPS);
        }

        if (rightPlayerGunDown.isPressed() == true) {
            rightPlayer.elevateGun(false, FPS);
        }

        if (rightPlayerShootController.isReadyToShoot() == true) {
            rightPlayer.shotBullet();
        }

    }
}
