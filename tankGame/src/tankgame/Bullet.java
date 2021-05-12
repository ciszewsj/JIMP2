package tankgame;

import java.util.ArrayList;
import java.util.List;

public class Bullet {

    private double R1;
    private double V1;
    private final double moveAngle;
    private double xPos;
    private double yPos;

    private final Player player;

    private final GunSide side;

    private boolean isInGameWindow;

    public Bullet(double R1, double V1, double moveAngle, double xPos, double yPos, Player player, GunSide side) {
        this.R1 = R1;
        this.V1 = V1;
        this.moveAngle = moveAngle;
        this.xPos = xPos;
        this.yPos = yPos;
        this.side = side;

        this.player = player;

        this.isInGameWindow = true;
    }

    public GunSide getSide() {
        return side;
    }

    public void changeSize(double DR1) {
        R1 -= DR1;
    }

    public void changeV1(double DV1) {
        V1 -= DV1;
    }

    public void hitCell(List<Cell> cellList) {
        for (Cell c : cellList) {
            if (isInGameWindow == true) {
                if (xPos + R1 > c.getXPos() - c.getH1() / 2 && xPos - R1 < c.getXPos() + c.getH1() / 2) {
                    if (yPos + R1 > c.getYPos() - c.getH1() / 2 && yPos - R1 < c.getYPos() + c.getH1() / 2) {
                        c.destroyCell(player);
                        isInGameWindow = false;
                    }
                }
            }
        }
    }

    public void hitCell(CellBomb cellBomb, int xWindowSize, int yWindowSize) {
        if (isInGameWindow == true) {
            if (Math.abs(yPos - (yWindowSize - cellBomb.getSize())) < R1) {
                if (Math.abs(xPos - (1024 - cellBomb.getH1()) / 2) < R1 + cellBomb.getH1() && Math.abs(xPos - (1024 + cellBomb.getH1()) / 2) < R1 + cellBomb.getH1()) {
                    cellBomb.destroyCell(player);
                    isInGameWindow = false;
                }
            }
            if (yPos - R1 >= 1024 - cellBomb.getSize()) {
                if (Math.abs(xPos - (1024 - cellBomb.getSize()) / 2) < R1 + cellBomb.getSize() && Math.abs(xPos - (1024 + cellBomb.getSize()) / 2) < R1 + cellBomb.getSize()) {
                    {
                        isInGameWindow = false;
                    }
                }
            }
        }
    }

    public void makeMove(double deltaTime, int xWindowSize, int yWindowSize) {

        if (side.equals(GunSide.LEFT)) {
            xPos += V1 * deltaTime * Math.cos(moveAngle);
            yPos += V1 * deltaTime * Math.sin(moveAngle);
        } else if (side.equals(GunSide.RIGHT)) {
            xPos -= V1 * deltaTime * Math.cos(moveAngle);
            yPos -= V1 * deltaTime * Math.sin(moveAngle);
        }

        if (xPos + R1 < 0 || xPos - R1 > xWindowSize || yPos + R1 < 0 || yPos - R1 > yWindowSize) {
            isInGameWindow = false;
        }
    }

    public boolean isInGameWindow() {
        return isInGameWindow;
    }

    public int getSize() {
        return (int) R1;
    }

    public int getXPos() {
        return (int) xPos;
    }

    public int getYPos() {
        return (int) yPos;
    }

    public static int countOfPlayerBullet(Player player, List<Bullet> bulletList) {
        int nOfPlayerBullet = 0;
        List<Bullet> copyOfBulletList = new ArrayList<>(bulletList);
        nOfPlayerBullet = copyOfBulletList.stream().filter((b) -> (b.getSide().equals(player.getSide()))).map((_item) -> 1).reduce(nOfPlayerBullet, Integer::sum);
        return nOfPlayerBullet;
    }
}
