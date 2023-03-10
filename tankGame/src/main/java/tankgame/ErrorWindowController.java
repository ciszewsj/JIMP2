package tankgame;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

public class ErrorWindowController {

    private final List<String> errorList;
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
        errorList.add("Gracz " + id + " zdobył za dużo punktów!");
        errorWindow.refreshWindow(errorList);
    }

    public void addErrorFileAlreadyExists(String filename) {
        errorList.add("Nie udało zapisać się planszy do pliku: " + filename + ". Plik istnieje.");
        errorWindow.refreshWindow(errorList);
    }

    public void addErrorFileSave(String filename) {
        errorList.add("Nie udało zapisać się planszy do pliku: " + filename + ".");
        errorWindow.refreshWindow(errorList);
    }

    public void addErrorFileRead(String filename) {
        errorList.add("Nie udało otworzyć pliku: " + filename + ".");
        errorWindow.refreshWindow(errorList);
    }

    public void addErrorParametrNotExists(String parametr) {
        errorList.add("Nie istnieje parametr: " + parametr + ".");
        errorWindow.refreshWindow(errorList);
    }

    public void addErrorParametrValue(String parametr, int min, int max) {
        errorList.add("Parametr: " + parametr + " ma nieprawidłową wartość. Prawidłowa wartość to liczba całkowita pomiędzy " + min + " i " + max + ".");
        errorWindow.refreshWindow(errorList);
    }
}
