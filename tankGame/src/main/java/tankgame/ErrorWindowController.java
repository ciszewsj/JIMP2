package tankgame;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

public class ErrorWindowController {

    private List<String> errorList;
    private ErrorWindow errorWindow;

    public ErrorWindowController(int width, int height) {
        errorList = new ArrayList<>();

        errorWindow = new ErrorWindow(width, height, errorList);
        EventQueue.invokeLater(()
                -> {
            errorWindow.setVisible(true);
        });
    }

    public void addErrorMessage(String message) {
        errorList.add(message);
        errorWindow.refreshWindow(errorList);
    }

    public void addErrorMessagePlayerPointError(String id) {
        errorList.add("Player " + id + " earn to much points!");
        errorWindow.refreshWindow(errorList);
    }
}
