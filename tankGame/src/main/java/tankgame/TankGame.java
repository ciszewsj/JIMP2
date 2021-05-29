package tankgame;

public class TankGame {

    public static void main(String[] args)  {
        while (true) {
            ErrorWindowController errorWindowController = new ErrorWindowController(360, 1024);
            GameRules gameRules = new GameRules();
            GameOptionWindow gameOptionWindow = new GameOptionWindow(gameRules);
            synchronized (gameOptionWindow.lock) {
                try {
                    gameOptionWindow.lock.wait();
                } catch (InterruptedException e) {
                }
            }
            try{
            GameController gameController = new GameController(errorWindowController,(GameRules) gameRules.clone());
            gameController.initGame();
            gameController.makeMove();
        }
        catch(CloneNotSupportedException e)
        {
            errorWindowController.addErrorMessage("Nie udało zacząć się gry ---> Błąd wewnętrzny.");
        }
            }
    }

}
