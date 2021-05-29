package tankgame;

public class TankGame {

    public static void main(String[] args) {
        while (true) {
            ErrorWindowController errorWindowController = new ErrorWindowController(360, 1024);
            
            GameOptionWindow gameOptionWindow = new GameOptionWindow();
            synchronized (gameOptionWindow.lock) {
                try {
                    gameOptionWindow.lock.wait();
                } catch (InterruptedException e) {
                }
            }
            GameRules gameRules = new GameRules();
            GameController gameController = new GameController(errorWindowController, gameRules);
            gameController.initGame();
            gameController.makeMove();
        }
    }

}
