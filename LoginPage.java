import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginBtn;

    LoginPage() {
        setTitle("User Login");
        setLayout(null);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Username:");
        l1.setBounds(50, 50, 100, 20);
        add(l1);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 150, 20);
        add(usernameField);

        JLabel l2 = new JLabel("Password:");
        l2.setBounds(50, 100, 100, 20);
        add(l2);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 20);
        add(passwordField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 150, 100, 30);
        loginBtn.addActionListener(this);
        add(loginBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_management", "root", "password");
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                new Dashboard(username);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username/Password");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
