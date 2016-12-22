import ui.container.model.table.CompositionTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by igor on 9.10.16.
 */
public class JTest extends JFrame{
    public JTest() throws HeadlessException {
        super("Test");
        add(new JTable(CompositionTableModel.getTableModelForExample()));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300,300);
        setVisible(true);
    }

    public static void main(String... args){
        new JTest();
    }
}
