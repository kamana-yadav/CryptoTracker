import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    CardLayout cardLayout;
    JPanel contentPanel;

    public MainFrame() {

        setTitle("Crypto Tracker");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JLabel title = new JLabel("Crypto Tracker Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);

add(title, BorderLayout.NORTH);

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(4,1));

        JButton dashboardBtn = new JButton("Dashboard");
        JButton portfolioBtn = new JButton("Portfolio");
        JButton favouritesBtn = new JButton("Favourites");
        JButton alertsBtn = new JButton("Alerts");

        sidebar.add(dashboardBtn);
        sidebar.add(portfolioBtn);
        sidebar.add(favouritesBtn);
        sidebar.add(alertsBtn);
        sidebar.setBackground(new Color(33, 37, 41));

        dashboardBtn.setBackground(new Color(52, 152, 219));
        dashboardBtn.setForeground(Color.WHITE);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        contentPanel.add(new DashboardPanel(),"Dashboard");
        contentPanel.add(new PortfolioPanel(),"Portfolio");
        contentPanel.add(new FavouritesPanel(),"Favourites");
        contentPanel.add(new AlertsPanel(),"Alerts");

        dashboardBtn.addActionListener(e ->
                cardLayout.show(contentPanel,"Dashboard"));

        portfolioBtn.addActionListener(e ->
                cardLayout.show(contentPanel,"Portfolio"));

        favouritesBtn.addActionListener(e ->
                cardLayout.show(contentPanel,"Favourites"));

        alertsBtn.addActionListener(e ->
                cardLayout.show(contentPanel,"Alerts"));

        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}