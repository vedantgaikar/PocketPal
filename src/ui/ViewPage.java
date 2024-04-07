package ui;

import Database.DatabaseHandler;
import Database.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewPage extends JFrame {
    private JPanel contentPane;
    private JButton allEntriesButton;
    private JTable entryTable;
    private User user;

    public ViewPage(User user) {
        this.user = user;
        setTitle("View Entries");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // Content panel
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(new Color(0, 0, 102)); // Dark blue background

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);

        // Button for displaying all entries
        allEntriesButton = new JButton("Display All Entries");
        customizeButton(allEntriesButton);

        // Add action listener to the button
        allEntriesButton.addActionListener(e -> displayAllEntries());

        // Add button to the panel
        buttonPanel.add(allEntriesButton);

        // Table for displaying entries
        entryTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(entryTable);

        // Add components to content pane
        contentPane.add(buttonPanel, BorderLayout.NORTH);
        contentPane.add(tableScrollPane, BorderLayout.CENTER);

        setContentPane(contentPane);
    }

    // Method to customize button appearance
    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 102, 204)); // Darker blue color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Padding
    }

    // Method to display all entries
    private void displayAllEntries() {
        try {
            ResultSet resultSet = DatabaseHandler.getAllEntries(user.getUserId());
            if (resultSet != null) {
                populateTable(resultSet);
            }
        } catch (SQLException exception) {
            System.out.println("null set caught");
            exception.printStackTrace();
        }
    }

    // Method to populate table with ResultSet data
    private void populateTable(ResultSet resultSet) throws SQLException {
        // Create a data list to hold the details of each entry
        ArrayList<String[]> data = new ArrayList<>();

        // Populate data from the ResultSet
        while (resultSet.next()) {
            int entry_id = resultSet.getInt("entry_id");
            String entry_idStr = String.valueOf(entry_id);
            int user_id = resultSet.getInt("user_id");
            String user_idStr = String.valueOf(user_id);
            String entryType = resultSet.getString("entry_type");
            String date = resultSet.getString("entry_date");
            String category = resultSet.getString("category");
            double amount = resultSet.getDouble("amount");
            String description = resultSet.getString("description");
            String paymentMode = resultSet.getString("payment_mode");

            // Add padding to the entry details
            String[] entryDetails = {
                    String.valueOf(entry_id),
                    String.valueOf(user_id),
                    date,
                    category,
                    String.format("%.2f", amount),
                    entryType,
                    description,
                    paymentMode
            };

            // Add the entry details to the data list
            data.add(entryDetails);
        }

        // Define column names
        String[] columnNames = {"Entry ID", "User ID", "Date", "Category", "Amount", "Entry Type", "Description", "Payment Mode"};

        // Convert the data list to a 2D array
        String[][] rowData = data.toArray(new String[0][]);

        // Create a table model with the data and column names
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);

        // Create the table with the table model
        JTable table = new JTable(model);

        // Set custom padding for the table cells
        table.setRowHeight(30); // Set row height
        table.setIntercellSpacing(new Dimension(10, 10)); // Set spacing between cells
        table.setGridColor(Color.LIGHT_GRAY); // Set color of grid lines

        // Create a panel to contain the table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the panel
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER); // Add the table to the panel

        // Create a panel for the back button
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back");
        customizeButton(backButton);
        backButton.addActionListener(e -> {
            // Close the current view page
            dispose();

            // Create and display the homepage
            new HomePage(user);
        });
        backButtonPanel.add(backButton);

        // Add the back button panel to the bottom of the table panel
        tablePanel.add(backButtonPanel, BorderLayout.SOUTH);

        // Set the content pane to the table panel
        setContentPane(tablePanel);
        setVisible(true);
    }


    public void main(String[] args) {
        // Sample initialization of User object
        User user = new User("username", "password", "email");

        SwingUtilities.invokeLater(() -> {
            ViewPage viewPage = new ViewPage(user);
            viewPage.setVisible(true);
        });
    }
}




