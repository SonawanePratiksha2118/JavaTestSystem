package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResultPage extends JFrame implements ActionListener {
    JButton b1,b2,b3;
    String username, chapter;
    int score;

    ResultPage(String user, String chap, int marks) {
        username = user;
        chapter = chap;
        score = marks;

        setSize(550,400);
        setLocation(450,200);
        setLayout(null);

        JLabel l1 = new JLabel("🎉 Congratulations, " + username + "!");
        l1.setBounds(120,50,400,40);
        l1.setFont(new Font("Calibiri",Font.BOLD,22));
        add(l1);

        JLabel l2 = new JLabel("You have completed: " + chapter);
        l2.setBounds(140,120,400,30);
        add(l2);

        JLabel l3 = new JLabel("Your Score: " + score + " / 10");
        l3.setBounds(180,160,200,30);
        add(l3);

        b1 = new JButton("OK / Dashboard");
        b1.setBounds(60,250,140,40);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Retake Quiz");
        b2.setBounds(210,250,130,40);
        b2.addActionListener(this);
        add(b2);

        b3 = new JButton("Get Certificate");
        b3.setBounds(350,250,140,40);
        b3.addActionListener(this);
        add(b3);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==b1) {
            setVisible(false);
            new Dashboard(username);
        } else if(ae.getSource()==b2) {
            setVisible(false);
            new StudentQuiz(username, chapter);
        } else if(ae.getSource()==b3) {
            setVisible(false);
            new CertificatePage(username, chapter, score);
        }
    }
}
