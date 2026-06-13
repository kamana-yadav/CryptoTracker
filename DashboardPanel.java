import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {

        setLayout(new BorderLayout());

        JPanel top = new JPanel();

        JTextField searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");

        top.add(new JLabel("Search Coin"));
        top.add(searchField);
        top.add(searchBtn);

        add(top, BorderLayout.NORTH);

        String[] columns = {
                "Coin",
                "Symbol",
                "Price",
                "Change %",
                "Market Cap"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns,0);

        JTable table = new JTable(model);

        model.addRow(new Object[]{
                "Bitcoin","BTC",65000,2.5,"1.2T"
        });

        add(new JScrollPane(table),
                BorderLayout.CENTER);
    }
}