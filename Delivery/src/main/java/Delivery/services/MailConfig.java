package Delivery.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by LevelNone on 23.04.2017.
 */
@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("howdeliveryworks@gmail.com");
        javaMailSender.setPassword("LolKekChebyrek1");

        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.setProperty("mail.debug", "true");
        return properties;
    }

    @Bean
    public SimpleMailMessage preConfiguredMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("howdeliveryworks@gmail.com");
        simpleMailMessage.setFrom("howdeliveryworks@gmail.com");
        simpleMailMessage.setSubject("FATAL - Application crash. Save your job !!");
        return simpleMailMessage;
    }




}
