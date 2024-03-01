import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class SnakeGame extends JFrame implements ActionListener{
    JTextField userTextField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton registerButton;
    Connection connection;

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userTextField.getText();
            String password = String.valueOf(passwordField.getPassword());

            if (validateLogin(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                new GameFrame(); // Start the game
                dispose(); // Close the login/register frame
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        } else if (e.getSource() == registerButton) {
            String username = userTextField.getText();
            String password = String.valueOf(passwordField.getPassword());

            if (registerUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Registration Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Registration Failed");
            }
        }
    }

    private boolean validateLogin(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/unisoft", "root", "");

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM login WHERE username=? AND password=?");
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                connection.close();
                return true;
            }
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }


    private boolean registerUser(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/unisoft", "root", "");

            PreparedStatement statement = connection.prepareStatement("INSERT INTO login (username, password) VALUES (?, ?)");
            statement.setString(1, username);
            statement.setString(2, password);

            int rowsInserted = statement.executeUpdate();

            connection.close();

            return rowsInserted > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
	public SnakeGame(){
		
//		new GameFrame();
		 setTitle("Login/Register");
	        setSize(300, 200);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);

	        JPanel panel = new JPanel();
	        panel.setLayout(new GridLayout(4, 2));
	        setLocationRelativeTo(null);

	        JLabel userLabel = new JLabel("Username:");
	        userTextField = new JTextField();
	        JLabel passwordLabel = new JLabel("Password:");
	        passwordField = new JPasswordField();
	        loginButton = new JButton("Login");
	        registerButton = new JButton("Register");

	        loginButton.addActionListener(this);
	        registerButton.addActionListener(this);

	        panel.add(userLabel);
	        panel.add(userTextField);
	        panel.add(passwordLabel);
	        panel.add(passwordField);
	        panel.add(loginButton);
	        panel.add(registerButton);

	        add(panel);
	        setVisible(true);

	}
	public static void main(String [] args) {
		new SnakeGame();
		
	}

}
