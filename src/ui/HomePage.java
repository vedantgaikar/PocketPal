package ui;

import Database.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePage {
    public HomePage(User user) {
        // Create the main window
        JFrame frame = new JFrame("PocketPal - A Student Expense Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(204, 229, 255)); // Light blue color
        frame.setLayout(new BorderLayout());

        // Create the title label
        JLabel titleLabel = new JLabel("Home", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48)); // Larger font size
        titleLabel.setForeground(Color.BLACK);
        frame.add(titleLabel, BorderLayout.NORTH); // Title label at the top

        // Create buttons panel
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setBackground(new Color(204, 229, 255)); // Light blue color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20); // Spacing between buttons

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
                    AddNewEntry frame = new AddNewEntry(user);
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
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current login frame
                frame.dispose();

                // Open the addNewEntry frame
                try {
                    ViewPage frame = new ViewPage(user);
                    frame.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


        // Create the delete a record button
        gbc.gridx = 1; // Move to the next column
        JButton deleteButton = new JButton("Delete a Record");
        customizeButton(deleteButton);
        buttonsPanel.add(deleteButton, gbc);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current login frame
                frame.dispose();

                // Open the Delete frame
                try {
                    DeletePage frame = new DeletePage(user);
                    frame.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Create the statistics button
        gbc.gridx = 0; // Reset column to the first column
        gbc.gridy++; // Move to the next row
        gbc.gridwidth = 2; // Span two columns
        JButton statisticsButton = new JButton("Statistics");
        customizeButton(statisticsButton);
        buttonsPanel.add(statisticsButton, gbc);
        statisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Statistics();
            }
        });

        // Increase the size of each button by 2x
        Dimension buttonSize = new Dimension(300, 80);
        addCategoryButton.setPreferredSize(buttonSize);
        addNewEntryButton.setPreferredSize(buttonSize);
        viewButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        statisticsButton.setPreferredSize(buttonSize);

        frame.add(buttonsPanel, BorderLayout.CENTER); // Buttons panel in the center

        // Set size and make visible
        frame.setSize(800, 600); // Larger frame size
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Create the exit button
        gbc.gridx = 0; // Reset column to the first column
        gbc.gridy++; // Move to the next row
        gbc.gridwidth = 2; // Span two columns
        JButton exitButton = new JButton("Exit");
        customizeButton(exitButton);
        buttonsPanel.add(exitButton, gbc);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // Close the current frame
                    frame.dispose();
                    // Exit the application
                    System.exit(0);
                }
            }
        });
    }

    // Method to customize button properties
    private static void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font size and bold
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 102, 204)); // Darker blue color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Add hover effect to button
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.WHITE);
                button.setForeground(new Color(0, 102, 204));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 102, 204));
                button.setForeground(Color.WHITE);
            }
        });
    }

    public static void main(String[] args) {
        // Dummy main method
    }
}
