package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField userText;
    JPasswordField passwordText;
    Choice loginChoice;
    JButton loginButton,cancelButton,signupButton;
    Login(){
        super("Login");
        getContentPane().setBackground(Color.cyan);

        JLabel username = new JLabel("UserName");
        username.setBounds(300,60,100,20);
        add(username);

        userText = new JTextField();
        userText.setBounds(400,60,150,20);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(300,100,100,20);
        add(password);

        passwordText = new JPasswordField();
        passwordText.setBounds(400,100,150,20);
        add(passwordText);


        JCheckBox showPassword = new JCheckBox();
        showPassword.setBounds(560, 100, 20, 20);
        showPassword.setToolTipText("Show Password");
        add(showPassword);


        showPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    passwordText.setEchoChar((char) 0);
                } else {
                    passwordText.setEchoChar('*'); //
                }
            }
        });

        JLabel login = new JLabel("Logging in as:");
        login.setBounds(300,140,100,20);
        add(login);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(400,140,100,20);
        add(loginChoice);

        loginButton = new JButton("Login");
        loginButton.setBounds(330,180,100,20);
        loginButton.setToolTipText("Click here to login");
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(460,180,100,20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton = new JButton("Sign Up");
        signupButton.setBounds(400,220,100,20);
        signupButton.addActionListener(this);
        add(signupButton);

        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image profileTwo = profileOne.getImage().getScaledInstance(300,300,Image.SCALE_AREA_AVERAGING);
        ImageIcon fProfileOne = new ImageIcon(profileTwo);
        JLabel profileLabel = new JLabel(fProfileOne);
        profileLabel.setBounds(5,2,300,300);
        add(profileLabel);

        setSize(640,300);

        setLocation(400,200);

        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loginButton){
            String susername = userText.getText();
            String spassword = passwordText.getText();
            String suser = loginChoice.getSelectedItem();

            try{
                database c = new database();
                String query = "select * from Signup where username = '"+susername+"' and password = '"+spassword+"' and usertype = '"+suser+"'";
                ResultSet resultset = c.statement.executeQuery(query);

                if(resultset.next()){
                    String meter = resultset.getString("meter_no");

                    setVisible(false);
                    new main_class(suser,meter);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid credentials");
                }

            }
            catch (Exception E){
                E.printStackTrace();
            }


        } else if (e.getSource()==cancelButton) {
            setVisible(false);
        } else if (e.getSource()==signupButton) {
            setVisible(false);
            new Signup();

        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
