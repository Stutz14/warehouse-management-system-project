package loginServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import client.MainClientUI;

public class LoginWindow implements ActionListener{

    // private final static String FILE_PATH = "userData.json";
    public static JLabel userLabel;
    public static JTextField userText;
    public static JLabel passwordLabel;
    public static JPasswordField passwordText;
    public static JButton button;
    public static JLabel success;


    public  void LoginWindow() {

        JPanel panel = new JPanel();
        final JFrame frame = new JFrame();
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        //frame.getContentPane().add(panel,"Center");

        panel.setLayout(null);

        userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        button = new JButton("Submit!");
        button.setBounds(100, 100, 100, 25);
        button.addActionListener((ActionListener) new LoginWindow());
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {frame.dispose();}
        });
        panel.add(button);

        success = new JLabel("");
        success.setBounds(100, 130, 300, 25);
        panel.add(success);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {


    	String user = userText.getText();
        String password = new String(passwordText.getPassword());

        AuthenticateUser userObj = new AuthenticateUser();

        if (userObj.authenticate(user, password)) {
            success.setText("Login successful");
            JOptionPane.showMessageDialog(null, "Application Launching", "Login successful", JOptionPane.INFORMATION_MESSAGE);
            MainClientUI.startClientUI();
            
        } else {
            success.setText("Login unsuccessful");
            JOptionPane.showMessageDialog(null, "Application Terminated!", "Login unsuccessful", JOptionPane.ERROR_MESSAGE);
        }

    }
}
