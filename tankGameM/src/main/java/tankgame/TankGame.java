package tankgame;

public class TankGame {

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.initGame();
        gameController.makeMove();
    }

}
