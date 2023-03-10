package tankgame;

public abstract class CellObject {

    private int P1;
    private final int pointForDestroy;
    private int H1;

    private final int maxP1;

    public CellObject(int P1, int pointForDestroy, int H1, int maxP1) {
        this.P1 = P1;
        this.pointForDestroy = pointForDestroy;
        this.H1 = H1;

        this.maxP1 = maxP1;
    }

    public void addP1() {
        if (P1 < maxP1 && P1 > 0) {
            P1++;
        }
    }

    public void removeP1() {
        P1--;
    }

    public int getP1() {
        return P1;
    }

    public void setP1To0() {
        P1 = 0;
    }

    public int getH1() {
        return H1;
    }

    public int getPointsForDestroy() {
        return pointForDestroy;
    }

    public boolean isAlive() {
        return getP1() > 0;
    }

    public void changeH1(int DH1) {
        H1 = DH1;
    }

    public abstract void destroyCell(Player player);
}
