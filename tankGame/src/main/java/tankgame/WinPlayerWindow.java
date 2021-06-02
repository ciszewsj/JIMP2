package tankgame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WinPlayerWindow {

    private boolean toSave;

    public WinPlayerWindow(final JFrame frame, String text) {
        int result = JOptionPane.showConfirmDialog(frame, text + "\n" + "Czy chcesz zapisać wygląd planszy ?", "GameResults",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        toSave = result == JOptionPane.YES_OPTION;
    }

    public boolean getToSave() {
        return toSave;
    }

}
