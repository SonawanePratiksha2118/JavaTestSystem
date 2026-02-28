package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.FileOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class CertificatePage extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4;
    JButton b1;
    Connection con;
    String fullName, chapter;
    int marks;

    
    CertificatePage(String username, String chapter, int marks) {
        this.chapter = chapter;
        this.marks = marks;

        setTitle("Certificate");
        setSize(600,450);
        setLocation(450,180);
        setLayout(null);

        fullName = getFullName(username); 
        l1 = new JLabel("📜 Certificate of Achievement");
        l1.setBounds(160,40,400,40);
        l1.setFont(new java.awt.Font("Calibiri", java.awt.Font.BOLD, 22));
        add(l1);

        l2 = new JLabel("This is to certify that");
        l2.setBounds(190,100,300,30);
        l2.setFont(new java.awt.Font("Calibiri", java.awt.Font.PLAIN, 16));
        add(l2);

        l3 = new JLabel(fullName + " has successfully completed " + chapter);
        l3.setBounds(70,140,500,30);
        l3.setFont(new java.awt.Font("Calibiri", java.awt.Font.PLAIN, 16));
        add(l3);

        l4 = new JLabel("with a score of " + marks + " / 10. Well done!");
        l4.setBounds(140,180,350,30);
        l4.setFont(new java.awt.Font("Calibiri", java.awt.Font.PLAIN, 16));
        add(l4);

        JLabel congrats = new JLabel("🎉 Congratulations!");
        congrats.setBounds(170,240,300,40);
        congrats.setFont(new java.awt.Font("Calibiri", java.awt.Font.BOLD, 20));
        add(congrats);

        
        b1 = new JButton("Download PDF");
        b1.setBounds(200,310,160,40);
        b1.addActionListener(this);
        add(b1);

        setVisible(true);
    }

    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == b1) {
            generatePDF();
        }
    }

    
    void generatePDF() {
        try {
            
            String folderPath = "D:\\TrainingAndCertificationSystem\\Certificates\\";
            java.io.File folder = new java.io.File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs(); 
            }

            String fileName = folderPath + "Certificate_" + fullName.replace(" ", "_") + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            
            com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(
                com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 24, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font subFont = new com.itextpdf.text.Font(
                com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 16, com.itextpdf.text.Font.NORMAL);
            com.itextpdf.text.Font boldFont = new com.itextpdf.text.Font(
                com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 18, com.itextpdf.text.Font.BOLD);

            
            document.add(new Paragraph("Certificate of Achievement", titleFont));
            document.add(new Paragraph("\nThis is to certify that\n", subFont));
            document.add(new Paragraph(fullName, boldFont));
            document.add(new Paragraph("\nhas successfully completed " + chapter, subFont));
            document.add(new Paragraph("\nwith a score of " + marks + " / 10", subFont));
            document.add(new Paragraph("\n\n🎉 Congratulations!", boldFont));

            document.close();

            JOptionPane.showMessageDialog(this, "🎉 PDF Certificate saved at:\n" + fileName);

           
            try {
                String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
                Runtime.getRuntime().exec(new String[]{chromePath, fileName});
            } catch (Exception ex) {
                
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(new java.io.File(fileName));
                } else {
                    JOptionPane.showMessageDialog(this, "PDF saved but cannot be opened automatically.");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error creating PDF: " + e.getMessage());
        }
    }


   
    String getFullName(String username) {
        String name = username;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/training_system","root","");
            PreparedStatement ps = con.prepareStatement(
                "SELECT first_name, middle_name, last_name FROM users WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                name = rs.getString("first_name") + " " +
                       rs.getString("middle_name") + " " +
                       rs.getString("last_name");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching name: " + e.getMessage());
        }
        return name;
    }
}

