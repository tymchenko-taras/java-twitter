package com.example.sweater.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Value("${sweater.mail.protocol}")
    private String protocol;
    @Value("${sweater.mail.host}")
    private String host;
    @Value("${sweater.mail.port}")
    private Integer port;
    @Value("${sweater.mail.username}")
    private String username;
    @Value("${sweater.mail.password}")
    private String password;
    @Value("${sweater.mail.debug}")
    private String debug;

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl implementation = new JavaMailSenderImpl();
//        implementation.setProtocol(protocol);
        implementation.setHost(host);
        implementation.setPort(port);
        implementation.setUsername(username);
        implementation.setPassword(password);

        Properties properties = implementation.getJavaMailProperties();
        properties.setProperty("mail.debug", debug);
        return implementation;
    }
}
