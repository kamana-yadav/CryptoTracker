package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * DashboardPanel — live crypto prices dikhata hai CoinGecko API se.
 *
 * Bug fix: pehle hardcoded Bitcoin row tha. Ab real API call karta hai.
 * Search se koi bhi coin dhundh sakte ho.
 */
public class DashboardPanel extends JPanel {

    private static final String[] DEFAULT_COINS =
            {"bitcoin", "ethereum", "dogecoin", "solana", "cardano"};

    private DefaultTableModel model;

    public DashboardPanel() {
        setLayout(new BorderLayout(0, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Top: search bar ---
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField searchField = new JTextField(18);
        JButton searchBtn = new JButton("Search");
        JButton refreshBtn = new JButton("Refresh All");
        JLabel statusLabel = new JLabel(" ");

        top.add(new JLabel("Coin ID:"));
        top.add(searchField);
        top.add(searchBtn);
        top.add(refreshBtn);
        top.add(statusLabel);
        add(top, BorderLayout.NORTH);

        // --- Table ---
        String[] columns = {"Coin", "Symbol", "Price (USD)", "24h Change %", "Market Cap"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };

        JTable table = new JTable(model);
        table.setRowHeight(24);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- Load default coins on start ---
        loadCoins(statusLabel, DEFAULT_COINS);

        // --- Search button ---
        searchBtn.addActionListener(e -> {
            String query = searchField.getText().trim().toLowerCase();
            if (query.isEmpty()) return;
            loadCoins(statusLabel, query);
        });

        // --- Refresh button ---
        refreshBtn.addActionListener(e -> {
            model.setRowCount(0);
            loadCoins(statusLabel, DEFAULT_COINS);
        });
    }

    private void loadCoins(JLabel statusLabel, String... coinIds) {
        statusLabel.setText("Loading...");

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                for (String coinId : coinIds) {
                    try {
                        CryptoData data = CryptoAPI.getMarketData(coinId);

                        SwingUtilities.invokeLater(() ->
                            model.addRow(new Object[]{
                                    data.getName(),
                                    data.getSymbol(),
                                    String.format("$%.2f", data.getPrice()),
                                    String.format("%.2f%%", data.getChange()),
                                    String.format("$%.0f", data.getMarketCap())
                            })
                        );

                    } catch (Exception ex) {
                        SwingUtilities.invokeLater(() ->
                            statusLabel.setText("Error: " + ex.getMessage())
                        );
                    }
                }
                return null;
            }

            @Override
            protected void done() {
                statusLabel.setText("Updated.");
            }
        };

        worker.execute();
    }
}
