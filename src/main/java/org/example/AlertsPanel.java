package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AlertsPanel — price alerts set karo aur check karo.
 *
 * Bug fix: pehle checkAlert(target - 100) tha — fake price tha.
 * Ab "Check Alerts" button real CoinGecko API se price fetch karta hai.
 */
public class AlertsPanel extends JPanel {

    private DefaultTableModel model;
    private List<PriceAlert> alerts = new ArrayList<>();

    public AlertsPanel() {
        setLayout(new BorderLayout(0, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Top controls ---
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextField coinField   = new JTextField(10);
        JTextField targetField = new JTextField(10);
        JButton addBtn   = new JButton("Add Alert");
        JButton checkBtn = new JButton("Check Alerts Now");
        JLabel statusLbl = new JLabel(" ");

        top.add(new JLabel("Coin ID (e.g. bitcoin):"));
        top.add(coinField);
        top.add(new JLabel("Target Price ($):"));
        top.add(targetField);
        top.add(addBtn);
        top.add(checkBtn);
        top.add(statusLbl);
        add(top, BorderLayout.NORTH);

        // --- Table ---
        String[] columns = {"Coin", "Target Price ($)", "Status"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable table = new JTable(model);
        table.setRowHeight(24);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- Add Alert ---
        addBtn.addActionListener(e -> {
            try {
                String coin   = coinField.getText().trim().toLowerCase();
                double target = Double.parseDouble(targetField.getText().trim());

                if (coin.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Coin ID dalo (e.g. bitcoin).");
                    return;
                }

                PriceAlert alert = new PriceAlert(coin, target);
                alerts.add(alert);
                model.addRow(new Object[]{coin, String.format("$%.2f", target), "Pending"});

                coinField.setText("");
                targetField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Target price sahi number mein dalo.");
            }
        });

        // --- Check Alerts (real API call) ---
        checkBtn.addActionListener(e -> {
            if (alerts.isEmpty()) {
                statusLbl.setText("Koi alert set nahi hai.");
                return;
            }

            statusLbl.setText("Checking...");
            checkBtn.setEnabled(false);

            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    for (int i = 0; i < alerts.size(); i++) {
                        PriceAlert alert = alerts.get(i);
                        final int row = i;

                        try {
                            double currentPrice = CryptoAPI.getCryptoPrice(
                                    alert.getCoinName(), "usd");
                            boolean triggered = alert.checkAlert(currentPrice);

                            SwingUtilities.invokeLater(() -> {
                                String status = triggered
                                        ? "TRIGGERED! Current: $" + String.format("%.2f", currentPrice)
                                        : "OK. Current: $" + String.format("%.2f", currentPrice);
                                model.setValueAt(status, row, 2);
                            });

                        } catch (Exception ex) {
                            SwingUtilities.invokeLater(() ->
                                model.setValueAt("Error: " + ex.getMessage(), row, 2)
                            );
                        }
                    }
                    return null;
                }

                @Override
                protected void done() {
                    statusLbl.setText("Check complete.");
                    checkBtn.setEnabled(true);
                }
            };

            worker.execute();
        });
    }
}
