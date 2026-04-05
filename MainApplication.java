package JManeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainApplication {

    // Main Panels
    private JFrame frame;
    private JPanel mainContentPanel;
    private JLabel statusLabel;

    public MainApplication() {
        CreateFrame();
    }

    // FRAME
    public JFrame CreateFrame() {
        frame = new JFrame();
        frame.setTitle("Student Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Add all sections
        frame.setJMenuBar(CreateMenuBar());
        frame.add(CreateHeaderPanel(),  BorderLayout.NORTH);
        frame.add(CreateSidePanel(),    BorderLayout.WEST);
        frame.add(CreateContentPanel(), BorderLayout.CENTER);
        frame.add(CreateFooterPanel(),  BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null); // centre on screen
        frame.setVisible(true);
        return frame;
    }

    // MENU BAR
    public JMenuBar CreateMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(CreateFileMenu());
        menuBar.add(CreateViewMenu());
        menuBar.add(CreateHelpMenu());
        return menuBar;
    }

    public JMenu CreateFileMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem newItem  = new JMenuItem("New");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Action Listeners
        newItem.addActionListener(e -> showContent("New File Created"));
        saveItem.addActionListener(e -> showContent("File Saved Successfully"));
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator(); // draws a line
        fileMenu.add(exitItem);
        return fileMenu;
    }

    public JMenu CreateViewMenu() {
        JMenu viewMenu = new JMenu("View");

        JMenuItem homeItem     = new JMenuItem("Home");
        JMenuItem studentsItem = new JMenuItem("Students");
        JMenuItem reportsItem  = new JMenuItem("Reports");

        homeItem.addActionListener(e     -> showContent("Welcome to the Home Page!"));
        studentsItem.addActionListener(e -> showContent("Student List goes here..."));
        reportsItem.addActionListener(e  -> showContent("Reports and Analytics..."));

        viewMenu.add(homeItem);
        viewMenu.add(studentsItem);
        viewMenu.add(reportsItem);
        return viewMenu;
    }

    public JMenu CreateHelpMenu() {
        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutItem   = new JMenuItem("About");
        JMenuItem contactItem = new JMenuItem("Contact");

        aboutItem.addActionListener(e ->
                JOptionPane.showMessageDialog(frame,
                        "Student Management System v1.0\nBuilt with Java Swing",
                        "About", JOptionPane.INFORMATION_MESSAGE));

        contactItem.addActionListener(e ->
                JOptionPane.showMessageDialog(frame,
                        "Email: support@sms.com",
                        "Contact", JOptionPane.INFORMATION_MESSAGE));

        helpMenu.add(aboutItem);
        helpMenu.add(contactItem);
        return helpMenu;
    }

    // HEADER PANEL (NORTH)
    public JPanel CreateHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(33, 97, 140));
        headerPanel.setPreferredSize(new Dimension(0, 60));

        JLabel titleLabel = new JLabel("Student Management System");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        headerPanel.add(titleLabel, BorderLayout.CENTER);
        return headerPanel;
    }

    // SIDE PANEL (WEST)
    public JPanel CreateSidePanel() {
        JPanel sidePanel = new JPanel(new GridLayout(4, 1, 5, 5));
        sidePanel.setBackground(new Color(52, 73, 94));
        sidePanel.setPreferredSize(new Dimension(150, 0));

        sidePanel.add(CreateSideButton("🏠 Home",     "Welcome to the Home Page!"));
        sidePanel.add(CreateSideButton("👨 Students", "Student List goes here..."));
        sidePanel.add(CreateSideButton("📊 Reports",  "Reports and Analytics..."));
        sidePanel.add(CreateSideButton("⚙ Settings",  "Settings Panel..."));

        return sidePanel;
    }

    public JButton CreateSideButton(String text, String content) {
        JButton button = new JButton(text);
        button.setBackground(new Color(52, 73, 94));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        // Clicking the button updates the main content panel
        button.addActionListener(e -> showContent(content));
        return button;
    }

    // CONTENT PANEL (CENTER)
    public JPanel CreateContentPanel() {
        mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Select an option from the menu or sidebar", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        welcomeLabel.setForeground(Color.GRAY);

        mainContentPanel.add(welcomeLabel, BorderLayout.CENTER);
        return mainContentPanel;
    }

    // FOOTER PANEL (SOUTH)
    public JPanel CreateFooterPanel() {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(33, 97, 140));
        footerPanel.setPreferredSize(new Dimension(0, 30));

        statusLabel = new JLabel("  Status: Ready");
        statusLabel.setForeground(Color.WHITE);

        JLabel versionLabel = new JLabel("v1.0  ");
        versionLabel.setForeground(Color.WHITE);

        footerPanel.add(statusLabel,  BorderLayout.WEST);
        footerPanel.add(versionLabel, BorderLayout.EAST);
        return footerPanel;
    }

    // HELPER — Update Content Panel
    public void showContent(String message) {
        // Clears current content and shows new message
        mainContentPanel.removeAll();

        JLabel label = new JLabel(message, JLabel.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 16));

        mainContentPanel.add(label, BorderLayout.CENTER);
        mainContentPanel.revalidate(); // refreshes layout
        mainContentPanel.repaint();   // redraws panel

        // Update footer status bar
        statusLabel.setText("  Status: " + message);
    }

}

