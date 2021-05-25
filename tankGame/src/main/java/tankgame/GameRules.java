package tankgame;

public class GameRules {

    private int V1;
    private int X1;
    private int R1;

    private int V2;
    private int H1;
    private int PW;

    private int PC;
    private int PD;
    private int PKB;

    private int T1;

    private int DV1;
    private int DV2;
    private int DR1;
    private int DH1;

    private int T2;
    private int T3;

    public GameRules() {
        V1 = 100;
        X1 = 15;
        R1 = 20;

        V2 = 100;
        H1 = 256;
        PW = 10;

        PC = 100;
        PD = 30;
        PKB = 9;

        T1 = 10;

        DV1 = 20;
        DV2 = 25;
        DR1 = 5;
        DH1 = 20;

        T2 = 5;
        T3 = 60;
    }

    public void setV1(int V1) {
        this.V1 = V1;
    }

    public void setX1(int X1) {
        this.X1 = X1;
    }

    public void setR1(int R1) {
        this.R1 = R1;
    }

    public void setV2(int V2) {
        this.V2 = V2;
    }

    public void setH1(int H1) {
        this.H1 = H1;
    }

    public void setPW(int PW) {
        this.PW = PW;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public void setPD(int PD) {
        this.PD = PD;
    }

    public void setPKB(int PKB) {
        this.PKB = PKB;
    }

    public void setT1(int T1) {
        this.T1 = T1;
    }

    public void setDV1(int DV1) {
        this.DV1 = DV1;
    }

    public void setDV2(int DV2) {
        this.DV2 = DV2;
    }

    public void setDR1(int DR1) {
        this.DR1 = DR1;
    }

    public void setDH1(int DH1) {
        this.DH1 = DH1;
    }

    public void setT2(int T2) {
        this.T2 = T2;
    }

    public void setT3(int T3) {
        this.T3 = T3;
    }

    public void makeT1() {
        V1 += DV1;

        V2 += DV2;

        R1 -= DR1;
        if (R1 < 1) {
            R1 = 1;
        }
        H1 -= DH1;
        if (H1 < 1) {
            H1 = 1;
        }
    }

    public int getV1() {
        return V1;
    }

    public int getX1() {
        return X1;
    }

    public int getR1() {
        return R1;
    }

    public int getV2() {
        return V2;
    }

    public int getH1() {
        return H1;
    }

    public int getPW() {
        return PW;
    }

    public int getPC() {
        return PC;
    }

    public int getPD() {
        return PD;
    }

    public int getPKB() {
        return PKB;
    }

    public int getT1() {
        return T1;
    }

    public int getT2() {
        return T2;
    }

    public int getT3() {
        return T3;
    }
}
