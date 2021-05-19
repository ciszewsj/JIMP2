package tankgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
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

    private final Player rightPlayer;
    private final Player leftPlayer;

    private final CellBomb cellBomb;

    private final List<Cell> cellList;
    private final List<Bullet> bulletList;

    private int timeToEndGame;

    private GameCanvas gameCanvas;

    public GameCanvas(Player rightPlayer, Player leftPlayer, CellBomb cellBomb, List<Cell> cellList, List<Bullet> bulletList, int timeToEndGame) {
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

        g.fill3DRect(leftPlayer.getXPos() - 50, leftPlayer.getYPos() - 50, 100, 100, true);
        g.fill3DRect(rightPlayer.getXPos() - 50, rightPlayer.getYPos() - 50, 100, 100, true);

        g.setColor(Color.BLACK);

        g.drawLine(leftPlayer.getXPos(), leftPlayer.getYPos(), (int) (leftPlayer.getXPos() + 150 * Math.cos(leftPlayer.getGunRotation())), (int) (leftPlayer.getYPos() + 150 * Math.sin(leftPlayer.getGunRotation())));
        g.drawLine(rightPlayer.getXPos(), rightPlayer.getYPos(), (int) (rightPlayer.getXPos() - 150 * Math.cos(rightPlayer.getGunRotation())), (int) (rightPlayer.getYPos() - 150 * Math.sin(rightPlayer.getGunRotation())));

    }

    private void drawBackground(Graphics g) {
        g.setColor(new Color(93, 93, 93));
        g.fill3DRect(0, 0, 1024, 1024, true);
    }

    private void drawMenu(Graphics g) {

        g.setColor(Color.WHITE);

        g.fill3DRect((1024 - 100) / 2, 1024 - 100, 100, 100, true);

        g.fill3DRect((1024 - 300) / 2, 0, 300, 100, true);

        g.fill3DRect(0, 0, 200, 100, true);

        g.fill3DRect(1024 - 200, 0, 200, 100, true);

        g.fill3DRect(0, 1024 - 100, 200, 100, true);

        g.fill3DRect(1024 - 200, 1024 - 100, 200, 100, true);

        g.setColor(Color.BLACK);

        g.setFont(g.getFont().deriveFont(30f));

        g.drawLine((1024 - 100) / 2, 1024, (1024 - 100) / 2, 1024 - 100);
        g.drawLine((1024 + 100) / 2, 1024, (1024 + 100) / 2, 1024 - 100);
        g.drawLine((1024 - 100) / 2, 1024 - 100, (1024 + 100) / 2, 1024 - 100);

        g.setColor(Color.RED);

        g.drawLine((1024 - cellBomb.getH1()) / 2, 1024 - 100, (1024 + cellBomb.getH1()) / 2, 1024 - 100);

        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(cellBomb.getP1()), (1024 - (100 + g.getFont().getSize()) / 2) / 2, 1024 - (100 + g.getFont().getSize()) / 2);

        g.drawLine((1024 - 300) / 2, 0, (1024 - 300) / 2, 100);
        g.drawLine((1024 + 300) / 2, 0, (1024 + 300) / 2, 100);
        g.drawLine((1024 - 300) / 2, 100, (1024 + 300) / 2, 100);

        g.drawString(convertSecondsToTime(timeToEndGame), (1024 - (g.getFont().getSize() * 3)) / 2, 100 / 2);

        g.drawLine(0, 1024 - 100, 200, 1024 - 100);
        g.drawLine(200, 1024 - 100, 200, 1024);

        g.drawString(String.valueOf(Bullet.countOfPlayerBullet(leftPlayer, bulletList)), (200 - (g.getFont().getSize())) / 2, 1024 - 100 / 2);

        g.drawLine(1024, 1024 - 100, 1024 - 200, 1024 - 100);
        g.drawLine(1024 - 200, 1024 - 100, 1024 - 200, 1024);

        g.drawString(String.valueOf(Bullet.countOfPlayerBullet(rightPlayer, bulletList)), 1024 - (200 + (g.getFont().getSize())) / 2, 1024 - 100 / 2);

        g.drawLine(0, 100, 200, 100);
        g.drawLine(200, 100, 200, 0);

        g.drawString(String.valueOf(leftPlayer.getPoints()), (200 - (g.getFont().getSize())) / 2, 100 / 2);

        g.drawLine(1024, 100, 1024 - 200, 100);
        g.drawLine(1024 - 200, 100, 1024 - 200, 0);

        g.drawString(String.valueOf(rightPlayer.getPoints()), 1024 - (200 + (g.getFont().getSize())) / 2, 100 / 2);

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
