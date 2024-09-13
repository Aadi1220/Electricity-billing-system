package electricity.billing.system;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener {
    Choice loginAsCho;
    TextField meterText,employerText,userNameText,nameText;
    JPasswordField passwordText;
    JButton create,back;
    Signup(){
        super("Sign up");
        getContentPane().setBackground(new Color(61, 178, 231, 250));

        JLabel createAs = new JLabel("Create Account as:");
        createAs.setBounds(30,50,120,20);
        add(createAs);

        loginAsCho = new Choice();

        loginAsCho.add("Admin");
        loginAsCho.add("Customer");
        loginAsCho.setBounds(170,50,120,20);
        add(loginAsCho);

        JLabel meterNo = new JLabel("Meter Id");
        meterNo.setBounds(30,100,120,20);
        meterNo.setVisible(false);
        add(meterNo);

        meterText = new TextField();
        meterText.setBounds(170,100,120,20);
        meterText.setVisible(false);
        add(meterText);

        JLabel employer = new JLabel("Employer Id");
        employer.setBounds(30,100,120,20);
        employer.setVisible(true);
        add(employer);


        employerText = new TextField();
        employerText.setBounds(170,100,120,20);
        employerText.setVisible(true);
        add(employerText);

        JLabel userName = new JLabel("Username");
        userName.setBounds(30,145,120,20);
        add(userName);

        userNameText = new TextField();
        userNameText.setBounds(170,145,120,20);
        add(userNameText);

        JLabel name = new JLabel("Name");
        name.setBounds(30,190,100,20);
        add(name);

        nameText = new TextField("");
        nameText.setBounds(170,190,120,20);
        add(nameText);

        meterText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    database c = new database();
                    ResultSet resultSet = c.statement.executeQuery("select * from Signup where meter_no ='"+meterText.getText()+"'");
                    if (resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                    }
                }
                catch (Exception E){
                    E.printStackTrace();
                }

            }
        });

        JLabel password = new JLabel("Password");
        password.setBounds(30,235,120,20);
        add(password);

        passwordText = new JPasswordField();
        passwordText.setBounds(170,235,120,20);
        add(passwordText);

        JCheckBox showPassword = new JCheckBox();
        showPassword.setBounds(300, 235, 20, 20);
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

        loginAsCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = loginAsCho.getSelectedItem();
                if(user.equals("Customer")){
                    employer.setVisible(false);
                    nameText.setEditable(false);
                    employerText.setVisible(false);
                    meterNo.setVisible(true);
                    meterText.setVisible(true);
                }
                else{
                    employer.setVisible(true);
                    employerText.setVisible(true);
                    meterNo.setVisible(false);
                    meterText.setVisible(false);
                }

            }
        });

        create = new JButton("Sign up");
        create.setBackground(new Color(3, 231, 252, 181));
        create.setBorder(new BevelBorder(BevelBorder.RAISED));
        create.setBounds(50,280,100,20);
        create.addActionListener(this);
        add(create);

        back = new JButton("Back");
        back.setBorder(new BevelBorder(BevelBorder.LOWERED));
        back.setBackground(new Color(252, 69, 3, 234));
        back.setBounds(190,280,100,20);
        back.addActionListener(this);
        add(back);

        ImageIcon boyIcon = new ImageIcon(ClassLoader.getSystemResource("icon/ready.png"));
        Image boyImg = boyIcon.getImage().getScaledInstance(400,500,Image.SCALE_AREA_AVERAGING);
        ImageIcon ready = new ImageIcon(boyImg);

        JLabel ready1 = new JLabel(ready);
        ready1.setBounds(300,15,300,350);
        add(ready1);

        setSize(600,380);
        setLocation(500,200);
        setLayout(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==create){
            String sloginAs = loginAsCho.getSelectedItem();
            String susername = userNameText.getText();
            String sname = nameText.getText();
            String spassword = passwordText.getText();
            String smeter = meterText.getText();
            try{
                database c = new database();
                String query = null;
                if (loginAsCho.equals("Admin")) {

                    query = "insert into Signup value('" + smeter + "','" + susername + "','" + sname + "','" + spassword + "','" + sloginAs + "')";
                }
                else {
                    query = "update Signup set username ='"+susername+"',password ='"+spassword+"',usertype ='"+sloginAs+"' where meter_no = '"+smeter+"'";
                }

                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Account created successfully");
                setVisible(false);
                new Login();
            }
            catch (Exception E){
                E.printStackTrace();
            }

        } else if (e.getSource()==back) {
            setVisible(false);
            new Login();

        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
