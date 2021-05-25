package tankgame;

public class TankGame {

    public static void main(String[] args) {
        ErrorWindowController errorWindowController = new ErrorWindowController(360, 1024);
        while (true) {
            GameRules gameRules = new GameRules();
            GameController gameController = new GameController(errorWindowController, gameRules);
            gameController.initGame();
            gameController.makeMove();
        }
    }

}
