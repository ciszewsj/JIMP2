package tankgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter {

    private int button;
    private boolean pressed;

    public KeyController(int button) {
        this.button = button;
        this.pressed = false;

    }

    @Override
    public void keyPressed(KeyEvent evt) {

        
        if (pressed == false) {
            if (evt.getKeyChar() == button) {
                System.out.println(evt.getKeyChar());
                pressed = true;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent evt) {
        if (pressed == true) {
            if (evt.getKeyChar() == button) {
                pressed = false;
            }
        }
    }

    public boolean isPressed() {
        return pressed;
    }
}
