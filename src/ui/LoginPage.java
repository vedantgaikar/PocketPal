package ui;

import Database.DatabaseHandler;
import Database.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Roboto", Font.PLAIN, 14));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(0, 51, 153)); // Dark blue color
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(120, 40)); // Button size


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
        panel.add(loginButton, gbc);

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

        // Add mouse listener for hover effect for signup
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(Color.WHITE); // Change background color on hover
                loginButton.setForeground(Color.BLUE); // Change text color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(new Color(0, 51, 153)); // Restore default background color
                loginButton.setForeground(Color.WHITE); // Restore default text color
            }
        });
        // Action listener for signup button
        loginButton.addActionListener(new ActionListener() {
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
        // Center the window on the screen
        frame.setLocationRelativeTo(null);
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
