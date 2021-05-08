package tankgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter {

    private GameController gameController;

    private int button;
    private boolean pressed;

    public KeyController(GameController gameController, int button) {
        this.button = button;
        this.gameController = gameController;
        this.pressed = false;

    }

    @Override
    public void keyPressed(KeyEvent evt) {

        if (evt.getKeyChar() == button) {
            pressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent evt) {
        if (evt.getKeyChar() == button) {
            System.err.println("false");
            pressed = false;
        }
    }

    public boolean isPressed() {
        return pressed;
    }
}
