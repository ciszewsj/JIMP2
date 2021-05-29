package tankgame;

import java.util.List;

public class Player {

    private final double xPos;
    private double yPos;
    private double gunRotation;

    private final int PC;
    private final int PD;
    private final int X1;

    private final double maxY;
    private final double minY;

    private final GunSide gunSide;

    private final double minRotation;
    private final double maxRotation;

    private int points;

    private final List<Bullet> bulletList;

    private final int tankSize;
    private final int gunSize;

    public Player(double xPos, double yPos, double gunRotation, GunSide gunSide, int PC, int PD, int X1, int tankSize, int gunSize, List<Bullet> bulletList, int yWindowSize, int yBarSize) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.gunRotation = gunRotation;
        this.PC = PC;
        this.PD = PD;
        this.points = 0;

        this.X1 = X1;

        this.gunSide = gunSide;

        this.maxY = yBarSize + tankSize / 2;
        this.minY = yWindowSize - yBarSize - tankSize / 2;
        this.minRotation = (double) -30 / (double) 180 * Math.PI;
        this.maxRotation = (double) 30 / (double) 180 * Math.PI;

        this.bulletList = bulletList;

        this.tankSize = tankSize;
        this.gunSize = gunSize;
    }

    public void movePlayer(boolean side, double deltaTime) {
        if (side == true) {
            yPos -= deltaTime * (double) PC;
            if (yPos < maxY) {
                yPos = maxY;
            }
        } else {
            yPos += deltaTime * (double) PC;
            if (yPos > minY) {
                yPos = minY;
            }
        }
    }

    public void elevateGun(boolean side, double deltaTime) {
        if (side) {
            gunRotation -= deltaTime * PD / 180 * Math.PI;
            if (gunRotation < minRotation) {
                gunRotation = minRotation;

            }
        } else {
            gunRotation += deltaTime * PD / 180 * Math.PI;
            if (gunRotation > maxRotation) {
                gunRotation = maxRotation;
            }
        }
    }

    public void addPoint(int point) {
        points += point;
    }

    public boolean ifToManyPoints(int maxPoints) {
        if (points > maxPoints) {
            points = maxPoints;
            return true;
        }
        return false;
    }

    public int getPoints() {
        return points;
    }

    public GunSide getSide() {
        return gunSide;
    }

    public void shotBullet(double R1, double V1) {
        int nOfPlayerBullet = Bullet.countOfPlayerBullet(this, bulletList);
        int dXPos = 0;
        int dYPos = 0;
        if (nOfPlayerBullet < X1) {

            if (gunSide.equals(GunSide.LEFT)) {
                dXPos += gunSize * Math.cos(gunRotation);
                dYPos += gunSize * Math.sin(gunRotation);
            } else if (gunSide.equals(GunSide.RIGHT)) {
                dXPos -= gunSize * Math.cos(gunRotation);
                dYPos -= gunSize * Math.sin(gunRotation);
            }

            Bullet bullet = new Bullet(R1, V1, gunRotation, xPos + dXPos, yPos + dYPos, this, gunSide);
            bulletList.add(bullet);
        }
    }

    public int getXPos() {
        return (int) xPos;
    }

    public int getYPos() {
        return (int) yPos;
    }

    public double getGunRotation() {
        return gunRotation;
    }

    public int getTankSize() {
        return tankSize;
    }

    public int getGunSize() {
        return gunSize;
    }

}
