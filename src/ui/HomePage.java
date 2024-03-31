package ui;

import Database.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {
    public HomePage(User user) {
        // Create the main window
        JFrame frame = new JFrame("PocketPal - A Student Expense Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(135, 206, 235)); // Sky blue color
        frame.setLayout(new BorderLayout());

        // Create the title label
        JLabel titleLabel = new JLabel("Home", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Larger font size
        titleLabel.setForeground(Color.BLACK);
        frame.add(titleLabel, BorderLayout.NORTH); // Title label at the top

        // Create buttons panel
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setBackground(new Color(135, 206, 235)); // Sky blue color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Spacing between buttons

        // Create the add category button
        JButton addCategoryButton = new JButton("Add Category");
        customizeButton(addCategoryButton);
        buttonsPanel.add(addCategoryButton, gbc);

        // Create the add new entry button
        gbc.gridx = 1; // Move to the next column
        JButton addNewEntryButton = new JButton("Add New Entry");
        customizeButton(addNewEntryButton);
        buttonsPanel.add(addNewEntryButton, gbc);
        addNewEntryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current login frame
                frame.dispose();

                // Open the addNewEntry frame
                try {
                    AddNewEntry frame = new AddNewEntry();
                    frame.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        // Create the view button
        gbc.gridx = 0; // Reset column to the first column
        gbc.gridy++; // Move to the next row
        JButton viewButton = new JButton("View");
        customizeButton(viewButton);
        buttonsPanel.add(viewButton, gbc);

        // Create the delete a record button
        gbc.gridx = 1; // Move to the next column
        JButton deleteButton = new JButton("Delete a Record");
        customizeButton(deleteButton);
        buttonsPanel.add(deleteButton, gbc);

        // Create the statistics button
        gbc.gridx = 0; // Reset column to the first column
        gbc.gridy++; // Move to the next row
        gbc.gridwidth = 2; // Span two columns
        JButton statisticsButton = new JButton("Statistics");
        customizeButton(statisticsButton);
        buttonsPanel.add(statisticsButton, gbc);

        // Increase the size of each button by 2x
        Dimension buttonSize = new Dimension(240, 60);
        addCategoryButton.setPreferredSize(buttonSize);
        addNewEntryButton.setPreferredSize(buttonSize);
        viewButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        statisticsButton.setPreferredSize(buttonSize);

        frame.add(buttonsPanel, BorderLayout.CENTER); // Buttons panel in the center

        // Set size and make visible
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Method to customize button properties
    private static void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 51, 153)); // Dark blue color
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {

    }
}
