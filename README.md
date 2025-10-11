Project Description

This is a simple Java quiz project that helps students learn Java topics and get certificates after completing quizzes.
It uses Java Swing for GUI, MySQL for storing data, and iTextPDF for generating certificates.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Features

User signup and login
Dashboard with Java topic
Topic-wise quizzes
Result page with score
Certificate generation in PDF format
PDF opens automatically after download

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Technologies Used

Java (Swing + AWT)
MySQL (JDBC)(I used mysql-connector-j-9.4.0)
iTextPDF Library (I used itextpdf-5.5.13.3 )
Eclipse IDE

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Database Tables

users = stores user details
questions = stores quiz questions and answers

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Files in Project

Signup.java
LoginPage.java
Dashboard.java
StudentQuiz.java
ResultPage.java
CertificatePage.java

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

How to Run

Create database training_system in MySQL.
Create tables users and questions.
Add mysql-connector-j.jar and itextpdf.jar to your project.
Run LoginPage.java to start the application.
