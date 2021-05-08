package tankgame;

import java.awt.event.KeyEvent;

public class ShootKeyController extends KeyController {

    private boolean isFire;

    public ShootKeyController(int button) {
        super(button);
        isFire = false;
    }

    public boolean isReadyToShoot() {
        if (isFire == false) {
            isFire = true;
            return true;
        }
        return false;
    }

    @Override
    public void keyReleased(KeyEvent evt) {
        if (isPressed() == true) {
            if (evt.getKeyCode() == getButton()) {
                changePressStatus();
                isFire = false;
            }
        }
    }

}
