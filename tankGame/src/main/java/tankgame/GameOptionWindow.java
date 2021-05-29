package tankgame;

import java.awt.Button;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GameOptionWindow {

    public static final Object lock = new Object();
    public static JFrame frame;

    private GameRules gameRules;

    public GameOptionWindow(GameRules gameRules) {
        frame = new JFrame("TankGame");
        GameOption.frame = frame;
        frame.setPreferredSize(new Dimension(1000, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createComponentsMenu(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);

        this.gameRules = gameRules;
    }

    public void createComponentsMenu(Container container) {
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(30, 30, 30, 30);
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;

        JScrollPane textArea = new JScrollPane();
        textArea.setViewportView(ScrollPanel());
        textArea.setPreferredSize(new Dimension(10, 100));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        container.add(textArea, constraints);

        TextField textField = new TextField("Path");
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        container.add(textField, constraints);

        Button save = new Button("Save");
        save.addActionListener((ActionEvent e) -> {
            GameOption.ReadFromFile(textField.getText(), gameRules);
            System.out.println("Udano");
            textArea.setViewportView(ScrollPanel());
            textArea.setPreferredSize(new Dimension(10, 100));
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 1;
            constraints.gridheight = 2;
            container.add(textArea, constraints);
        });

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        container.add(save, constraints);

        JButton start = new JButton("Start");
        start.addActionListener((ActionEvent e) -> {
            synchronized (lock) {
                lock.notify();
            }
            GameOptionWindow.frame.setVisible(false);
        });

        start.setBackground(Color.white);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        container.add(start, constraints);

    }

    public JTextArea ScrollPanel() {

        System.out.println("qwertyuiop" + Integer.toString(GameRules.getV1()));
        String TEXT = "V1 = " + Integer.toString(GameRules.getV1()) + "\n"
                + "V2 = " + Integer.toString(GameRules.getV2()) + "\n"
                + "X1 = " + Integer.toString(GameRules.getX1()) + "\n"
                + "R1 = " + Integer.toString(GameRules.getR1()) + "\n"
                + "H1 = " + Integer.toString(GameRules.getH1()) + "\n"
                + "PW = " + Integer.toString(GameRules.getPW()) + "\n"
                + "PC = " + Integer.toString(GameRules.getPC()) + "\n"
                + "PD = " + Integer.toString(GameRules.getPD()) + "\n"
                + "PKB = " + Integer.toString(GameRules.getPKB()) + "\n"
                + "T1 = " + Integer.toString(GameRules.getT1()) + "\n"
                + "DV1 = " + Integer.toString(GameRules.getDV1()) + "\n"
                + "DV2 = " + Integer.toString(GameRules.getDV2()) + "\n"
                + "DR1 = " + Integer.toString(GameRules.getDR1()) + "\n"
                + "DH1 = " + Integer.toString(GameRules.getDH1()) + "\n"
                + "T2 = " + Integer.toString(GameRules.getT2()) + "\n"
                + "T3 = " + Integer.toString(GameRules.getT3());
        JTextArea text = new JTextArea(10, 20);
        text.setText(TEXT);
        text.setEditable(false);
        return text;
    }
}
