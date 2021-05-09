package tankgame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Structures {

    private final List<Point> pos;

    public Structures(List<Point> pos) {
        this.pos = pos;
    }

    public Point getPoint() {
        if (pos.isEmpty()) {
            return null;
        }
        Point pointToReturn = pos.get(0);
        pos.remove(pointToReturn);
        return pointToReturn;
    }

    private static void getPosStruct(List<Point> pos, int structureID) {
        switch (structureID) {
            case 0:
                pos.add(new Point(0, 0));
                break;
            case 1:
                pos.add(new Point(0, 0));
                pos.add(new Point(0, 1));
                break;
            case 2:
                pos.add(new Point(0, 0));
                pos.add(new Point(-1, 0));
                pos.add(new Point(1, 0));
                break;
            case 3:
                pos.add(new Point(0, 0));
                pos.add(new Point(-1, 0));
                pos.add(new Point(1, 0));
                pos.add(new Point(0, 1));
                break;
            case 4:
                pos.add(new Point(1, 1));
                pos.add(new Point(-1, 0));
                pos.add(new Point(1, 0));
                pos.add(new Point(1, 2));
                pos.add(new Point(-1, 2));
                break;
            default:
                break;
        }
    }

    public static Structures getRandomStructure() {
        List<Point> pos = new ArrayList<>();
        Random random = new Random();
        int structureID = random.nextInt() % 5;
        getPosStruct(pos, structureID);
        return new Structures(pos);
    }

    public static Structures getRandomStructure(long seed) {
        List<Point> pos = new ArrayList<>();
        Random random = new Random(seed);
        int structureID = random.nextInt() % 5;
        getPosStruct(pos, structureID);
        return new Structures(pos);
    }
}
