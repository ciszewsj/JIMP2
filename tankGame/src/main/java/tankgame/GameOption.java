package tankgame;

import javax.swing.JFrame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class GameOption {

    public static JFrame frame;

    public static void ReadFromFile(String path) {
        try {
            FileReader file = new FileReader(new File(path));
            try (Scanner scanner = new Scanner(file)) {
                String line = scanner.nextLine();
                String[] subLine;
                String withoutSpaces;
                String delimeter = "=";
                while (scanner.hasNextLine()) {
                    withoutSpaces = line.replaceAll("\\s+", "");
                    subLine = withoutSpaces.split(delimeter);
                    String variable = subLine[0];

                    if (subLine[1].matches("\\d+")) {
                        int value = Integer.parseInt(subLine[1]);
                        switch (variable.toUpperCase()) {
                            case "V1":
                                GameRules.setV1(value);
                                break;
                            case "X1":
                                GameRules.setX1(value);
                                break;
                            case "R1":
                                GameRules.setR1(value);
                                break;
                            case "V2":
                                GameRules.setV2(value);
                                break;
                            case "H1":
                                GameRules.setH1(value);
                                break;
                            case "T1":
                                GameRules.setT1(value);
                                break;
                            case "DV1":
                                GameRules.setDV1(value);
                                break;
                            case "DV2":
                                GameRules.setDV2(value);
                                break;
                            case "DR1":
                                GameRules.setDR1(value);
                                break;
                            case "DH1":
                                GameRules.setDH1(value);
                                break;
                            case "T2":
                                GameRules.setT2(value);
                                break;
                            case "T3":
                                GameRules.setT3(value);
                                break;
                            default:
                                parametrNotExists(variable.toUpperCase());
                                break;

                        }
                        line = scanner.nextLine();
                    } else {
                        invalidDataFormat(variable, subLine[1]);
                        break;
                    }
                }
            } catch (Exception e) {
            };
        } catch (FileNotFoundException e) {
            unsucceededReadFile(path);
        }
    }

    public static void unsucceededReadFile(String filename) {
        JOptionPane.showMessageDialog(frame,
                "Nie udało się otworzyć plik: " + filename + ".");
    }

    public static void parametrNotExists(String parametr) {
        JOptionPane.showMessageDialog(frame,
                "Nie istnieje parametr: " + parametr + ".");
    }

    public static void invalidDataFormat(String variable, String value) {
        JOptionPane.showMessageDialog(frame,
                "Wartość parametru " + variable + " = " + value + "nie jest liczbą całkowitą");
    }
}
