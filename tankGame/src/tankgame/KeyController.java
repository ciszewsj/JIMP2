package tankgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter{
    private int upRight;
    private int downRight;
    
    private GameController gameController;
    
    public KeyController(GameController gameController){
        upRight = 38;
        downRight = 40;
        this.gameController = gameController;
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        System.out.println("wciśnieto");
    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        
    }
}
