package tankgame;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameController {

    public GameController() {

    }

    public void initGame() {

    }

    public void endGame() {

    }

    public void makeMove() {

        try {
            sleep(1 / 30);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
