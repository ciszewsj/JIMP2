package tankgame;

public class CellBomb extends CellObject {

    private final int xSize;
    private final int ySize;

    public CellBomb(int P1, int pointForDestroy, int H1, int maxP1) {
        super(P1, pointForDestroy, H1, maxP1);
        this.xSize = 256;
        this.ySize = 100;
    }

    @Override
    public void destroyCell(Player player) {
        if (isAlive()) {
            removeP1();
            if (getP1() <= 0) {
                player.addPoint(getPointsForDestroy());
            }
        }
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }
}
