import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AlertsPanel extends JPanel {

    public AlertsPanel() {

        setLayout(new BorderLayout());

        JPanel top = new JPanel();

        JTextField coinField = new JTextField(10);
        JTextField targetField = new JTextField(10);

        JButton addBtn = new JButton("Add Alert");

        top.add(new JLabel("Coin"));
        top.add(coinField);

        top.add(new JLabel("Target"));
        top.add(targetField);

        top.add(addBtn);

        add(top, BorderLayout.NORTH);

        String[] columns = {
                "Coin",
                "Target Price"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        addBtn.addActionListener(e -> {

            try {

                String coin = coinField.getText();

                double target =
                        Double.parseDouble(targetField.getText());

                PriceAlert alert =
                        new PriceAlert(coin, target);

                alert.checkAlert(target - 100);

                model.addRow(new Object[]{
                        coin,
                        target
                });

                JOptionPane.showMessageDialog(
                        this,
                        "Alert Added Successfully!"
                );

                coinField.setText("");
                targetField.setText("");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter valid data"
                );
            }
        });
    }
}