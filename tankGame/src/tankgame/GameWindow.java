package tankgame;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GameWindow extends JFrame {

    private final int width;
    private final int height;

    private Player rightPlayer;
    private Player leftPlayer;

    private CellBomb cellBomb;

    private List<Cell> cellList;
    private List<Bullet> bulletList;

    private GameCanvas gameCanvas;

    public GameWindow(Player rightPlayer, Player leftPlayer, CellBomb cellBomb, List<Cell> cellList, List<Bullet> bulletList, GameController gameController) {
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
        setBounds(x, y, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("tankGame");
        setResizable(false);

        this.rightPlayer = rightPlayer;
        this.leftPlayer = leftPlayer;
        this.cellBomb = cellBomb;
        this.cellList = cellList;
        this.bulletList = bulletList;

        gameCanvas = new GameCanvas(rightPlayer, leftPlayer, cellBomb, cellList, bulletList);
        JTextField jbutton = new JTextField();
        jbutton.addKeyListener(new KeyController(gameController, KeyEvent.VK_0));
        jbutton.addKeyListener(new KeyController(gameController, 's'));
        add(jbutton);
        add(gameCanvas);
    }

    public void refreshWindow() {
        gameCanvas = new GameCanvas(rightPlayer, leftPlayer, cellBomb, cellList, bulletList);
        gameCanvas.updateFrame();
        add(gameCanvas);
    }

}

class GameCanvas extends JComponent {

    private Player rightPlayer;
    private Player leftPlayer;

    private CellBomb cellBomb;

    private List<Cell> cellList;
    private List<Bullet> bulletList;

    private GameCanvas gameCanvas;

    public GameCanvas(Player rightPlayer, Player leftPlayer, CellBomb cellBomb, List<Cell> cellList, List<Bullet> bulletList) {
        this.rightPlayer = rightPlayer;
        this.leftPlayer = leftPlayer;
        this.cellBomb = cellBomb;
        this.cellList = cellList;
        this.bulletList = bulletList;

    }

    public void updateFrame() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        drawTanks(g);
        drawMenu(g);
        g.dispose();
    }

    private void drawBullets(Graphics g) {
        g.setColor(Color.CYAN);
        for (Bullet b : bulletList) {
            g.fillOval(b.getXPos() - b.getSize() / 2, b.getYPos() - b.getSize() / 2, b.getSize(), b.getSize());
        }
    }

    private void drawCells(Graphics g) {
        for (Cell c : cellList) {
            if (c.getP1() == 1) {
                g.setColor(Color.RED);
            } else if (c.getP1() == 2) {
                g.setColor(Color.ORANGE);
            } else if (c.getP1() == 3) {
                g.setColor(Color.YELLOW);
            } else if (c.getP1() == 4) {
                g.setColor(Color.BLUE);
            } else if (c.getP1() == 5) {
                g.setColor(Color.PINK);
            } else if (c.getP1() == 6) {
                g.setColor(Color.LIGHT_GRAY);
            } else if (c.getP1() == 7) {
                g.setColor(Color.GRAY);
            } else if (c.getP1() == 8) {
                g.setColor(Color.DARK_GRAY);
            } else if (c.getP1() == 9) {
                g.setColor(Color.GREEN);
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

        g.drawString(String.valueOf(cellBomb.getP1()), (1024 - (100 + g.getFont().getSize()) / 2) / 2, 1024 - (100 + g.getFont().getSize()) / 2);

        g.drawLine((1024 - 300) / 2, 0, (1024 - 300) / 2, 100);
        g.drawLine((1024 + 300) / 2, 0, (1024 + 300) / 2, 100);
        g.drawLine((1024 - 300) / 2, 100, (1024 + 300) / 2, 100);

        g.drawString("00:00", (1024 - (g.getFont().getSize() * 3)) / 2, 100 / 2);

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
}
