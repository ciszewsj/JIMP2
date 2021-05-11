package tankgame;

import java.awt.Point;
import java.util.List;
import java.util.Random;

public class CellGenerator {

    public static void generateCellStructure(List<Cell> cellList, int H1, double V2, int middle) {
        Structures structure = Structures.getRandomStructure();
        Random random = new Random();
        Point point;
        while (true) {
            point = structure.getPoint();
            if (point == null) {
                break;
            }

            int P1 = random.nextInt() % 9;
            if (P1 < 0) {
                P1 *= -1;
            }
            int xPos = (int) (middle - point.getX() * H1);
            int yPos = (int) (-H1 / 2 - point.getY() * H1);
            Cell newCell = new Cell(P1 + 1, H1, V2, xPos, yPos);
            cellList.add(newCell);
        }
    }
}
