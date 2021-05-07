package tankgame;

import java.util.List;

public class Player {
    
    private final String playerID;
    
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
    
    int points;
    
    private List<Bullet> bulletList;
    
    public Player(String playerID, double xPos, double yPos, double gunRotation, GunSide gunSide, int PC, int PD, int X1, List<Bullet> bulletList) {
        this.playerID = playerID;
        this.xPos = xPos;
        this.yPos = yPos;
        this.gunRotation = gunRotation;
        this.PC = PC;
        this.PD = PD;
        this.points = 0;
        
        this.X1 = X1;
        
        this.gunSide = gunSide;
        
        this.maxY = 0;
        this.minY = 100;
        this.minRotation = -30;
        this.maxRotation = 30;
        
        this.bulletList = bulletList;
    }
    
    public void movePlayer(boolean side, double deltaTime) {
        if (side) {
            yPos -= deltaTime * PC;
            if (yPos < maxY) {
                yPos = maxY;
            }
        } else {
            yPos += deltaTime * PC;
            if (yPos < minY) {
                yPos = minY;
            }
        }
    }
    
    public void elevateGun(boolean side, double deltaTime) {
        if (side) {
            gunRotation -= deltaTime * PD;
            if (gunRotation < minRotation) {
                gunRotation = minRotation;
            }
        } else {
            gunRotation += deltaTime * PD;
            if (gunRotation < maxRotation) {
                gunRotation = maxRotation;
            }
        }
    }
    
    public void addPoint(int point) {
        points += point;
    }
    
    public void shotBullet() {
        int nOfPlayerBullet = 0;
        for (Bullet b : bulletList) {
            if (b.getSide().equals(gunSide)) {
                nOfPlayerBullet++;
            }
        }
        if (nOfPlayerBullet < X1) {
            Bullet bullet = new Bullet(xPos, xPos, maxY, xPos, yPos, gunSide);
            bulletList.add(bullet);
        }
    }
}
