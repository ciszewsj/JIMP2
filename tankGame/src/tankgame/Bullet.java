package tankgame;

public class Bullet {

    private double R1;
    private double V1;
    private double moveAngle;
    private double xPos;
    private double yPos;

    private GunSide side;

    public Bullet() {

    }

    public void changeSize(double DR1) {
        R1 -= DR1;
    }

    public void changeV1(double DV1) {
        V1 -= DV1;
    }

    public void makeMove(double deltaTime) {

        yPos += V1 * deltaTime * Math.sin(moveAngle);

        if (side.equals(GunSide.LEFT)) {
            xPos += V1 * deltaTime * Math.cos(moveAngle);
        } else if (side.equals(GunSide.RIGHT)) {
            xPos -= V1 * deltaTime * Math.cos(moveAngle);
        }
    }
}
