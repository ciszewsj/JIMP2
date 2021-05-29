package tankgame;

import java.awt.Button;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GameWindow extends JFrame {

    private final int width;
    private final int height;

    private final GameCanvas gameCanvas;

    public GameWindow(Player rightPlayer, Player leftPlayer, CellBomb cellBomb, List<Cell> cellList, List<Bullet> bulletList, KeyController rightPlayerUp, KeyController rightPlayerDown, KeyController rightPlayerGunUp, KeyController rightPlayerGunDown, KeyController leftPlayerUp, KeyController leftPlayerDown, KeyController leftPlayerGunUp, KeyController leftPlayerGunDown, ShootKeyController rightPlayerShootController, ShootKeyController leftPlayerShootController, double timeToEndGame) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        width = 1024;
        height = 1024;
        int x = size.width / 2 - width / 2;
        int y = size.height / 2 - height / 2;
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("tankGame");
        setResizable(false);

        gameCanvas = new GameCanvas(rightPlayer, leftPlayer, cellBomb, cellList, bulletList, (int) timeToEndGame);
        JTextField jbutton = new JTextField();

        jbutton.addKeyListener(rightPlayerUp);
        jbutton.addKeyListener(rightPlayerDown);
        jbutton.addKeyListener(rightPlayerGunUp);
        jbutton.addKeyListener(rightPlayerGunDown);

        jbutton.addKeyListener(rightPlayerShootController);

        jbutton.addKeyListener(leftPlayerUp);
        jbutton.addKeyListener(leftPlayerDown);
        jbutton.addKeyListener(leftPlayerGunDown);
        jbutton.addKeyListener(leftPlayerGunUp);

        jbutton.addKeyListener(leftPlayerShootController);

        add(jbutton);
        add(gameCanvas);
    }

    public synchronized void refreshWindow(double timeToEndGame) {
        gameCanvas.updateFrame((int) timeToEndGame);
    }

    public synchronized void saveGameWindow(String filename) throws FileNotFoundException, NullPointerException, IOException, FileAlreadyExistsException {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        gameCanvas.paintComponent(img.getGraphics());
        try {
            File tmpDir = new File("/var/tmp");
            boolean exists = tmpDir.exists();
            if (exists == true) {
                ImageIO.write(img, "png", new File(filename + ".png"));
            } else {
                throw new FileAlreadyExistsException("Plik o podanej nazwie istnieje");
            }
        } catch (FileNotFoundException | NullPointerException e) {
            throw e;
        }
    }
}

class GameCanvas extends JComponent {

    private final int size;

    private final int xBarSize;
    private final int yBarSize;

    private final int xClockSize;
    private final int yClockSize;

    private final Player rightPlayer;
    private final Player leftPlayer;

    private final CellBomb cellBomb;

    private final List<Cell> cellList;
    private final List<Bullet> bulletList;

    private int timeToEndGame;

    private GameCanvas gameCanvas;

    public GameCanvas(Player rightPlayer, Player leftPlayer, CellBomb cellBomb, List<Cell> cellList, List<Bullet> bulletList, int timeToEndGame) {

        size = 1024;
        xBarSize = 200;
        yBarSize = 100;
        xClockSize = 300;
        yClockSize = 100;

        this.rightPlayer = rightPlayer;
        this.leftPlayer = leftPlayer;
        this.cellBomb = cellBomb;
        this.cellList = cellList;
        this.bulletList = bulletList;
        this.timeToEndGame = timeToEndGame;

    }

    public void updateFrame(int timeToEndGame) {
        this.timeToEndGame = timeToEndGame;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        drawBackground(g);
        drawBullets(g);
        drawCells(g);
        drawTanks(g);
        drawMenu(g);
        g.dispose();
    }

    private void drawBullets(Graphics g) {
        g.setColor(Color.CYAN);
        List<Bullet> copyOfBulletList = new ArrayList<>(bulletList);
        for (Bullet b : copyOfBulletList) {
            g.fillOval(b.getXPos() - b.getSize() / 2, b.getYPos() - b.getSize() / 2, b.getSize(), b.getSize());
        }
    }

    private void drawCells(Graphics g) {
        List<Cell> copyOfCells = new ArrayList<>(cellList);
        for (Cell c : copyOfCells) {
            switch (c.getP1()) {
                case 1:
                    g.setColor(Color.RED);
                    break;
                case 2:
                    g.setColor(Color.ORANGE);
                    break;
                case 3:
                    g.setColor(Color.YELLOW);
                    break;
                case 4:
                    g.setColor(Color.BLUE);
                    break;
                case 5:
                    g.setColor(Color.PINK);
                    break;
                case 6:
                    g.setColor(Color.LIGHT_GRAY);
                    break;
                case 7:
                    g.setColor(Color.GRAY);
                    break;
                case 8:
                    g.setColor(Color.DARK_GRAY);
                    break;
                case 9:
                    g.setColor(Color.GREEN);
                    break;
                default:
                    break;
            }
            g.fill3DRect(c.getXPos() - c.getH1() / 2, c.getYPos() - c.getH1() / 2, c.getH1(), c.getH1(), true);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(c.getP1()), c.getXPos() - g.getFont().getSize(), c.getYPos() - g.getFont().getSize());
        }
    }

    private void drawTanks(Graphics g) {
        g.setColor(Color.GREEN);

        g.fill3DRect(leftPlayer.getXPos() - leftPlayer.getTankSize() / 2, leftPlayer.getYPos() - leftPlayer.getTankSize() / 2, leftPlayer.getTankSize(), leftPlayer.getTankSize(), true);
        g.fill3DRect(rightPlayer.getXPos() - rightPlayer.getTankSize() / 2, rightPlayer.getYPos() - rightPlayer.getTankSize() / 2, rightPlayer.getTankSize(), rightPlayer.getTankSize(), true);

        g.setColor(Color.BLACK);

        g.drawLine(leftPlayer.getXPos(), leftPlayer.getYPos(), (int) (leftPlayer.getXPos() + 150 * Math.cos(leftPlayer.getGunRotation())), (int) (leftPlayer.getYPos() + 150 * Math.sin(leftPlayer.getGunRotation())));
        g.drawLine(rightPlayer.getXPos(), rightPlayer.getYPos(), (int) (rightPlayer.getXPos() - 150 * Math.cos(rightPlayer.getGunRotation())), (int) (rightPlayer.getYPos() - 150 * Math.sin(rightPlayer.getGunRotation())));

    }

    private void drawBackground(Graphics g) {
        g.setColor(new Color(93, 93, 93));
        g.fill3DRect(0, 0, size, size, true);
    }

    private void drawMenu(Graphics g) {

        g.setColor(Color.WHITE);

        g.fill3DRect((size - cellBomb.getXSize()) / 2, size - cellBomb.getYSize(), cellBomb.getXSize(), cellBomb.getYSize(), true);

        g.fill3DRect((size - xClockSize) / 2, 0, xClockSize, yClockSize, true);

        g.fill3DRect(0, 0, xBarSize, yBarSize, true);

        g.fill3DRect(size - xBarSize, 0, xBarSize, yBarSize, true);

        g.fill3DRect(0, size - yBarSize, xBarSize, yBarSize, true);

        g.fill3DRect(size - xBarSize, size - yBarSize, xBarSize, yBarSize, true);

        g.setColor(Color.BLACK);

        g.setFont(g.getFont().deriveFont(30f));

        g.drawLine((size - cellBomb.getXSize()) / 2, size, (size - cellBomb.getXSize()) / 2, size - cellBomb.getYSize());
        g.drawLine((size + cellBomb.getXSize()) / 2, size, (size + cellBomb.getXSize()) / 2, size - cellBomb.getYSize());
        g.drawLine((size - cellBomb.getXSize()) / 2, size - cellBomb.getYSize(), (size + cellBomb.getXSize()) / 2, size - cellBomb.getYSize());

        g.setColor(Color.RED);

        g.drawLine((size - cellBomb.getH1()) / 2, size - cellBomb.getYSize(), (size + cellBomb.getH1()) / 2, size - cellBomb.getYSize());

        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(cellBomb.getP1()), (size - (cellBomb.getXSize() + g.getFont().getSize()) / 2) / 2, size - (cellBomb.getYSize() + g.getFont().getSize()) / 2);

        g.drawLine((size - xClockSize) / 2, 0, (size - xClockSize) / 2, yClockSize);
        g.drawLine((size + xClockSize) / 2, 0, (size + xClockSize) / 2, yClockSize);
        g.drawLine((size - xClockSize) / 2, yClockSize, (size + xClockSize) / 2, yClockSize);

        g.drawString(convertSecondsToTime(timeToEndGame), (size - (g.getFont().getSize() * 3)) / 2, yClockSize / 2);

        g.drawLine(0, size - yBarSize, xBarSize, size - yBarSize);
        g.drawLine(xBarSize, size - yBarSize, xBarSize, size);

        g.drawString(String.valueOf(Bullet.countOfPlayerBullet(leftPlayer, bulletList)), (xBarSize - (g.getFont().getSize())) / 2, size - yBarSize / 2);

        g.drawLine(size, size - yBarSize, size - xBarSize, size - yBarSize);
        g.drawLine(size - xBarSize, size - yBarSize, size - xBarSize, size);

        g.drawString(String.valueOf(Bullet.countOfPlayerBullet(rightPlayer, bulletList)), size - (xBarSize + (g.getFont().getSize())) / 2, size - yBarSize / 2);

        g.drawLine(0, yBarSize, xBarSize, yBarSize);
        g.drawLine(xBarSize, yBarSize, xBarSize, 0);

        g.drawString(String.valueOf(leftPlayer.getPoints()), (xBarSize - (g.getFont().getSize())) / 2, yBarSize / 2);

        g.drawLine(size, yBarSize, size - xBarSize, yBarSize);
        g.drawLine(size - xBarSize, yBarSize, size - xBarSize, 0);

        g.drawString(String.valueOf(rightPlayer.getPoints()), size - (xBarSize + (g.getFont().getSize())) / 2, yBarSize / 2);

    }

    private String convertSecondsToTime(int seconds) {
        String timeFormat = "";
        if (seconds > 0) {
            int minutes = seconds / 60;
            seconds = seconds - minutes * 60;

            if (minutes < 10) {
                timeFormat += 0;
            }
            timeFormat += String.valueOf(minutes) + ":";
            if (seconds < 10) {
                timeFormat += "0";
            }
            timeFormat += String.valueOf(seconds);
        } else {
            timeFormat = "00:00";
        }
        return timeFormat;
    }
}

class CreatePanelMenu {

    public static final Object lock = new Object();
    public static JFrame frame;

    public static void CreatePanelMenu() {
        frame = new JFrame("TankGame");
        GameOption.frame = frame;
        frame.setPreferredSize(new Dimension(1000, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createComponentsMenu(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    public static void createComponentsMenu(Container container) {
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
            GameOption.ReadFromFile(textField.getText());
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
            CreatePanelMenu.frame.setVisible(false);
        });

        start.setBackground(Color.white);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        container.add(start, constraints);

    }

    public static JTextArea ScrollPanel() {

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
