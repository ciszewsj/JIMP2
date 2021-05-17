package tankgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter {

    private final int button;
    private boolean pressed;

    public KeyController(int button) {
        this.button = button;
        this.pressed = false;

    }

    @Override
    public void keyPressed(KeyEvent evt) {

        if (pressed == false) {
            if (evt.getKeyCode() == button) {
                pressed = true;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent evt) {
        if (pressed == true) {
            if (evt.getKeyCode() == button) {
                pressed = false;
            }
        }
    }

    public void changePressStatus() {
        pressed = false;
    }

    public boolean isPressed() {
        return pressed;
    }

    public int getButton() {
        return button;
    }
}
