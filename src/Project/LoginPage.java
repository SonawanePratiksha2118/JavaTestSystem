package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {
    JButton b1,b2;
    JTextField t1,t2;

    LoginPage() {
        setSize(550,400);
        setLocation(450,200);
        setLayout(null);

        JLabel l1 = new JLabel("Login Page");
        l1.setBounds(190,20,400,40);
        l1.setFont(new Font("Calibiri",Font.PLAIN,28));
        add(l1);

        JLabel l2 = new JLabel("Username");
        l2.setBounds(70,100,400,40);
        add(l2);

        t1 = new JTextField();
        t1.setBounds(200,100,250,35);
        add(t1);

        JLabel l3 = new JLabel("Password");
        l3.setBounds(70,200,400,40);
        add(l3);

        t2 = new JTextField();
        t2.setBounds(200,200,250,35);
        add(t2);

        b1 = new JButton("Login");
        b1.setBounds(100,290,100,40);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Signup");
        b2.setBounds(300,290,100,40);
        b2.addActionListener(this);
        add(b2);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==b1) {
            String user = t1.getText();
            String pass = t2.getText();
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/training_system","root","Pr@1817tiksha");
                PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
                ps.setString(1,user);
                ps.setString(2,pass);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    setVisible(false);
                    new Dashboard(user);
                } else {
                    JOptionPane.showMessageDialog(this,"Invalid Username or Password!");
                }
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
            }
        } else if(ae.getSource()==b2) {
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
