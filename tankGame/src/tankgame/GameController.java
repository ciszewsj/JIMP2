package tankgame;

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

    private int upRightPlayer;
    private int downRightPlayer;
    private int upGunRightPlayer;
    private int downGunRightPlayer;

    private KeyController keyController;
    
    private GameWindow gameWindow;
    
    public GameController() {
        FPS = 1 / 30 * 1000;
        cellBomb = new CellBomb(0, 0, 0);
        cellList = new ArrayList<>();
        bulletList = new ArrayList<>();

        upRightPlayer = 38;
        downRightPlayer = 40;
        
        keyController = new KeyController(this);
        
        gameWindow = new GameWindow(rightPlayer, leftPlayer, cellBomb, cellList, bulletList);
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
