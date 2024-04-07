package ui;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class Statistics extends JFrame {
    private JTextField monthlyStatsField;
    private JTextField categoryStatsField;
    private JTextField highestExpenditureField;
    private JTextField lowestExpenditureField;

    public Statistics() {
        setTitle("Statistics");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(135, 206, 235)); // Sky Blue

        // Title Panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Statistics");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Panel for monthly and category stats
        JPanel statsPanel = new JPanel(new GridLayout(2, 1));

        // Monthly Stats Panel
        JPanel monthlyStatsPanel = new JPanel(new GridBagLayout());
        JLabel monthlyStatsLabel = new JLabel("Monthly Stats:");
        monthlyStatsField = new JTextField(15);
        monthlyStatsField.setEditable(false);
        addComponent(monthlyStatsPanel, monthlyStatsLabel, 0, 0, 1, 1, GridBagConstraints.WEST);
        addComponent(monthlyStatsPanel, monthlyStatsField, 0, 1, 1, 1, GridBagConstraints.WEST);

        // Category Stats Panel
        JPanel categoryStatsPanel = new JPanel(new GridBagLayout());
        JLabel categoryStatsLabel = new JLabel("Category Stats:");
        categoryStatsField = new JTextField(15);
        categoryStatsField.setEditable(false);
        addComponent(categoryStatsPanel, categoryStatsLabel, 0, 0, 1, 1, GridBagConstraints.WEST);
        addComponent(categoryStatsPanel, categoryStatsField, 0, 1, 1, 1, GridBagConstraints.WEST);

        statsPanel.add(monthlyStatsPanel);
        statsPanel.add(categoryStatsPanel);

        // Expenditure Stats Panel
        JPanel expenditureStatsPanel = new JPanel(new GridBagLayout());
        JLabel highestExpenditureLabel = new JLabel("Highest of the month:");
        highestExpenditureField = new JTextField(15);
        highestExpenditureField.setEditable(false);
        JLabel lowestExpenditureLabel = new JLabel("Lowest of the month:");
        lowestExpenditureField = new JTextField(15);
        lowestExpenditureField.setEditable(false);
        addComponent(expenditureStatsPanel, highestExpenditureLabel, 0, 0, 1, 1, GridBagConstraints.WEST);
        addComponent(expenditureStatsPanel, highestExpenditureField, 0, 1, 1, 1, GridBagConstraints.WEST);
        addComponent(expenditureStatsPanel, lowestExpenditureLabel, 1, 0, 1, 1, GridBagConstraints.WEST);
        addComponent(expenditureStatsPanel, lowestExpenditureField, 1, 1, 1, 1, GridBagConstraints.WEST);

        // Add components to main panel
        mainPanel.add(statsPanel, BorderLayout.CENTER);
        mainPanel.add(expenditureStatsPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);

        setVisible(true);

        // Sample data
        double highestExpenditure = 1500.75;
        double lowestExpenditure = 300.50;
        DecimalFormat df = new DecimalFormat("#.##");
        highestExpenditureField.setText("$" + df.format(highestExpenditure));
        lowestExpenditureField.setText("$" + df.format(lowestExpenditure));
    }

    private void addComponent(Container container, Component component, int gridx, int gridy, int gridwidth, int gridheight, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = anchor;
        container.add(component, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Statistics();
            }
        });
    }
}