package tankgame;

public class TankGame {

    public static void main(String[] args) {
        ErrorWindowController errorWindowController = new ErrorWindowController(360, 1024);
        GameController gameController = new GameController(errorWindowController);
        gameController.initGame();
        gameController.makeMove();
    }

}
