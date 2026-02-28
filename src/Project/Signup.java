package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {
    JButton b3, b4;
    JTextField t1, t2, t3, t4, t5;

    Signup() {
        setSize(550,500);
        setLocation(450,150);
        setLayout(null);

        JLabel l1 = new JLabel("Signup Page");
        l1.setBounds(190,20,400,40);
        l1.setFont(new Font("Calibiri",Font.PLAIN,28));
        add(l1);

        addLabelAndField("First Name", 80, t1 = new JTextField());
        addLabelAndField("Middle Name", 130, t2 = new JTextField());
        addLabelAndField("Last Name", 180, t3 = new JTextField());
        addLabelAndField("Username", 230, t4 = new JTextField());
        addLabelAndField("Password", 280, t5 = new JTextField());

        b3 = new JButton("Signup");
        b3.setBounds(150,360,100,40);
        b3.addActionListener(this);
        add(b3);

        b4 = new JButton("Back");
        b4.setBounds(300,360,100,40);
        b4.addActionListener(this);
        add(b4);

        setVisible(true);
    }

    void addLabelAndField(String text, int y, JTextField field) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(70, y, 400, 40);
        lbl.setFont(new Font("Calibiri", Font.PLAIN, 18));
        add(lbl);
        field.setBounds(200, y, 250, 35);
        add(field);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == b3) {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/training_system","root","");
                PreparedStatement ps = con.prepareStatement("INSERT INTO users(first_name,middle_name,last_name,username,password) VALUES(?,?,?,?,?)");
                ps.setString(1, t1.getText());
                ps.setString(2, t2.getText());
                ps.setString(3, t3.getText());
                ps.setString(4, t4.getText());
                ps.setString(5, t5.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Signup Successful!");
                con.close();
                setVisible(false);
                new LoginPage();
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        } else if(ae.getSource() == b4) {
            setVisible(false);
            new LoginPage();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}

