import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class HomePage extends JFrame implements ActionListener {
    JButton loginBtn, registerBtn;

    HomePage() {
        setTitle("Event Management System");
        setLayout(null);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel welcome = new JLabel("Welcome to Event Management");
        welcome.setBounds(50, 50, 300, 30);
        add(welcome);

        loginBtn = new JButton("Login");
        registerBtn = new JButton("Register");

        loginBtn.setBounds(100, 100, 200, 30);
        registerBtn.setBounds(100, 150, 200, 30);

        loginBtn.addActionListener(this);
        registerBtn.addActionListener(this);

        add(loginBtn);
        add(registerBtn);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == loginBtn) {
        new LoginPage(); // Navigate to LoginPage
        this.dispose(); // Close the HomePage
    } else if (e.getSource() == registerBtn) {
        new RegistrationPage(); // Navigate to RegistrationPage
        this.dispose(); // Close the HomePage
    }
}

    public static void main(String[] args) {
        new HomePage();
    }
}

// Placeholder for LoginPage class
class LoginPage extends JFrame {
    LoginPage() {
        setTitle("Login Page");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Login Page");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);
        setVisible(true);
    }
}

// Placeholder for RegistrationPage class
class RegistrationPage extends JFrame {
    RegistrationPage() {
        setTitle("Registration Page");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Registration Page");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);
        setVisible(true);
    }
}