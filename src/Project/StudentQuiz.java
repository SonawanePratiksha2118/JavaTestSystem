package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentQuiz extends JFrame implements ActionListener {
    JLabel l1;
    JRadioButton r1,r2,r3,r4;
    JButton b1;
    ButtonGroup bg;
    Connection con;
    ResultSet rs;
    int score = 0;
    String username, topic;

    StudentQuiz(String user, String topicName) {
        username = user;
        topic = topicName;

        setTitle("Quiz - " + topic);
        setSize(700,400);
        setLocation(450,200);
        setLayout(null);

        l1 = new JLabel("Question will appear here");
        l1.setBounds(50,50,600,40);
        l1.setFont(new Font("Calibiri",Font.PLAIN,18));
        add(l1);

        r1 = new JRadioButton();
        r2 = new JRadioButton();
        r3 = new JRadioButton();
        r4 = new JRadioButton();
        r1.setBounds(80,100,300,30);
        r2.setBounds(80,140,300,30);
        r3.setBounds(80,180,300,30);
        r4.setBounds(80,220,300,30);

        bg = new ButtonGroup();
        bg.add(r1); bg.add(r2); bg.add(r3); bg.add(r4);

        add(r1); add(r2); add(r3); add(r4);

        b1 = new JButton("Next");
        b1.setBounds(250,290,120,40);
        b1.addActionListener(this);
        add(b1);

        connect();
        loadQuestion();

        setVisible(true);
    }

    void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/training_system","root","Pr@1817tiksha");
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("SELECT * FROM questions WHERE topic_name='" + topic + "'");
            rs.first();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Error in Database: " + e.getMessage());
        }
    }

    void loadQuestion() {
        try {
            l1.setText("Q. " + rs.getString("question_text"));
            r1.setText(rs.getString("option1"));
            r2.setText(rs.getString("option2"));
            r3.setText(rs.getString("option3"));
            r4.setText(rs.getString("option4"));
            bg.clearSelection();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Error Loading Question");
        }
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            int correct = rs.getInt("correct_option");
            int ans = 0;
            if(r1.isSelected()) ans=1;
            else if(r2.isSelected()) ans=2;
            else if(r3.isSelected()) ans=3;
            else if(r4.isSelected()) ans=4;
            if(ans == correct) score++;

            if(!rs.next()) {
                setVisible(false);
                new ResultPage(username, topic, score);
            } else {
                loadQuestion();
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
        }
    }
}
