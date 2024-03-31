package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage {
    public static void main(String[] args) {
        // Create the main window
        JFrame frame = new JFrame("PocketPal - A Student Expense Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400); // Adjust the window size
        frame.getContentPane().setBackground(new Color(135, 206, 235)); // Sky blue color

        // Create a panel for login and signup options
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(0, 102, 204)); // Dark blue color
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10); // Padding for labels
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        gbc.weighty = 1; // Vertical weight
        gbc.weightx = 1; // Horizontal weight

        // Create the login button with label
        JButton loginButton = new JButton("LOGIN");
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(0, 51, 153)); // Dark blue color
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(120, 40)); // Button size
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current login frame
                frame.dispose();

                // Open the signup frame
                new LoginPage();

            }
        });

        // Create the signup button with label
        JButton signupButton = new JButton("SIGNUP");
        signupButton.setForeground(Color.WHITE);
        signupButton.setBackground(new Color(0, 51, 153)); // Dark blue color
        signupButton.setBorderPainted(false);
        signupButton.setFocusPainted(false);
        signupButton.setPreferredSize(new Dimension(120, 40)); // Button size
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current login frame
                frame.dispose();

                // Open the signup frame
                new SignupPage();

            }
        });

        // Create the login label
        JLabel loginLabel = new JLabel("Already have an account?");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center alignment
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Font settings
        loginLabel.setForeground(Color.WHITE); // Text color

        // Create the signup label
        JLabel signupLabel = new JLabel("Don't have an account? Create a new one");
        signupLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center alignment
        signupLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Font settings
        signupLabel.setForeground(Color.WHITE); // Text color

        // Add components to the panel
        panel.add(loginLabel, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(5, 10, 2, 10); // Reduce space between label and button
        panel.add(loginButton, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 10, 0, 10); // Padding for labels
        panel.add(signupLabel, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(5, 10, 2, 10); // Reduce space between label and button
        panel.add(signupButton, gbc);

        // Load and resize the logo image
        ImageIcon originalIcon = new ImageIcon("src/logo.png"); // Replace with your logo image path
        Image originalImage = originalIcon.getImage();
        int newWidth = 200; // New width for the image
        int newHeight = 200; // New height for the image
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledIcon);

        // Set up the logo panel
        JPanel logoPanel = new JPanel(new GridBagLayout());
        logoPanel.setOpaque(false); // Make the panel transparent
        GridBagConstraints logoGbc = new GridBagConstraints();
        logoGbc.gridx = 0;
        logoGbc.gridy = 0;
        logoPanel.add(logoLabel, logoGbc);

        // Set up the layout using GridBagLayout for the main content pane
        frame.setLayout(new GridBagLayout());
        GridBagConstraints frameConstraints = new GridBagConstraints();
        frameConstraints.gridx = 0;
        frameConstraints.gridy = 0;
        frameConstraints.weightx = 1.0;
        frameConstraints.weighty = 1.0;
        frameConstraints.fill = GridBagConstraints.BOTH;
        frame.getContentPane().add(panel, frameConstraints);
        frameConstraints.gridx = 1;
        frameConstraints.anchor = GridBagConstraints.CENTER;
        frame.getContentPane().add(logoPanel, frameConstraints);

        frame.setVisible(true);
    }
}
