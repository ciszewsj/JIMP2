package tankgame;

import java.util.List;

public class Player {

    private double xPos;
    private double yPos;
    private double gunRotation;

    private int PC;
    private int PD;
    private final int X1;

    private double maxY;
    private final double minY;

    private final GunSide gunSide;

    private final double minRotation;
    private final double maxRotation;

    private int points;

    private List<Bullet> bulletList;

    public Player(double xPos, double yPos, double gunRotation, GunSide gunSide, int PC, int PD, int X1, List<Bullet> bulletList) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.gunRotation = gunRotation;
        this.PC = PC;
        this.PD = PD;
        this.points = 0;

        this.X1 = X1;

        this.gunSide = gunSide;

        this.maxY = 100 + 50;
        this.minY = 1024 - 100 - 50;
        this.minRotation = (double) -30 / (double) 180 * Math.PI;
        this.maxRotation = (double) 30 / (double) 180 * Math.PI;

        this.bulletList = bulletList;
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

    public int getPoints() {
        return points;
    }

    public GunSide getSide() {
        return gunSide;
    }

    public void shotBullet() {
        int nOfPlayerBullet = Bullet.countOfPlayerBullet(this, bulletList);
        if (nOfPlayerBullet < X1) {
            Bullet bullet = new Bullet(0, 0, gunRotation, xPos, yPos, gunSide);
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
}
