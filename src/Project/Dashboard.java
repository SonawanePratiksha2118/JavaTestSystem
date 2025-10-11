package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {
    JButton[] topicButtons = new JButton[6];
    String[] topics = {
        "Introduction to Java", 
        "Variables and Data Types", 
        "Control Statements",
        "OOP Concepts",
        "Exception Handling",
        "Arrays and Strings"
    };
    String username;

    Dashboard(String user) {
        username = user;
        setTitle("Java Training Dashboard");
        setSize(600,600);
        setLocation(450,150);
        setLayout(null);

        JLabel l1 = new JLabel("Welcome " + user + " 👋");
        l1.setBounds(200,20,300,40);
        l1.setFont(new Font("Calibiri",Font.BOLD,22));
        add(l1);

        int y = 100;
        for(int i=0; i<topics.length; i++) {
            topicButtons[i] = new JButton("Take Quiz: " + topics[i]);
            topicButtons[i].setBounds(150, y, 300, 40);
            topicButtons[i].addActionListener(this);
            add(topicButtons[i]);
            y += 50;
        }

        JButton exit = new JButton("Exit");
        exit.setBounds(150, 430, 300, 40);
        exit.addActionListener(e -> {
            new LoginPage();
            dispose();
        });
        add(exit);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<topics.length; i++) {
            if(e.getSource() == topicButtons[i]) {
                setVisible(false);
                new StudentQuiz(username, topics[i]);
            }
        }
    }
}
