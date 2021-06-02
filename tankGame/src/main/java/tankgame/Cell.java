package tankgame;

public class Cell extends CellObject {

    private double V2;
    private final double xPos;
    private double yPos;

    public Cell(int P1, int H1, double V2, double xPos, double yPos) {
        super(P1, P1, H1);
        this.V2 = V2;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void moveCell(double deltaTime, int gameYSize) {
        yPos += deltaTime * V2;
        if (yPos - getH1() / 2 > gameYSize) {
            setP1To0();
        }
    }

    public void changeV2(double DV2) {
        V2 = DV2;
    }

    public int getXPos() {
        return (int) xPos;
    }

    public int getYPos() {
        return (int) yPos;
    }

    @Override
    public void destroyCell(Player player) {
        if (isAlive()) {
            removeP1();
            if (getP1() == 0) {
                player.addPoint(getPointsForDestroy());
            }
        }
    }

}
