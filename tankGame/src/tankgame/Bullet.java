package tankgame;

import java.util.List;

public class Bullet {

    private double R1;
    private double V1;
    private final double moveAngle;
    private double xPos;
    private double yPos;

    private final GunSide side;

    private boolean isInGameWindow;

    public Bullet(double R1, double V1, double moveAngle, double xPos, double yPos, GunSide side) {
        this.R1 = R1;
        this.V1 = V1;
        this.moveAngle = moveAngle;
        this.xPos = xPos;
        this.yPos = yPos;
        this.side = side;

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

    }

    public void hitCell(CellBomb cellBomb) {

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

    public static int countOfPlayerBullet(Player player, List<Bullet> bulletList) {
        int nOfPlayerBullet = 0;
        for (Bullet b : bulletList) {
            if (b.getSide().equals(player.getSide())) {
                nOfPlayerBullet++;
            }
        }
        return nOfPlayerBullet;
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
}
