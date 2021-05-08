package tankgame;

import java.util.List;

public class Cell extends CellObject {

    private double V2;
    private double xPos;
    private double yPos;

    private List<Cell> cellList;

    public Cell(int P1, int pointForDestroy, int H1, double V2, double xPos, double yPos) {
        super(P1, pointForDestroy, H1);
        this.V2 = V2;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void moveCell(double deltaTime) {
        yPos += deltaTime * V2;
    }

    public void changeV2(double DV2) {
        V2 -= DV2;
    }

    public int getXPos() {
        return (int) xPos;
    }

    public int getYPos() {
        return (int) yPos;
    }

    @Override
    public void destroyCell(Player player) {
        removeP1();
        if (getP1() <= 0) {
            player.addPoint(getPointsForDestroy());
        }
        cellList.remove(this);
    }

}
