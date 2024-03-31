package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AddNewEntry extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddNewEntry frame = new AddNewEntry();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AddNewEntry() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);

        // Initialize contentPane
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Set the background color of the content pane to sky blue
        contentPane.setBackground(new Color(135, 206, 235)); // Sky blue color

        JLabel lblNewEntry = new JLabel("ADD NEW ENTRY");
        lblNewEntry.setFont(new Font("Dialog", Font.BOLD, 18));
        lblNewEntry.setBounds(165, 11, 170, 33);
        contentPane.add(lblNewEntry);

        JLabel lblEntryType = new JLabel("Entry Type:");
        lblEntryType.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblEntryType.setBounds(31, 60, 76, 23);
        contentPane.add(lblEntryType);

        ButtonGroup entryTypeGroup = new ButtonGroup();
        JRadioButton rdbtnIncome = new JRadioButton("Income");
        rdbtnIncome.setFont(new Font("Dialog", Font.PLAIN, 14));
        rdbtnIncome.setBounds(117, 60, 76, 23);
        contentPane.add(rdbtnIncome);
        entryTypeGroup.add(rdbtnIncome);

        JRadioButton rdbtnExpense = new JRadioButton("Expense");
        rdbtnExpense.setFont(new Font("Dialog", Font.PLAIN, 14));
        rdbtnExpense.setBounds(193, 60, 76, 23);
        contentPane.add(rdbtnExpense);
        entryTypeGroup.add(rdbtnExpense);

        JLabel lblDate = new JLabel("Date:");
        lblDate.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblDate.setBounds(31, 96, 48, 23);
        contentPane.add(lblDate);

        JFormattedTextField formattedTextFieldDate = new JFormattedTextField();
        formattedTextFieldDate.setFont(new Font("Dialog", Font.PLAIN, 14));
        formattedTextFieldDate.setBounds(117, 96, 152, 23);
        contentPane.add(formattedTextFieldDate);

        JLabel lblCategory = new JLabel("Category:");
        lblCategory.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblCategory.setBounds(31, 132, 76, 23);
        contentPane.add(lblCategory);

        JComboBox<String> comboBoxCategory = new JComboBox<String>();
        comboBoxCategory.setFont(new Font("Dialog", Font.PLAIN, 14));
        comboBoxCategory.setBounds(117, 131, 152, 23);
        comboBoxCategory.addItem("Food");
        comboBoxCategory.addItem("Transportation");
        comboBoxCategory.addItem("Entertainment");
        comboBoxCategory.addItem("Utilities");
        comboBoxCategory.addItem("Others");
        contentPane.add(comboBoxCategory);

        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblAmount.setBounds(31, 168, 76, 23);
        contentPane.add(lblAmount);

        JTextField textFieldAmount = new JTextField();
        textFieldAmount.setFont(new Font("Dialog", Font.PLAIN, 14));
        textFieldAmount.setBounds(117, 168, 152, 23);
        contentPane.add(textFieldAmount);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblDescription.setBounds(31, 204, 100, 23);
        contentPane.add(lblDescription);

        JTextArea textAreaDescription = new JTextArea();
        textAreaDescription.setFont(new Font("Dialog", Font.PLAIN, 14));
        textAreaDescription.setBounds(117, 204, 280, 60);
        contentPane.add(textAreaDescription);

        JLabel lblTime = new JLabel("Time:");
        lblTime.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblTime.setBounds(31, 280, 76, 23);
        contentPane.add(lblTime);

        JFormattedTextField formattedTextFieldTime = new JFormattedTextField();
        formattedTextFieldTime.setFont(new Font("Dialog", Font.PLAIN, 14));
        formattedTextFieldTime.setBounds(117, 280, 152, 23);
        contentPane.add(formattedTextFieldTime);

        JLabel lblModeOfPayment = new JLabel("Mode of Payment:");
        lblModeOfPayment.setFont(new Font("Dialog", Font.PLAIN, 14));
        lblModeOfPayment.setBounds(31, 316, 120, 23);
        contentPane.add(lblModeOfPayment);

        JComboBox<String> comboBoxPaymentMode = new JComboBox<String>();
        comboBoxPaymentMode.setFont(new Font("Dialog", Font.PLAIN, 14));
        comboBoxPaymentMode.setBounds(161, 316, 152, 23);
        comboBoxPaymentMode.addItem("Cash");
        comboBoxPaymentMode.addItem("Credit Card");
        comboBoxPaymentMode.addItem("Debit Card");
        comboBoxPaymentMode.addItem("Online Transfer");
        contentPane.add(comboBoxPaymentMode);

        JButton btnSave = new JButton("Save");
        btnSave.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnSave.setBounds(157, 356, 89, 23);
        contentPane.add(btnSave);

        JButton btnClear = new JButton("Clear");
        btnClear.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnClear.setBounds(50, 356, 89, 23);
        contentPane.add(btnClear);

        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnExit.setBounds(265, 356, 89, 23);
        contentPane.add(btnExit);

        // Action Listener for Save Button
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add your save functionality here
                // For example, you can retrieve values from the fields and save them to a database
                // Or perform any other action you want when the save button is clicked
                String entryType = rdbtnIncome.isSelected() ? "Income" : "Expense";
                String date = formattedTextFieldDate.getText();
                String category = (String) comboBoxCategory.getSelectedItem();
                String amount = textFieldAmount.getText();
                String description = textAreaDescription.getText();
                String time = formattedTextFieldTime.getText();
                String paymentMode = (String) comboBoxPaymentMode.getSelectedItem();

                // Just printing the values for demonstration
                System.out.println("Entry Type: " + entryType);
                System.out.println("Date: " + date);
                System.out.println("Category: " + category);
                System.out.println("Amount: " + amount);
                System.out.println("Description: " + description);
                System.out.println("Time: " + time);
                System.out.println("Mode of Payment: " + paymentMode);
            }
        });

        // Action Listener for Clear Button
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                formattedTextFieldDate.setText("");
                textFieldAmount.setText("");
                textAreaDescription.setText("");
                formattedTextFieldTime.setText("");
                // Clear any other fields if needed
            }
        });

        // Action Listener for Exit Button
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}