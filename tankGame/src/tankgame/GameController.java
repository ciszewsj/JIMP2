package tankgame;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameController {

    private final long FPS;

    private Player rightPlayer;
    private Player leftPlayer;

    private CellBomb cellBomb;
    
    private List<Cell> cellList;
    
    private List<Bullet> bulletList;
    public GameController() {
        FPS = 1 / 30 * 1000;
        cellList = new ArrayList<>();
        bulletList = new ArrayList<>();
    }

    public void initGame() {
    }

    public void endGame() {

    }

    public void makeMove() {

        try {
            sleep(FPS);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
