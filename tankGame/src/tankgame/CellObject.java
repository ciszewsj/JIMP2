package tankgame;

public abstract class CellObject {

    private int P1;
    private int pointForDestroy;
    private int H1;

    private int maxP1;

    public CellObject(int P1, int pointForDestroy, int H1) {
        this.P1 = P1;
        this.pointForDestroy = pointForDestroy;
        this.H1 = H1;

        this.maxP1 = 9;
    }

    public void addP1() {
        if (P1 < 9) {
            P1++;
        }
    }

    public int getP1() {
        return P1;
    }

    public int getH1() {
        return H1;
    }

    public int getPointsForDestroy() {
        return pointForDestroy;
    }

    public void reduceH1(int DH1) {
        H1 -= DH1;
    }

    public abstract void destroyCell(Player player);
}
