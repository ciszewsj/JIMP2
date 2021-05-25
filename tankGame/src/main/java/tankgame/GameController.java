package tankgame;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameController extends KeyAdapter {

    private final double FPS;
    private final double toSecond;
    private final double toTimer;

    private int windowSize;
    private int playerDistance;
    
    private final int maxPoints;

    private double T1Timer;
    private double T2Timer;
    private double T3Timer;
    private double T4Timer;

    private boolean gameIsEnd;

    private double nextCellSpawnTime;

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

    private final GameRules gameRules;

    public GameController(ErrorWindowController errorWindowController, GameRules gameRules) {
        FPS = (double) 1 / (double) 30;
        toSecond = 10;
        toTimer = 1000;
        
        windowSize = 1024;
        playerDistance = 100;
        
        cellBomb = new CellBomb(gameRules.getPKB(), gameRules.getPW(), gameRules.getH1());
        cellList = new ArrayList<>();
        bulletList = new ArrayList<>();

        this.errorWindowController = errorWindowController;
        this.gameRules = gameRules;

        rightPlayer = new Player(windowSize - playerDistance, windowSize / 2, 0, GunSide.RIGHT, gameRules.getPC(), gameRules.getPD(), gameRules.getX1(), bulletList);
        leftPlayer = new Player(playerDistance, windowSize / 2, 0, GunSide.LEFT, gameRules.getPC(), gameRules.getPD(), gameRules.getX1(), bulletList);

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

        maxPoints = 999;

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
        String text;
        String fileSavePath;

        SaveGameWindow saveGameWindow;

        gameWindow.refreshWindow(gameRules.getT3() - T3Timer);
        gameIsEnd = true;
        if (rightPlayer.getPoints() > leftPlayer.getPoints()) {
            text = "Gracz prawy wygrał!";
        } else if (rightPlayer.getPoints() < leftPlayer.getPoints()) {
            text = "Gracz lewy wygrał!";
        } else {
            text = "Mecz zakończył się obustronnym walkowerem!";
        }
        WinPlayerWindow winPlayerWindow = new WinPlayerWindow(gameWindow, text);
        if (winPlayerWindow.getToSave() == true) {
            saveGameWindow = new SaveGameWindow(320, 240);
            EventQueue.invokeLater(()
                    -> {
                saveGameWindow.setVisible(true);
            });
            while (saveGameWindow.isClosed() != true) {
                if (saveGameWindow.isReadyToSave() == true) {
                    fileSavePath = saveGameWindow.getFilePath();
                    try {
                        gameWindow.saveGameWindow(fileSavePath);
                        saveGameWindow.succeededSave(fileSavePath);
                    } catch (FileAlreadyExistsException e) {
                        saveGameWindow.unsucceededSave();
                        errorWindowController.addErrorFileAlreadyExists(fileSavePath);
                    } catch (FileNotFoundException | NullPointerException e) {
                        saveGameWindow.unsucceededSave();
                        errorWindowController.addErrorFileSave(fileSavePath);
                    } catch (IOException e) {
                        saveGameWindow.unsucceededSave();
                        errorWindowController.addErrorFileSave(fileSavePath);
                    }
                }
            }
            saveGameWindow.dispose();

        }
        gameWindow.dispose();

    }

    public void makeMove() {

        int timeToSleep = (int) ((double) 100 * FPS);
        while (gameIsEnd == false) {
            try {
                for (Iterator<Cell> it = cellList.iterator(); it.hasNext();) {
                    Cell c = it.next();
                    c.moveCell(FPS / toSecond, windowSize);
                    if (c.getP1() <= 0) {
                        it.remove();
                    }
                }

                for (Iterator<Bullet> it = bulletList.iterator(); it.hasNext();) {
                    Bullet b = it.next();
                    b.hitCell(cellList);
                    b.hitCell(cellBomb, windowSize, windowSize);
                    b.makeMove(FPS / toSecond, windowSize, windowSize);
                    if (b.isInGameWindow() == false) {
                        it.remove();
                    }
                }

                moveTanks();
                if (cellBomb.getP1() <= 0) {
                    endGame();
                }

                if (leftPlayer.ifToManyPoints(maxPoints) == true) {
                    errorWindowController.addErrorMessagePlayerPointError("left");
                    endGame();
                }
                if (rightPlayer.ifToManyPoints(maxPoints) == true) {
                    errorWindowController.addErrorMessagePlayerPointError("right");
                    endGame();
                }

                timeAction(timeToSleep);
                sleep(timeToSleep);
                gameWindow.refreshWindow(gameRules.getT3() - T3Timer);
            } catch (InterruptedException ex) {
                gameIsEnd = true;
                errorWindowController.addErrorMessage("Wystąpił wewnętrzny błąd gry.");
            }
        }
    }

    private void timeAction(int timeToSleep) {
        T1Timer += (double) timeToSleep / toTimer;
        T2Timer += (double) timeToSleep / toTimer;
        T3Timer += (double) timeToSleep / toTimer;
        T4Timer += (double) timeToSleep / toTimer;
        if (T1Timer > gameRules.getT1()) {
            T1Timer = 0;
            gameRules.makeT1();

            for (Iterator<Cell> it = cellList.iterator(); it.hasNext();) {
                Cell c = it.next();
                c.changeV2(gameRules.getV2());
                c.changeH1(gameRules.getH1());
            }

            for (Iterator<Bullet> it = bulletList.iterator(); it.hasNext();) {
                Bullet b = it.next();
                b.changeSize(gameRules.getR1());
                b.changeV1(gameRules.getV1());
            }

            cellBomb.changeH1(gameRules.getH1());

        }
        if (T2Timer > gameRules.getT2()) {
            T2Timer = 0;
            for (Iterator<Cell> it = cellList.iterator(); it.hasNext();) {
                Cell c = it.next();
                c.addP1();
            }
            cellBomb.addP1();
        }
        if (T3Timer > gameRules.getT3()) {
            endGame();
        }
        if (T4Timer > nextCellSpawnTime) {
            generateNextTimeCellSpawn();
            T4Timer = 0;

        }
    }

    private void generateNextTimeCellSpawn() {
        CellGenerator.generateCellStructure(cellList, gameRules.getH1(), gameRules.getV2(), windowSize / 2);
        nextCellSpawnTime = 4 * (double) gameRules.getH1() / gameRules.getV2();
    }

    private void moveTanks() {

        if (leftPlayerUp.isPressed() == true) {
            leftPlayer.movePlayer(true, FPS / toSecond);
        }

        if (leftPlayerDown.isPressed() == true) {
            leftPlayer.movePlayer(false, FPS / toSecond);
        }

        if (leftPlayerGunUp.isPressed() == true) {
            leftPlayer.elevateGun(true, FPS / toSecond);
        }

        if (leftPlayerGunDown.isPressed() == true) {
            leftPlayer.elevateGun(false, FPS / toSecond);
        }

        if (leftPlayerShootController.isReadyToShoot() == true) {
            leftPlayer.shotBullet(gameRules.getR1(), gameRules.getV1());
        }

        if (rightPlayerUp.isPressed() == true) {
            rightPlayer.movePlayer(true, FPS / toSecond);
        }

        if (rightPlayerDown.isPressed() == true) {
            rightPlayer.movePlayer(false, FPS / toSecond);
        }

        if (rightPlayerGunUp.isPressed() == true) {
            rightPlayer.elevateGun(true, FPS / toSecond);
        }

        if (rightPlayerGunDown.isPressed() == true) {
            rightPlayer.elevateGun(false, FPS / toSecond);
        }

        if (rightPlayerShootController.isReadyToShoot() == true) {
            rightPlayer.shotBullet(gameRules.getR1(), gameRules.getV1());
        }

    }
}
