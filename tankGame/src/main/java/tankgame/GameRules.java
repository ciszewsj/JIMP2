package tankgame;

public class GameRules implements Cloneable {

    private static int V1 = 100;
    private static int X1 = 15;
    private static int R1 = 20;

    private static int V2 = 100;
    private static int H1 = 256;
    private static int PW = 10;

    private static int PC = 100;
    private static int PD = 30;
    private static int PKB = 9;

    private static int T1 = 10;

    private static int DV1 = 20;
    private static int DV2 = 25;
    private static int DR1 = 5;
    private static int DH1 = 20;

    private static int T2 = 5;
    private static int T3 = 60;

    public static void setV1(int V1) {
        if (V1 > 0 || V1 < 32768) {
            GameRules.V1 = V1;
        }
    }

    public static void setX1(int X1) {
        if (X1 > 0 || X1 < 128) {
            GameRules.X1 = X1;
        }
    }

    public static void setR1(int R1) {
        if (R1 > 0 || R1 < 128) {
            GameRules.R1 = R1;
        }
    }

    public static void setV2(int V2) {
        if (V2 >= 0 || V2 < 32768) {
            GameRules.V2 = V2;
        }
    }

    public static void setH1(int H1) {
        if (H1 >= 0 || H1 < 256) {
            GameRules.H1 = H1;
        }
    }

    public static void setPW(int PW) {
        if (PW >= 0 || PW < 1000) {
            GameRules.PW = PW;
        }
    }

    public static void setPC(int PC) {
        if (PC > 0 || PC < 256) {
            GameRules.PC = PC;
        }
    }

    public static void setPD(int PD) {
        if (PD > 0 || PD < 128) {
            GameRules.PD = PD;
        }
    }

    public static void setPKB(int PKB) {
        if (PKB > 0 || H1 < 999) {
            GameRules.PKB = PKB;
        }
    }

    public static void setT1(int T1) {
        if (T1 > 0 || T1 < 32768) {
            GameRules.T1 = T1;
        }
    }

    public static void setDV1(int DV1) {
        if (DV1 > 0 || DV1 < 32768) {
            GameRules.DV1 = DV1;
        }
    }

    public static void setDV2(int DV2) {
        if (PKB > 0 || H1 < 999) {
            GameRules.DV2 = DV2;
        }
    }

    public static void setDR1(int DR1) {
        if (DR1 > 0 || DR1 < 256) {
            GameRules.DR1 = DR1;
        }
    }

    public static void setDH1(int DH1) {
        if (DH1 > 0 || DH1 < 256) {
            GameRules.DH1 = DH1;
        }
    }

    public static void setT2(int T2) {
        if (T2 > 0 || T2 < 32768) {
            GameRules.T2 = T2;
        }
    }

    public static void setT3(int T3) {
        if (T3 > 0 || T3 < 32768) {
            GameRules.T3 = T3;
        }
    }

    public static void makeT1() {
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

    public static int getV1() {
        return V1;
    }

    public static int getX1() {
        return X1;
    }

    public static int getR1() {
        return R1;
    }

    public static int getV2() {
        return V2;
    }

    public static int getH1() {
        return H1;
    }

    public static int getPW() {
        return PW;
    }

    public static int getPC() {
        return PC;
    }

    public static int getPD() {
        return PD;
    }

    public static int getPKB() {
        return PKB;
    }

    public static int getT1() {
        return T1;
    }

    public static int getT2() {
        return T2;
    }

    public static int getT3() {
        return T3;
    }

    public static int getDV1() {
        return DV1;
    }

    public static int getDV2() {
        return DV2;
    }

    public static int getDR1() {
        return DR1;
    }

    public static int getDH1() {
        return DH1;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        GameRules gameRules = (GameRules) super.clone();
        return gameRules;
    }

}
