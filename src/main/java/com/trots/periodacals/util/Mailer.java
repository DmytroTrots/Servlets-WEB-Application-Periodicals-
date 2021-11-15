package com.trots.periodacals.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mailer {
    public static void send(String to, String subject, String message) throws MessagingException {
        final String user="roiten27@gmail.com";//change accordingly
        final String pass="27052k17800";

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        // below mentioned mail.smtp.port is optional
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(user,pass);
            }
        });

        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage mimeMessage = new MimeMessage(session);

            // Set From: header field of the header.
            mimeMessage.setFrom(new InternetAddress(user));

            // Set To: header field of the header.
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            mimeMessage.setSubject(subject);

            // Now set the actual message
            mimeMessage.setText(message);

            System.out.println("sending...");
            // Send message
            Transport.send(mimeMessage);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }


}
