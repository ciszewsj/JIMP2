package tankgame;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class ErrorWindow extends JFrame {

    private JList errorListPanel;
    private Container cointaner;

    public ErrorWindow(int width, int height, List<String> errorList) {

        errorListPanel = new JList(new ConvertListObject(errorList));
        cointaner = getContentPane();
        cointaner.add(new JScrollPane(errorListPanel));
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ErrorListDescritpion");
        pack();

    }

    public void refreshWindow(List<String> errorList) {
        errorListPanel = new JList(new ConvertListObject(errorList));
        cointaner = getContentPane();
        cointaner.add(new JScrollPane(errorListPanel));
        pack();
    }

}

class ConvertListObject extends AbstractListModel {

    private final List<String> errorList;

    public ConvertListObject(List<String> errorList) {
        this.errorList = new ArrayList<>(errorList);
    }

    @Override
    public int getSize() {
        return errorList.size();
    }

    @Override
    public Object getElementAt(int i) {
        return errorList.get(i);
    }

}
