import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PortfolioPanel extends JPanel {

    public PortfolioPanel() {

        setLayout(new BorderLayout());

        PortfolioManager pm = new PortfolioManager();

        pm.addCoin(new CryptoData(
                "Bitcoin",
                "BTC",
                65000,
                2.5,
                1200000000000.0
        ));

        pm.addCoin(new CryptoData(
                "Ethereum",
                "ETH",
                3500,
                1.8,
                450000000000.0
        ));

        JLabel totalLabel =
                new JLabel("Portfolio Value: $" + pm.calculateTotal());

        add(totalLabel, BorderLayout.NORTH);

        String[] columns = {
                "Coin",
                "Symbol",
                "Price"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

        for (CryptoData coin : pm.portfolio) {
            model.addRow(new Object[]{
                    coin.getname(),
                    coin.getsymbol(),
                    coin.getprice()
            });
        }

        JTable table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}