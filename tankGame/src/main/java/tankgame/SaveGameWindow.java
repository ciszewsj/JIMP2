package tankgame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SaveGameWindow extends JFrame {

    private boolean isClosed;
    private boolean isReadyToSave;
    private String filePath;
    private final JTextField pathField;

    public SaveGameWindow(int width, int height) {
        isClosed = false;
        isReadyToSave = false;
        pathField = new JTextField("Podaj ścieżkę do pliku");

        JButton confirmButton = new JButton("Zapisz");
        JButton closeButton = new JButton("Wyjdź");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

        closeButton.addActionListener((ae) -> {
            isClosed = true;
        });

        confirmButton.addActionListener((ae) -> {
            isReadyToSave = true;
        });

        JPanel p = new JPanel();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setTitle("SaveViewToFile");
        p.add(pathField);
        p.add(confirmButton);
        p.add(closeButton);
        setSize(width, height);
        add(p);
        pack();
    }

    public void succeededSave(String filename) {
        JOptionPane.showMessageDialog(this,
                "Zapisano planszę do pliku: " + filename);
        closeWindow();
    }

    public void unsucceededSave() {
        JOptionPane.showMessageDialog(this,
                "Nie udało zapisać się planszy do tego pliku.");
    }

    public synchronized boolean isReadyToSave() {
        return isReadyToSave;
    }

    private void closeWindow() {
        isClosed = true;
    }

    public synchronized boolean isClosed() {
        return isClosed;
    }

    public synchronized String getFilePath() {
        filePath = pathField.getText() + ".png";
        isReadyToSave = false;
        return filePath;
    }
}
