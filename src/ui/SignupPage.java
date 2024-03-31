package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Database.DatabaseHandler;
import Database.User;
import ui.LoginPage;
import ui.MainPage;

public class SignupPage {
    public static void main(String[] args) {
        new SignupPage();
    }

    public SignupPage() {
        // Create the main window
        JFrame frame = new JFrame("PocketPal - Create New Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 300);

        // Create the title label
        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Decreased font size
        titleLabel.setBounds(70, 10, 300, 40); // Adjusted position and size

        // Create the name label and field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nameLabel.setBounds(40, 60, 100, 20);
        JTextField nameField = new JTextField();
        nameField.setBounds(140, 60, 200, 25);
        nameField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 2));

        // Create the email label and field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setBounds(40, 100, 100, 20);
        JTextField emailField = new JTextField();
        emailField.setBounds(140, 100, 200, 25);
        emailField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 2));

        // Create the password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setBounds(40, 140, 100, 20);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(140, 140, 200, 25);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 2));

        // Create the sign up button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(140, 180, 100, 30);
        signUpButton.setBackground(new Color(0, 51, 153));
        signUpButton.setForeground(Color.WHITE);

        // Create the login label
        JLabel loginLabel = new JLabel("Already Registered?");
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        loginLabel.setBounds(40, 220, 120, 20);

        // Create the login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(160, 220, 80, 20);
        loginButton.setBackground(new Color(0, 51, 153));
        loginButton.setForeground(Color.WHITE);

        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open login page
                new LoginPage();
                frame.dispose(); // Close current frame
            }
        });

        // Add action listener to sign up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get user input
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Check if any of the fields are empty
                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method if any field is empty
                }

                // Create User object
                User user = new User(name, email, password);

                // Pass User object to DatabaseHandler for insertion
                DatabaseHandler.insertUser(user);
                // Optionally, display a message or perform other actions upon successful signup
                frame.dispose();
                new MainPage(); // opens the HomePage
            }
        });

        // Create the panel and add components to it
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(135, 206, 235)); // Sky blue color
        panel.add(titleLabel);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(signUpButton);
        panel.add(loginLabel);
        panel.add(loginButton);

        // Add the panel to the frame and make it visible
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
