package tankgame;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
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

    private KeyController leftPlayerUp;
    private KeyController leftPlayerDown;
    private KeyController leftPlayerGunUp;
    private KeyController leftPlayerGunDown;

    private GameWindow gameWindow;

    public GameController() {
        FPS = (double) 1 / (double) 30;
        cellBomb = new CellBomb(9, 9, 9);
        cellList = new ArrayList<>();
        bulletList = new ArrayList<>();

        rightPlayer = new Player(1024 - 100, 1024 / 2, 1, GunSide.RIGHT, 10, 10, 10, bulletList);

        leftPlayer = new Player(100, 1024 / 2, 0, GunSide.LEFT, 10, 10, 10, bulletList);

        leftPlayerUp = new KeyController('w');
        leftPlayerDown = new KeyController('s');
        leftPlayerGunUp = new KeyController('a');
        leftPlayerGunDown = new KeyController('d');

        gameWindow = new GameWindow(rightPlayer, leftPlayer, cellBomb, cellList, bulletList, rightPlayerUp, rightPlayerDown, rightPlayerGunUp, rightPlayerGunDown, leftPlayerUp, leftPlayerDown, leftPlayerGunUp, leftPlayerGunDown);

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
                for (Bullet b : bulletList) {
                    b.makeMove(FPS);
                    b.hitCell(cellList);
                    b.hitCell(cellBomb);
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
    }
}
