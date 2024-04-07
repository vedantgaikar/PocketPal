package ui;

import Database.DatabaseHandler;
import Database.EntryDetails;
import Database.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics extends JFrame {

    private User user;
    private JTextField highestExpenditureField;
    private JTextField lowestExpenditureField;
    private JTextField pocketMoneyField;
    private JTable monthlyTable;
    private JTable categoryTable;

    public Statistics(User user) {
        this.user = user;
        setTitle("Statistics");
        setSize(800, 600); // Set an appropriate size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Open the frame at the center of the screen
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(135, 206, 235)); // Light Blue

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.LIGHT_GRAY); // Dark Blue
        titlePanel.setForeground(Color.WHITE);
        JLabel titleLabel = new JLabel("Statistics");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel(new GridLayout(1, 2));
        statsPanel.setBackground(new Color(135, 206, 235)); // Light blue

        JPanel monthlyStatsPanel = new JPanel(new BorderLayout());
        monthlyStatsPanel.setBackground(new Color(240, 240, 240)); // Light Gray
        monthlyStatsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        monthlyStatsPanel.add(new JLabel("Monthly Expense"), BorderLayout.NORTH);

        JPanel categoryStatsPanel = new JPanel(new BorderLayout());
        categoryStatsPanel.setBackground(new Color(240, 240, 240)); // Light Gray
        categoryStatsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        categoryStatsPanel.add(new JLabel("Category Expense"), BorderLayout.NORTH);

        monthlyTable = new JTable(new DefaultTableModel(new Object[]{"Month", "Expense"}, 0));
        categoryTable = new JTable(new DefaultTableModel(new Object[]{"Category", "Expense"}, 0));

        JScrollPane monthlyScrollPane = new JScrollPane(monthlyTable);
        JScrollPane categoryScrollPane = new JScrollPane(categoryTable);

        monthlyStatsPanel.add(monthlyScrollPane, BorderLayout.CENTER);
        categoryStatsPanel.add(categoryScrollPane, BorderLayout.CENTER);

        statsPanel.add(monthlyStatsPanel);
        statsPanel.add(categoryStatsPanel);

        mainPanel.add(statsPanel, BorderLayout.CENTER);

        JPanel expenditureStatsPanel = createStatsPanel("Expenditure Stats:");
        highestExpenditureField = new JTextField(15);
        highestExpenditureField.setEditable(false);
        lowestExpenditureField = new JTextField(15);
        lowestExpenditureField.setEditable(false);
        expenditureStatsPanel.add(createStatsPanel("Highest :"));
        expenditureStatsPanel.add(highestExpenditureField);
        expenditureStatsPanel.add(createStatsPanel("Lowest :"));
        expenditureStatsPanel.add(lowestExpenditureField);

        JPanel pocketMoneyPanel = createStatsPanel("Total Money Remaining:");
        pocketMoneyField = new JTextField(15);
        pocketMoneyField.setEditable(false);
        pocketMoneyPanel.add(pocketMoneyField);

        statsPanel.add(expenditureStatsPanel);
        statsPanel.add(pocketMoneyPanel);

        mainPanel.add(statsPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(new Color(0, 51, 153));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(Color.WHITE);
                backButton.setForeground(new Color(0, 51, 153));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(0, 51, 153));
                backButton.setForeground(Color.WHITE);
            }
        });
        backButton.addActionListener(e -> {
            dispose(); // Close the current frame
            new HomePage(user);
            // Open the previous frame or navigate back to the previous page
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240)); // Light Gray
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

        displayExpenses();
        displayPocketMoney();
        displayMonthlyExpenses(); // Display monthly expenses
        displayCategoryWiseExpenses(); // Display category-wise expenses
    }

    private JPanel createStatsPanel(String labelText) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(240, 240, 240)); // Light Gray
        JLabel label = new JLabel(labelText);
        panel.add(label);
        return panel;
    }

    private void displayExpenses() {
        try {
            List<EntryDetails> expenses = DatabaseHandler.getExpensesForUser(user.getUserId());

            if (!expenses.isEmpty()) {
                double lowestExpense = expenses.get(0).getAmount();
                double highestExpense = expenses.get(0).getAmount();

                for (EntryDetails expense : expenses) {
                    double amount = expense.getAmount();
                    if (amount < lowestExpense) {
                        lowestExpense = amount;
                    }
                    if (amount > highestExpense) {
                        highestExpense = amount;
                    }
                }

                DecimalFormat df = new DecimalFormat("#.##");
                lowestExpenditureField.setText("Rs." + df.format(lowestExpense));
                highestExpenditureField.setText("Rs." + df.format(highestExpense));
            } else {
                lowestExpenditureField.setText("No expenses recorded");
                highestExpenditureField.setText("No expenses recorded");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to fetch expense data.");
        }
    }

    private void displayPocketMoney() {
        try {
            double pocketMoney = DatabaseHandler.getPocketMoneyForUser(user.getUserId());
            DecimalFormat df = new DecimalFormat("#.##");
            pocketMoneyField.setText("Rs." + df.format(pocketMoney));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to fetch pocket money.");
        }
    }

    private void displayMonthlyExpenses() {
        try {
            List<EntryDetails> expenses = DatabaseHandler.getExpensesForUser(user.getUserId());

            // Initialize a map to store monthly expenses
            Map<String, Double> monthlyExpensesMap = new HashMap<>();

            // Process each entry to calculate monthly expenses
            for (EntryDetails expense : expenses) {
                String date = expense.getDate(); // Assuming date format is "DD-MON-YYYY"
                double amount = expense.getAmount();

                // Extract month and year from the date
                String[] parts = date.split("-");
                String monthYear = parts[1] + "-" + parts[2]; // Format: "MON-YYYY"

                // Update monthly expenses map
                monthlyExpensesMap.put(monthYear, monthlyExpensesMap.getOrDefault(monthYear, 0.0) + amount);
            }

            // Display monthly expenses
            DefaultTableModel model = (DefaultTableModel) monthlyTable.getModel();
            model.setRowCount(0); // Clear previous data
            for (Map.Entry<String, Double> entry : monthlyExpensesMap.entrySet()) {
                String monthYear = entry.getKey();
                double totalExpense = entry.getValue();
                model.addRow(new Object[]{monthYear, "Rs. " + totalExpense});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to fetch monthly expenses.");
        }
    }

    private void displayCategoryWiseExpenses() {
        try {
            List<EntryDetails> expenses = DatabaseHandler.getExpensesForUser(user.getUserId());

            // Initialize a map to store category-wise expenses
            Map<String, Double> categoryExpensesMap = new HashMap<>();

            // Process each entry to calculate category-wise expenses
            for (EntryDetails expense : expenses) {
                String category = expense.getCategory();
                double amount = expense.getAmount();

                // Update category-wise expenses map
                categoryExpensesMap.put(category, categoryExpensesMap.getOrDefault(category, 0.0) + amount);
            }

            // Display category-wise expenses
            DefaultTableModel model = (DefaultTableModel) categoryTable.getModel();
            model.setRowCount(0); // Clear previous data
            for (Map.Entry<String, Double> entry : categoryExpensesMap.entrySet()) {
                String category = entry.getKey();
                double totalExpense = entry.getValue();
                model.addRow(new Object[]{category, "Rs. " + totalExpense});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to fetch category-wise expenses.");
        }
    }
}
