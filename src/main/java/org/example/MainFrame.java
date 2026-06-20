package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * MainFrame — Swing GUI ka main window.
 * Sidebar buttons se alag panels (Dashboard, Portfolio, Favourites, Alerts) switch hote hain.
 */
public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;

    public MainFrame() {
        setTitle("Crypto Tracker");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Header ---
        JLabel title = new JLabel("Crypto Tracker Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // --- Sidebar ---
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(4, 1, 0, 4));
        sidebar.setBackground(new Color(33, 37, 41));
        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 8, 10, 8));
        sidebar.setPreferredSize(new Dimension(150, 0));

        String[] labels = {"Dashboard", "Portfolio", "Favourites", "Alerts"};
        JButton[] btns  = new JButton[labels.length];

        for (int i = 0; i < labels.length; i++) {
            JButton btn = new JButton(labels[i]);
            btn.setBackground(new Color(52, 73, 94));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(10, 8, 10, 8));
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btns[i] = btn;
            sidebar.add(btn);
        }

        // Highlight first button
        btns[0].setBackground(new Color(52, 152, 219));

        add(sidebar, BorderLayout.WEST);

        // --- Content area ---
        cardLayout  = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        contentPanel.add(new DashboardPanel(),  "Dashboard");
        contentPanel.add(new PortfolioPanel(),  "Portfolio");
        contentPanel.add(new FavouritesPanel(), "Favourites");
        contentPanel.add(new AlertsPanel(),     "Alerts");

        add(contentPanel, BorderLayout.CENTER);

        // Button click handlers
        for (int i = 0; i < labels.length; i++) {
            final String panel = labels[i];
            final int idx = i;
            btns[i].addActionListener(e -> {
                cardLayout.show(contentPanel, panel);
                // Highlight active button
                for (JButton b : btns)      b.setBackground(new Color(52, 73, 94));
                btns[idx].setBackground(new Color(52, 152, 219));
            });
        }

        setVisible(true);
    }
}
