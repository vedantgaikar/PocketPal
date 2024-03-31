package ui;

import Database.DatabaseHandler;
import Database.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    public LoginPage() {
        // Create the main window
        JFrame frame = new JFrame("PocketPal - Login into Existing Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); // Adjust the window size
        frame.getContentPane().setBackground(new Color(135, 206, 235)); // Sky blue color

        // Create a panel for signup options
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false); // Make the panel transparent

        // Create text fields for username and password
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        // Create labels for text fields
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        // Create the signup button
        JButton signupButton = new JButton("Login");
        signupButton.setForeground(Color.WHITE);
        signupButton.setBackground(new Color(0, 51, 153)); // Dark blue color
        signupButton.setBorderPainted(false);
        signupButton.setFocusPainted(false);
        signupButton.setPreferredSize(new Dimension(120, 40)); // Button size

        // Add components to the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        panel.add(usernameLabel, gbc);
        gbc.gridy++;
        panel.add(usernameField, gbc);
        gbc.gridy++;
        panel.add(passwordLabel, gbc);
        gbc.gridy++;
        panel.add(passwordField, gbc);
        gbc.gridy++;
        panel.add(signupButton, gbc);

        // Add logo image (ensure "logo.png" is in your project directory or provide the correct path)
        ImageIcon logoIcon = new ImageIcon("logo.png"); // Replace with your logo image
        JLabel logoLabel = new JLabel(logoIcon);

        // Set up the logo panel
        JPanel logoPanel = new JPanel();
        logoPanel.add(logoLabel);
        logoPanel.setOpaque(false); // Make the panel transparent

        // Set up the layout using BorderLayout
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER); // Center for text fields and signup button
        frame.add(logoPanel, BorderLayout.EAST); // Right side for logo

        // Action listener for signup button
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve username and password from text fields
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Validate username and password against the database
                User isValid = validateUser(username, password);

                if (isValid!=null) {
                    // Successful signup
                    JOptionPane.showMessageDialog(frame, "Login successful! Welcome, " + username + "!");
                    frame.dispose();
                    new HomePage(isValid);
                } else {
                    // Invalid username or password
                    JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginPage();
    }
    // Dummy method for validating user against a database
    private static User validateUser(String username, String password) {
        // Query the database to check if a user with the provided username and password exists
        User user = DatabaseHandler.getUserByUsername(username);

        // Check if the user is not null and the password matches
        if (user != null && user.getPassword().equals(password)) {
            return user; // Username and password match
        } else {
            return null; // Username or password is incorrect
        }
    }
}
