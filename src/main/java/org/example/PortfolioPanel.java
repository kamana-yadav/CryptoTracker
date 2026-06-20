package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * PortfolioPanel — in-memory portfolio manage karne ke liye Swing UI.
 * Fix: getName(), getPrice() correct getters use karta hai.
 * Add/Remove buttons working hain.
 */
public class PortfolioPanel extends JPanel {

    private PortfolioManager pm = new PortfolioManager();
    private DefaultTableModel model;
    private JLabel totalLabel;

    public PortfolioPanel() {
        setLayout(new BorderLayout(0, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Top controls ---
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextField nameField  = new JTextField(10);
        JTextField symField   = new JTextField(5);
        JTextField priceField = new JTextField(8);
        JButton addBtn    = new JButton("Add");
        JButton removeBtn = new JButton("Remove");
        totalLabel = new JLabel("Total: $0.00");

        top.add(new JLabel("Coin:"));
        top.add(nameField);
        top.add(new JLabel("Symbol:"));
        top.add(symField);
        top.add(new JLabel("Price ($):"));
        top.add(priceField);
        top.add(addBtn);
        top.add(removeBtn);
        add(top, BorderLayout.NORTH);

        // --- Table ---
        String[] columns = {"Coin", "Symbol", "Price (USD)"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable table = new JTable(model);
        table.setRowHeight(24);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- Total label ---
        add(totalLabel, BorderLayout.SOUTH);

        // --- Add button ---
        addBtn.addActionListener(e -> {
            try {
                String name  = nameField.getText().trim();
                String sym   = symField.getText().trim().toUpperCase();
                double price = Double.parseDouble(priceField.getText().trim());

                if (name.isEmpty() || sym.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Coin aur symbol dono bharo.");
                    return;
                }

                CryptoData coin = new CryptoData(name, sym, price, 0, 0);
                pm.addCoin(coin);
                model.addRow(new Object[]{name, sym, String.format("$%.2f", price)});
                updateTotal();

                nameField.setText("");
                symField.setText("");
                priceField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Price sahi number mein dalo.");
            }
        });

        // --- Remove button ---
        removeBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Pehle ek coin select karo.");
                return;
            }
            String name = (String) model.getValueAt(row, 0);
            pm.removeCoin(name);
            model.removeRow(row);
            updateTotal();
        });

        // Load default sample data
        loadSample();
    }

    private void loadSample() {
        CryptoData btc = new CryptoData("Bitcoin", "BTC", 65000, 2.5, 1200000000000.0);
        CryptoData eth = new CryptoData("Ethereum", "ETH", 3500, 1.8, 450000000000.0);
        pm.addCoin(btc);
        pm.addCoin(eth);

        for (CryptoData c : pm.getPortfolio()) {
            model.addRow(new Object[]{
                    c.getName(),
                    c.getSymbol(),
                    String.format("$%.2f", c.getPrice())
            });
        }
        updateTotal();
    }

    private void updateTotal() {
        totalLabel.setText(String.format("Portfolio Total: $%.2f", pm.calculateTotal()));
    }
}
