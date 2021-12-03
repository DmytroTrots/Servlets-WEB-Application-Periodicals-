package com.trots.periodacals.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

/**
 * The type Mailer.
 */
public class Mailer {

    /**
     * The constant user.
     */
    final static String user="periodicalsiteepam@gmail.com";//change accordingly
    /**
     * The constant pass.
     */
    final static String pass="passwordEPAM";
    /**
     * The constant ORDER_REPORT_PDF.
     */
    public static final String ORDER_REPORT_PDF = "C:\\Users\\Dima\\Desktop\\periodacals\\src\\main\\webapp\\resources\\images\\order_report.pdf";

    /**
     * Send.
     *
     * @param to      the to
     * @param subject the subject
     * @param message the message
     * @throws MessagingException the messaging exception
     */
    public static void send(String to, String subject, String message) throws MessagingException {
        Session session = getSession(user, pass);
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

    /**
     * Send mail to admin report orders.
     *
     * @param to      the to
     * @param subject the subject
     * @param message the message
     * @throws MessagingException the messaging exception
     */
    public static void sendMailToAdminReportOrders(String to, String subject, String message) throws MessagingException {
        Session session = getSession(user, pass);
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

            Multipart emailContent = new MimeMultipart();

            MimeBodyPart pdfAttachment = new MimeBodyPart();
            pdfAttachment.attachFile(ORDER_REPORT_PDF);

            emailContent.addBodyPart(pdfAttachment);

            mimeMessage.setContent(emailContent);

            System.out.println("sending...");
            // Send message
            Transport.send(mimeMessage);
            System.out.println("Sent message successfully....");
        } catch (MessagingException | IOException mex) {
            mex.printStackTrace();
        }
    }

    private static Session getSession(String user, String pass) {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.starttls.enable", "true");

        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
    }

}
