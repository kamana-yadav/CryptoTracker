import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FavouritesPanel extends JPanel {

    public FavouritesPanel() {

        setLayout(new BorderLayout());

        String[] columns = {
                "Coin",
                "Symbol"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns,0);

        JTable table = new JTable(model);

        model.addRow(new Object[]{
                "Bitcoin","BTC"
        });

        add(new JScrollPane(table),
                BorderLayout.CENTER);
    }
}