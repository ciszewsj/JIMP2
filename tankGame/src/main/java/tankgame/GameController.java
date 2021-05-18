package tankgame;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameController extends KeyAdapter {

    private final double FPS;

    private double T1Timer;
    private double T2Timer;
    private double T3Timer;
    private double T4Timer;

    private boolean gameIsEnd;

    private int nextCellSpawnTime;

    private final Player rightPlayer;
    private final Player leftPlayer;

    private final CellBomb cellBomb;

    private final List<Cell> cellList;

    private final List<Bullet> bulletList;

    private final KeyController rightPlayerUp;
    private final KeyController rightPlayerDown;
    private final KeyController rightPlayerGunUp;
    private final KeyController rightPlayerGunDown;

    private final ShootKeyController leftPlayerShootController;

    private final KeyController leftPlayerUp;
    private final KeyController leftPlayerDown;
    private final KeyController leftPlayerGunUp;
    private final KeyController leftPlayerGunDown;

    private final ShootKeyController rightPlayerShootController;

    private final GameWindow gameWindow;

    private final ErrorWindowController errorWindowController;

    public GameController(ErrorWindowController errorWindowController) {
        FPS = (double) 1 / (double) 30;
        cellBomb = new CellBomb(9, 9, 100);
        cellList = new ArrayList<>();
        bulletList = new ArrayList<>();

        this.errorWindowController = errorWindowController;

        rightPlayer = new Player(1024 - 100, 1024 / 2, 0, GunSide.RIGHT, 100, 100, 10, bulletList);

        leftPlayer = new Player(100, 1024 / 2, 0, GunSide.LEFT, 100, 100, 10, bulletList);

        leftPlayerUp = new KeyController(87);
        leftPlayerDown = new KeyController(83);
        leftPlayerGunUp = new KeyController(65);
        leftPlayerGunDown = new KeyController(68);

        leftPlayerShootController = new ShootKeyController(88);

        rightPlayerUp = new KeyController(38);
        rightPlayerDown = new KeyController(40);
        rightPlayerGunUp = new KeyController(37);
        rightPlayerGunDown = new KeyController(39);

        rightPlayerShootController = new ShootKeyController(17);

        T1Timer = 0;
        T2Timer = 0;
        T3Timer = 0;
        T4Timer = 0;

        gameIsEnd = false;

        nextCellSpawnTime = 0;

        gameWindow = new GameWindow(rightPlayer, leftPlayer, cellBomb, cellList, bulletList, rightPlayerUp, rightPlayerDown, rightPlayerGunUp, rightPlayerGunDown, leftPlayerUp, leftPlayerDown, leftPlayerGunUp, leftPlayerGunDown, rightPlayerShootController, leftPlayerShootController, T3Timer);

    }

    public void initGame() {
        EventQueue.invokeLater(()
                -> {
            gameWindow.setVisible(true);
        });
    }

    private void endGame() {
        gameWindow.refreshWindow(T3Timer);
        gameIsEnd = true;
        String text;
        if (rightPlayer.getPoints() > leftPlayer.getPoints()) {
            text = "Gracz prawy wygrał!";
        } else if (rightPlayer.getPoints() < leftPlayer.getPoints()) {
            text = "Gracz lewy wygrał!";
        } else {
            text = "Mecz zakończył się obustronnym walkowerem!";
        }
        WinPlayerWindow winPlayerWindow = new WinPlayerWindow(gameWindow, text);
        if (winPlayerWindow.getToSave() == true) {
            gameWindow.saveGameWindow("");
        }
        gameWindow.dispose();

    }

    public void makeMove() {

        int timeToSleep = (int) ((double) 100 * FPS);
        while (gameIsEnd == false) {
            try {
                for (Iterator<Cell> it = cellList.iterator(); it.hasNext();) {
                    Cell c = it.next();
                    c.moveCell(FPS / 10, 1024);
                    if (c.getP1() <= 0) {
                        it.remove();
                    }
                }

                for (Iterator<Bullet> it = bulletList.iterator(); it.hasNext();) {
                    Bullet b = it.next();
                    b.hitCell(cellList);
                    b.hitCell(cellBomb, 1024, 1024);
                    b.makeMove(FPS / 10, 1024, 1024);
                    if (b.isInGameWindow() == false) {
                        it.remove();
                    }
                }

                moveTanks();
                if (cellBomb.getP1() <= 0) {
                    gameIsEnd = true;
                }

                if (leftPlayer.ifToManyPoints(999) == true) {
                    errorWindowController.addErrorMessagePlayerPointError("left");
                    endGame();
                }
                if (rightPlayer.ifToManyPoints(999) == true) {
                    errorWindowController.addErrorMessagePlayerPointError("right");
                    endGame();
                }

                timeAction(timeToSleep);
                sleep(timeToSleep);
                gameWindow.refreshWindow(T3Timer);
            } catch (InterruptedException ex) {
                gameIsEnd = true;
                errorWindowController.addErrorMessage("Wystąpił wewnętrzny błąd gry.");
            }
        }
    }

    private void timeAction(int timeToSleep) {
        T1Timer += (double) timeToSleep / 1000;
        T2Timer += (double) timeToSleep / 1000;
        T3Timer += (double) timeToSleep / 1000;
        T4Timer += (double) timeToSleep / 1000;
        if (T1Timer > 0) {
            T1Timer = 0;
        }
        if (T2Timer > 0);
        {
            T2Timer = 0;
        }
        if (T3Timer > 10) {
            endGame();
        }
        if (T4Timer > nextCellSpawnTime) {
            generateNextTimeCellSpawn();
            T4Timer = 0;
        }
    }

    private void generateNextTimeCellSpawn() {
        CellGenerator.generateCellStructure(cellList, 100, 100, 1024 / 2);
        nextCellSpawnTime = (int) (4 * 100 / 100);
    }

    private void moveTanks() {

        if (leftPlayerUp.isPressed() == true) {
            leftPlayer.movePlayer(true, FPS / 10);
        }

        if (leftPlayerDown.isPressed() == true) {
            leftPlayer.movePlayer(false, FPS / 10);
        }

        if (leftPlayerGunUp.isPressed() == true) {
            leftPlayer.elevateGun(true, FPS / 10);
        }

        if (leftPlayerGunDown.isPressed() == true) {
            leftPlayer.elevateGun(false, FPS / 10);
        }

        if (leftPlayerShootController.isReadyToShoot() == true) {
            leftPlayer.shotBullet(10, 100);
        }

        if (rightPlayerUp.isPressed() == true) {
            rightPlayer.movePlayer(true, FPS / 10);
        }

        if (rightPlayerDown.isPressed() == true) {
            rightPlayer.movePlayer(false, FPS / 10);
        }

        if (rightPlayerGunUp.isPressed() == true) {
            rightPlayer.elevateGun(true, FPS / 10);
        }

        if (rightPlayerGunDown.isPressed() == true) {
            rightPlayer.elevateGun(false, FPS / 10);
        }

        if (rightPlayerShootController.isReadyToShoot() == true) {
            rightPlayer.shotBullet(10, 100);
        }

    }
}
