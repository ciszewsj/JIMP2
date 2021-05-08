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

    private final long FPS;

    private Player rightPlayer;
    private Player leftPlayer;

    private CellBomb cellBomb;

    private List<Cell> cellList;

    private List<Bullet> bulletList;

    private KeyController keyController;

    private GameWindow gameWindow;

    public GameController() {
        FPS = 1 / 30 * 1000;
        cellBomb = new CellBomb(9, 9, 9);
        cellList = new ArrayList<>();
        bulletList = new ArrayList<>();

        rightPlayer = new Player(1024 - 100, 1024 / 2, 0, GunSide.RIGHT, 0, 0, 0, bulletList);

        leftPlayer = new Player(100, 1024 / 2, 0, GunSide.LEFT, 0, 0, 0, bulletList);

        gameWindow = new GameWindow(rightPlayer, leftPlayer, cellBomb, cellList, bulletList, this);

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
                sleep(FPS);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
