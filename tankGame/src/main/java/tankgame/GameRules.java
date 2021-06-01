package tankgame;

public class GameRules implements Cloneable {

    private int V1 = 100;
    private int X1 = 15;
    private int R1 = 20;

    private int V2 = 100;
    private int H1 = 256;
    private int PW = 10;

    private int PC = 100;
    private int PD = 30;
    private int PKB = 9;

    private int T1 = 10;

    private int DV1 = 20;
    private int DV2 = 25;
    private int DR1 = 5;
    private int DH1 = 20;

    private int T2 = 5;
    private int T3 = 60;

    public void setV1(int V1, ErrorWindowController error) {
        int min = 0;
        int max = 32768;
        if (V1 > min && V1 < max) {
            this.V1 = V1;
        } else {
            error.addErrorParametrValue("V1", min, max);
        }
    }

    public void setX1(int X1, ErrorWindowController error) {
        int min = 0;
        int max = 128;
        if (X1 > min && X1 < max) {
            this.X1 = X1;
        } else {
            error.addErrorParametrValue("X1", min, max);
        }
    }

    public void setR1(int R1, ErrorWindowController error) {
        int min = 0;
        int max = 128;
        if (R1 > min && R1 < max) {
            this.R1 = R1;
        } else {
            error.addErrorParametrValue("R1", min, max);
        }
    }

    public void setV2(int V2, ErrorWindowController error) {
        int min = 0;
        int max = 32768;
        if (V2 > min && V2 < max) {
            this.V2 = V2;
        } else {
            error.addErrorParametrValue("V2", min, max);
        }
    }

    public void setH1(int H1, ErrorWindowController error) {
        int min = 0;
        int max = 256;
        if (H1 > min && H1 < max) {
            this.H1 = H1;
        } else {
            error.addErrorParametrValue("H1", min, max);
        }
    }

    public void setPW(int PW, ErrorWindowController error) {
        int min = 0;
        int max = 1000;
        if (PW > min && PW < max) {
            this.PW = PW;
        } else {
            error.addErrorParametrValue("PW", min, max);
        }
    }

    public void setPC(int PC, ErrorWindowController error) {
        int min = 0;
        int max = 32768;
        if (PC > min && PC < max) {
            this.PC = PC;
        } else {
            error.addErrorParametrValue("PC", min, max);
        }
    }

    public void setPD(int PD, ErrorWindowController error) {
        int min = 0;
        int max = 128;
        if (PD > min && PD < max) {
            this.PD = PD;
        } else {
            error.addErrorParametrValue("PD", min, max);
        }
    }

    public void setPKB(int PKB, ErrorWindowController error) {
        int min = 0;
        int max = 999;
        if (PKB > min && PKB < max) {
            this.PKB = PKB;
        } else {
            error.addErrorParametrValue("PKB", min, max);
        }
    }

    public void setT1(int T1, ErrorWindowController error) {
        int min = 0;
        int max = 32768;
        if (T1 > min && T1 < max) {
            this.T1 = T1;
        } else {
            error.addErrorParametrValue("T1", min, max);
        }
    }

    public void setDV1(int DV1, ErrorWindowController error) {
        int min = 0;
        int max = 32768;
        if (DV1 > min && DV1 < max) {
            this.DV1 = DV1;
        } else {
            error.addErrorParametrValue("DV1", min, max);
        }
    }

    public void setDV2(int DV2, ErrorWindowController error) {
        int min = 0;
        int max = 999;
        if (DV2 > min && DV2 < max) {
            this.DV2 = DV2;
        } else {
            error.addErrorParametrValue("DV2", min, max);
        }
    }

    public void setDR1(int DR1, ErrorWindowController error) {
        int min = 0;
        int max = 256;
        if (DR1 > min && DR1 < max) {
            this.DR1 = DR1;
        } else {
            error.addErrorParametrValue("DR1", min, max);
        }
    }

    public void setDH1(int DH1, ErrorWindowController error) {
        int min = 0;
        int max = 256;
        if (DH1 > min && DH1 < max) {
            this.DH1 = DH1;
        } else {
            error.addErrorParametrValue("DH1", min, max);
        }
    }

    public void setT2(int T2, ErrorWindowController error) {
        int min = 0;
        int max = 32768;
        if (T2 > min && T2 < max) {
            this.T2 = T2;
        } else {
            error.addErrorParametrValue("T2", min, max);
        }
    }

    public void setT3(int T3, ErrorWindowController error) {
        int min = 0;
        int max = 32768;
        if (T3 > min && T3 < max) {
            this.T3 = T3;
        } else {
            error.addErrorParametrValue("T3", min, max);
        }

    }

    public void makeT1() {

        if (V1 + DV1 > 0 && V1 + DV1 < 32768) {
            V1 += DV1;
        }
        if (V2 + DV2 > 0 && V2 + DV2 < 32768) {
            V2 += DV2;
        }
        if (R1 - DR1 > 0 && R1 - DR1 < 256) {
            R1 -= DR1;
        }

        if (H1 - DH1 > 0 && H1 - DH1 < 256) {
            H1 -= DH1;
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

    public int getDV1() {
        return DV1;
    }

    public int getDV2() {
        return DV2;
    }

    public int getDR1() {
        return DR1;
    }

    public int getDH1() {
        return DH1;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        GameRules gameRules = (GameRules) super.clone();
        return gameRules;
    }

}
