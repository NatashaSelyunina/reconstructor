package reConstructor.domain.Utillity;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailData {

    private final String FROM_NAME = "REconstructor's Team";
    private JavaMailSender emailSender;
    private String username;
    private String password;
    private final freemarker.template.Configuration freemarkerConfiguration;

    @Autowired
    public MailData(JavaMailSender emailSender, @Value("${spring.mail.username}") String username,
                    @Value("${spring.mail.password}") String password,
                    Configuration freemarkerConfiguration) {
        this.emailSender = emailSender;
        this.username = username;
        this.password = password;
        this.freemarkerConfiguration = freemarkerConfiguration;
    }

    public JavaMailSender getEmailSender() {
        return emailSender;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Configuration getFreemarkerConfiguration() {
        return freemarkerConfiguration;
    }

    public String getFROM_NAME() {
        return FROM_NAME;
    }
}
