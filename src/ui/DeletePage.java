package ui;

import Database.DatabaseHandler;
import Database.EntryDetails;
import Database.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePage extends JFrame {

    private JLabel labelAmount;
    private JLabel labelDate;
    private JLabel labelCategory;
    private JLabel labelDescription;
    private JLabel labelMode;

    public DeletePage(User user) {
        // Set up the frame
        getContentPane().setBackground(new Color(173, 216, 230)); // Set background color to light blue
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 420); // Increased height to accommodate buttons
        setLocationRelativeTo(null); // Center the frame on screen
        setLayout(null); // Using absolute positioning

        // Label for entering ID
        JLabel labelEnterId = new JLabel("Enter Id for deletion:");
        labelEnterId.setFont(new Font("Arial", Font.BOLD, 18));
        labelEnterId.setBounds(31, 11, 226, 33); // Positioning
        add(labelEnterId);

        // Text field for entering ID
        JTextField textFieldId = new JTextField();
        textFieldId.setFont(new Font("Arial", Font.PLAIN, 14));
        textFieldId.setBounds(227, 16, 150, 30); // Positioning
        add(textFieldId);

        // Button to view details
        JButton buttonViewDetails = new JButton("View Details");
        customizeButton(buttonViewDetails); // Customize button appearance
        buttonViewDetails.setBounds(100, 60, 120, 30); // Positioning
        add(buttonViewDetails);

        // Labels for displaying details
        labelAmount = new JLabel("Amount:");
        labelAmount.setFont(new Font("Arial", Font.PLAIN, 14));
        labelAmount.setBounds(31, 110, 300, 23); // Positioning
        add(labelAmount);

        labelDate = new JLabel("Date:");
        labelDate.setFont(new Font("Arial", Font.PLAIN, 14));
        labelDate.setBounds(31, 150, 300, 23); // Positioning
        add(labelDate);

        labelCategory = new JLabel("Category:");
        labelCategory.setFont(new Font("Arial", Font.PLAIN, 14));
        labelCategory.setBounds(31, 190, 300, 23); // Positioning
        add(labelCategory);

        labelDescription = new JLabel("Description:");
        labelDescription.setFont(new Font("Arial", Font.PLAIN, 14));
        labelDescription.setBounds(31, 230, 300, 23); // Positioning
        add(labelDescription);

        labelMode = new JLabel("Mode:");
        labelMode.setFont(new Font("Arial", Font.PLAIN, 14));
        labelMode.setBounds(31, 270, 300, 23); // Positioning
        add(labelMode);

        // Button to confirm deletion
        JButton buttonConfirmDeletion = new JButton("Confirm Deletion");
        customizeButton(buttonConfirmDeletion); // Customize button appearance
        buttonConfirmDeletion.setBounds(100, 310, 150, 33); // Positioning
        add(buttonConfirmDeletion);

        // Button to cancel deletion
        JButton buttonCancel = new JButton("Cancel");
        customizeButton(buttonCancel); // Customize button appearance
        buttonCancel.setBounds(270, 310, 100, 33); // Positioning
        add(buttonCancel);

        // Action listener for the "View Details" button
        buttonViewDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Simulating fetching data based on ID
                String id = textFieldId.getText();
                if (!id.isEmpty()) {
                    try {
                        int entryId = Integer.parseInt(id);
                        // Fetch entry details from the database based on entry ID
                        EntryDetails entryDetails = DatabaseHandler.getEntryDetailsById(entryId);
                        if (entryDetails != null) {
                            // Set values for the labels based on fetched data
                            labelAmount.setText("Amount: Rs." + entryDetails.getAmount());
                            labelDate.setText("Date: " + entryDetails.getDate());
                            labelCategory.setText("Category: " + entryDetails.getCategory());
                            labelDescription.setText("Description: " + entryDetails.getDescription());
                            labelMode.setText("Mode: " + entryDetails.getPaymentMode());
                        } else {
                            // Reset labels if entry ID not found
                            resetLabels();
                            JOptionPane.showMessageDialog(null, "Entry ID not found.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Entry ID.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter Entry ID.");
                }
            }
        });

        // Action listener for the "Confirm Deletion" button
        buttonConfirmDeletion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the entry ID from the text field
                String id = textFieldId.getText();
                if (!id.isEmpty()) {
                    try {
                        int entryId = Integer.parseInt(id);
                        // Call method in DatabaseHandler to delete the entry
                        if (DatabaseHandler.deleteEntry(entryId)) {
                            // Display a confirmation message
                            JOptionPane.showMessageDialog(null, "Entry deleted successfully.");
                            // Reset labels
                            resetLabels();
                        } else {
                            // Display an error message if deletion fails
                            JOptionPane.showMessageDialog(null, "Failed to delete entry.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Entry ID.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter Entry ID.");
                }
            }
        });
        // Action listener for the "Cancel" button
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame on cancel
                new HomePage(user); // Open the home page
            }
        });
    }

    // Method to reset details labels
    private void resetLabels() {
        labelAmount.setText("Amount:");
        labelDate.setText("Date:");
        labelCategory.setText("Category:");
        labelDescription.setText("Description:");
        labelMode.setText("Mode:");
    }

    // Method to customize button properties
    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 14)); // Larger font size and plain style
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 102, 204)); // Darker blue color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Padding
    }

    public static void main(String[] args) {
        // Entry point
    }
}
