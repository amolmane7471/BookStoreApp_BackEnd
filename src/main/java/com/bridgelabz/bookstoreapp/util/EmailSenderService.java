package com.bridgelabz.bookstoreapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
@Component
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailsender;

    public void sendEmailToUser(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mailsender7471@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailsender.send(message);
        System.out.println("Mail sent to the User...!");

    }
//    public static void sendEmailToUser( String subject, String messageText){
//
//        String from = "mailsender7471@gmail.com";
//        String to = "mailsender7471@gmail.com";
//
//
//        String host = "smtp.gmail.com";
//        Properties properties = System.getProperties();
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", "465");
//        properties.put("mail.smtp.ssl.enable", "true");
//        properties.put("mail.smtp.auth", "true");
//
//
//        Session session = Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(from, "ivkcyhbvysiotmvk");
//            }
//        });
//
//        try{
//
//             MimeMessage message = new MimeMessage(session);
//            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//            message.setSubject(subject);
//            message.setText(messageText);
//
//            System.out.println("Sending the message "+messageText);
//            Transport.send(message);
//            System.out.println("Message sent successfully");
//
//
//        }catch (MessagingException e){
//            e.printStackTrace();
//        }
//
//    }

}
