package ui;

import Database.DatabaseHandler;
import Database.DateConverter;
import Database.EntryDetails;
import Database.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class AddNewEntry extends JFrame {

    private JPanel contentPane;
    private User user;

    // Constructor
    public AddNewEntry(User user) {
        this.user = user;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(135, 206, 235));

        // Title label
        JLabel lblNewEntry = new JLabel("ADD NEW ENTRY");
        lblNewEntry.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewEntry.setBounds(150, 20, 200, 30);
        contentPane.add(lblNewEntry);

        // Entry type label and radio buttons
        JLabel lblEntryType = new JLabel("Entry Type:");
        lblEntryType.setFont(new Font("Arial", Font.PLAIN, 16));
        lblEntryType.setBounds(40, 80, 100, 20);
        contentPane.add(lblEntryType);
        ButtonGroup entryTypeGroup = new ButtonGroup();
        JRadioButton rdbtnIncome = new JRadioButton("Income");
        rdbtnIncome.setFont(new Font("Arial", Font.PLAIN, 16));
        rdbtnIncome.setBounds(150, 80, 100, 20);
        contentPane.add(rdbtnIncome);
        entryTypeGroup.add(rdbtnIncome);
        JRadioButton rdbtnExpense = new JRadioButton("Expense");
        rdbtnExpense.setFont(new Font("Arial", Font.PLAIN, 16));
        rdbtnExpense.setBounds(280, 80, 100, 20);
        contentPane.add(rdbtnExpense);
        entryTypeGroup.add(rdbtnExpense);

        // Date label and text field
        JLabel lbDate = new JLabel("Date:");
        lbDate.setFont(new Font("Arial", Font.PLAIN, 16));
        lbDate.setBounds(40, 120, 100, 20);
        contentPane.add(lbDate);
        String datePlaceholder = "DD-MON-YYYY"; // Placeholder format similar to SQL*Plus
        JFormattedTextField formattedTextFieldDate = new JFormattedTextField();
        formattedTextFieldDate.setFont(new Font("Arial", Font.PLAIN, 16));
        formattedTextFieldDate.setBounds(150, 120, 200, 20);
        formattedTextFieldDate.setText(datePlaceholder); // Set placeholder text
        contentPane.add(formattedTextFieldDate);

        // Category label and combo box
        JLabel lblCategory = new JLabel("Category:");
        lblCategory.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCategory.setBounds(40, 160, 100, 20);
        contentPane.add(lblCategory);
        JComboBox<String> comboBoxCategory = new JComboBox<String>();
        comboBoxCategory.setFont(new Font("Arial", Font.PLAIN, 16));
        comboBoxCategory.setBounds(150, 160, 200, 20);
        comboBoxCategory.addItem("Food");
        comboBoxCategory.addItem("Transportation");
        comboBoxCategory.addItem("Entertainment");
        comboBoxCategory.addItem("Utilities");
        comboBoxCategory.addItem("Others");
        contentPane.add(comboBoxCategory);

        // Amount label and text field
        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setFont(new Font("Arial", Font.PLAIN, 16));
        lblAmount.setBounds(40, 200, 100, 20);
        contentPane.add(lblAmount);
        JTextField textFieldAmount = new JTextField();
        textFieldAmount.setFont(new Font("Arial", Font.PLAIN, 16));
        textFieldAmount.setBounds(150, 200, 200, 20);
        contentPane.add(textFieldAmount);

        // Description label and text area
        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setFont(new Font("Arial", Font.PLAIN, 16));
        lblDescription.setBounds(40, 240, 100, 20);
        contentPane.add(lblDescription);
        JTextArea textAreaDescription = new JTextArea();
        textAreaDescription.setFont(new Font("Arial", Font.PLAIN, 16));
        textAreaDescription.setBounds(150, 240, 300, 80);
        contentPane.add(textAreaDescription);

        // Mode of payment label and combo box
        JLabel lblModeOfPayment = new JLabel("Mode of Payment:");
        lblModeOfPayment.setFont(new Font("Arial", Font.PLAIN, 16));
        lblModeOfPayment.setBounds(40, 340, 150, 20);
        contentPane.add(lblModeOfPayment);
        JComboBox<String> comboBoxPaymentMode = new JComboBox<String>();
        comboBoxPaymentMode.setFont(new Font("Arial", Font.PLAIN, 16));
        comboBoxPaymentMode.setBounds(200, 340, 200, 20);
        comboBoxPaymentMode.addItem("Cash");
        comboBoxPaymentMode.addItem("Credit Card");
        comboBoxPaymentMode.addItem("Debit Card");
        comboBoxPaymentMode.addItem("Online Transfer");
        contentPane.add(comboBoxPaymentMode);

        // Save button
        JButton btnSave = new JButton("Save");
        btnSave.setFont(new Font("Arial", Font.PLAIN, 16));
        btnSave.setBounds(100, 400, 100, 30);
        btnSave.setBackground(new Color(0, 51, 153));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.setBorderPainted(false);
        contentPane.add(btnSave);

        // Clear button
        JButton btnClear = new JButton("Clear");
        btnClear.setFont(new Font("Arial", Font.PLAIN, 16));
        btnClear.setBounds(220, 400, 100, 30);
        btnClear.setBackground(new Color(0, 51, 153));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFocusPainted(false);
        btnClear.setBorderPainted(false);
        contentPane.add(btnClear);

        // Exit button
        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Arial", Font.PLAIN, 16));
        btnExit.setBounds(340, 400, 100, 30);
        btnExit.setBackground(new Color(0, 51, 153));
        btnExit.setForeground(Color.WHITE);
        btnExit.setFocusPainted(false);
        btnExit.setBorderPainted(false);
        contentPane.add(btnExit);


        // Save button action listener
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get values from UI components
                    String entryType = rdbtnIncome.isSelected() ? "Income" : "Expense";
                    String date = DateConverter.convertToSQLDateFormat(formattedTextFieldDate.getText());

                    String category = (String) comboBoxCategory.getSelectedItem();
                    String amountStr = textFieldAmount.getText();
                    double amount = 0.0;
                    try {
                        amount = Double.parseDouble(amountStr);
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Invalid amount format: " + amountStr);
                    }
                    String description = textAreaDescription.getText();
                    String paymentMode = (String) comboBoxPaymentMode.getSelectedItem();

                    // Create EntryDetails object
                    EntryDetails entry = new EntryDetails(entryType, date, category, amount, description, paymentMode);
                    // Insert entry into database
                    DatabaseHandler.insertEntry(entry, user);

                    dispose();
                    new HomePage(user);
                    // Display success message
                    JOptionPane.showMessageDialog(null, "Entry added successfully.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: Failed to add entry.");
                }
            }
        });

        // Clear button action listener
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Clear all input fields
                formattedTextFieldDate.setText("");
                textFieldAmount.setText("");
                textAreaDescription.setText("");
                // Clear any other fields if needed
            }
        });

        // Exit button action listener
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                dispose();
                new HomePage(user);

            }
        });
        // Add hover effect to buttons
        MouseListener buttonHover = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.setBackground(Color.WHITE);
                btn.setForeground(new Color(0, 51, 153));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.setBackground(new Color(0, 51, 153));
                btn.setForeground(Color.WHITE);
            }
        };

// Add hover effect to Save button
        btnSave.addMouseListener(buttonHover);

// Add hover effect to Clear button
        btnClear.addMouseListener(buttonHover);

// Add hover effect to Exit button
        btnExit.addMouseListener(buttonHover);

        // Make the frame appear at the center of the screen
        setLocationRelativeTo(null);
        // Make the frame visible
        setVisible(true);
    }
}