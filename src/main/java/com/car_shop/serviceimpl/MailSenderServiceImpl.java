package com.car_shop.serviceimpl;

import com.car_shop.service.MailSenderService;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailSenderServiceImpl implements MailSenderService
{
    private final static String EMAIL_FROM = "murrayandy768@gmail.com"; // your email to sent confirmation to users
    private final static String EMAIL_PASSWORD = "car_shop1"; // your email password

    @Override
    public void sendMail(String theme, String mailBody, String email)
    {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getDefaultInstance(properties,
                new Authenticator()
                {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(EMAIL_FROM, EMAIL_PASSWORD);
                    }
                });
        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    email));
            message.setSubject(theme, "UTF-8");
            message.setText(mailBody);
            synchronized (this)
            {
                Transport.send(message);
            }
        }
        catch (MessagingException mex)
        {
            mex.printStackTrace();
            System.out.println("You have some problems with connection!");
        }
    }
}
