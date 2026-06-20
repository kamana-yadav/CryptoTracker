package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * FavouritesPanel — favourite coins list dikhata aur manage karta hai.
 */
public class FavouritesPanel extends JPanel {

    private DefaultTableModel model;

    public FavouritesPanel() {
        setLayout(new BorderLayout(0, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Top controls ---
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField coinField = new JTextField(12);
        JTextField symField  = new JTextField(6);
        JButton addBtn    = new JButton("Add Favourite");
        JButton removeBtn = new JButton("Remove");

        top.add(new JLabel("Coin:"));
        top.add(coinField);
        top.add(new JLabel("Symbol:"));
        top.add(symField);
        top.add(addBtn);
        top.add(removeBtn);
        add(top, BorderLayout.NORTH);

        // --- Table ---
        String[] columns = {"Coin", "Symbol"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable table = new JTable(model);
        table.setRowHeight(24);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Sample data
        model.addRow(new Object[]{"Bitcoin", "BTC"});
        model.addRow(new Object[]{"Ethereum", "ETH"});

        // --- Add ---
        addBtn.addActionListener(e -> {
            String coin = coinField.getText().trim();
            String sym  = symField.getText().trim().toUpperCase();
            if (coin.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Coin name khali nahi hona chahiye.");
                return;
            }
            model.addRow(new Object[]{coin, sym});
            coinField.setText("");
            symField.setText("");
        });

        // --- Remove ---
        removeBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Pehle ek coin select karo.");
                return;
            }
            model.removeRow(row);
        });
    }
}
