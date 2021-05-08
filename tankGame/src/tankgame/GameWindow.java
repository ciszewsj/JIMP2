package tankgame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.List;
import javafx.scene.paint.Color;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class GameWindow extends JFrame {

    private final int width;
    private final int height;

    private Player rightPlayer;
    private Player leftPlayer;

    private CellBomb cellBomb;

    private List<Cell> cellList;
    private List<Bullet> bulletList;
    
    private GameCanvas gameCanvas;
    
    public GameWindow(Player rightPlayer, Player leftPlayer, CellBomb cellBomb, List<Cell> cellList, List<Bullet> bulletList) {
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
        
        gameCanvas = new GameCanvas();
    }

    public void refreshWindow() {
        GameCanvas gameCanvas = new GameCanvas();
        gameCanvas.updateFrame();
        add(gameCanvas);
    }

}

class GameCanvas extends JComponent {

    public GameCanvas() {

    }
    
    public void updateFrame()
    {
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        g.drawLine(0, 100, 1024, 1024);
        //g.dispose();
        
    }
}
