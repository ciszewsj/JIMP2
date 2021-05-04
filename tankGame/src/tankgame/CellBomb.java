package tankgame;

public class CellBomb extends CellObject {

    public CellBomb(int P1, int pointForDestroy, int H1) {
        super(P1, pointForDestroy, H1);
    }

    @Override
    public void destroyCell(Player player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
