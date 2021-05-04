package tankgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Structures {

    private List<Integer> xPos;
    private List<Integer> yPos;

    public Structures(List<Integer> xPos, List<Integer> yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    private static void getPosStruct(List<Integer> xPos, List<Integer> yPos, int structureID) {
        switch (structureID) {
            case 0:
                xPos.add(0);
                yPos.add(0);
                break;
            case 1:
                xPos.add(0);
                yPos.add(0);
                xPos.add(0);
                yPos.add(1);
                break;
            case 2:
                xPos.add(0);
                yPos.add(0);
                xPos.add(1);
                yPos.add(0);
                xPos.add(-1);
                yPos.add(0);
                break;
            case 3:
                xPos.add(0);
                yPos.add(0);
                xPos.add(1);
                yPos.add(0);
                xPos.add(-1);
                yPos.add(0);
                xPos.add(0);
                yPos.add(1);
                break;
            case 4:
                xPos.add(0);
                yPos.add(1);
                xPos.add(1);
                yPos.add(0);
                xPos.add(-1);
                yPos.add(0);
                xPos.add(-1);
                yPos.add(2);
                xPos.add(1);
                yPos.add(2);
                break;
            default:
                break;
        }
    }

    public static Structures getRandomStructure() {
        List<Integer> xPos = new ArrayList<>();
        List<Integer> yPos = new ArrayList<>();
        Random random = new Random();
        int structureID = random.nextInt() % 5;
        getPosStruct(xPos, yPos, structureID);
        return new Structures(xPos, yPos);
    }

    public static Structures getRandomStructure(long seed) {
        List<Integer> xPos = new ArrayList<>();
        List<Integer> yPos = new ArrayList<>();
        Random random = new Random(seed);
        int structureID = random.nextInt() % 5;
        getPosStruct(xPos, yPos, structureID);
        return new Structures(xPos, yPos);
    }
}
