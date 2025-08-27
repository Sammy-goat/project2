import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RegistrationPage extends JFrame implements ActionListener {
    // Components
    JRadioButton emailOption, phoneOption;
    JTextField firstNameField, lastNameField, usernameField, emailField, phoneField;
    JPasswordField passwordField;
    JButton registerBtn;
    ButtonGroup group;

    RegistrationPage() {
        // Frame Setup
        setTitle("User Registration");
        setLayout(null);
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Registration Options
        JLabel l0 = new JLabel("Register via:");
        l0.setBounds(50, 30, 100, 20);
        add(l0);

        emailOption = new JRadioButton("Email");
        phoneOption = new JRadioButton("Phone");
        group = new ButtonGroup();
        group.add(emailOption);
        group.add(phoneOption);
        emailOption.setBounds(150, 30, 80, 20);
        phoneOption.setBounds(230, 30, 80, 20);
        add(emailOption);
        add(phoneOption);

        // First Name
        JLabel l1 = new JLabel("First Name:");
        l1.setBounds(50, 70, 100, 20);
        add(l1);

        firstNameField = new JTextField();
        firstNameField.setBounds(150, 70, 150, 20);
        add(firstNameField);

        // Last Name
        JLabel l2 = new JLabel("Last Name:");
        l2.setBounds(50, 110, 100, 20);
        add(l2);

        lastNameField = new JTextField();
        lastNameField.setBounds(150, 110, 150, 20);
        add(lastNameField);

        // Username
        JLabel l3 = new JLabel("Username:");
        l3.setBounds(50, 150, 100, 20);
        add(l3);

        usernameField = new JTextField();
        usernameField.setBounds(150, 150, 150, 20);
        add(usernameField);

        // Password
        JLabel l4 = new JLabel("Password:");
        l4.setBounds(50, 190, 100, 20);
        add(l4);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 190, 150, 20);
        add(passwordField);

        // Email
        JLabel l5 = new JLabel("Email:");
        l5.setBounds(50, 230, 100, 20);
        l5.setVisible(false);
        add(l5);

        emailField = new JTextField();
        emailField.setBounds(150, 230, 150, 20);
        emailField.setVisible(false);
        add(emailField);

        // Phone Number
        JLabel l6 = new JLabel("Phone:");
        l6.setBounds(50, 270, 100, 20);
        l6.setVisible(false);
        add(l6);

        phoneField = new JTextField();
        phoneField.setBounds(150, 270, 150, 20);
        phoneField.setVisible(false);
        add(phoneField);

        // Register Button
        registerBtn = new JButton("Register");
        registerBtn.setBounds(150, 320, 100, 30);
        registerBtn.addActionListener(this);
        add(registerBtn);

        // Show fields based on selection
        emailOption.addActionListener(e -> {
            l5.setVisible(true);
            emailField.setVisible(true);
            l6.setVisible(false);
            phoneField.setVisible(false);
        });

        phoneOption.addActionListener(e -> {
            l5.setVisible(false);
            emailField.setVisible(false);
            l6.setVisible(true);
            phoneField.setVisible(true);
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailOption.isSelected() ? emailField.getText() : null;
        String phone = phoneOption.isSelected() ? phoneField.getText() : null;

        // Validation
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || (email == null && phone == null)) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        // Database Connection and Insertion
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_management", "root", "password");

            String query = "INSERT INTO users (first_name, last_name, username, password, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, username);
            pst.setString(4, password);
            pst.setString(5, email);
            pst.setString(6, phone);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "User Registered Successfully!");
                new LoginPage();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to Register!");
            }

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new RegistrationPage();
    }
}