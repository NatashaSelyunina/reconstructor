package reConstructor.services;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import reConstructor.domain.Utillity.MailData;
import reConstructor.domain.entities.Moderator;

@Service
public class MailSenderService {

    private MailData mailData;

    public MailSenderService(MailData mailData) {
        this.mailData = mailData;
    }

    private void sendEmail(Moderator moderator, Map<String, Object> linkModel,
                           Template template) {

        try {
            Session session = Session.getInstance(getMailProperties(), new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailData.getUsername(), mailData.getPassword());
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailData.getUsername(), mailData.getFROM_NAME()));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(moderator.getEmail()));
            message.setSubject("Welcome to REconstructor.me!");

            String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, linkModel);

            message.setContent(htmlText, "text/html");
            Transport.send(message);
        } catch (MessagingException | java.io.IOException | TemplateException e) {
            throw new RuntimeException("Failed to send activation email", e);
        }
    }

    public void sendActivationEmail(String validationCode, Moderator moderator) {

        String activationLink = ("https://reconstructor.me/activate?validation-code=" +
                validationCode);

        String name = moderator.getName();
        String surname = moderator.getSurname();
        //TODO использовать поля имя фамилия для обращения к
        // пользователю в самом письме ... спасибо

        Map<String, Object> userData = new HashMap<>();
        userData.put("activationLink", activationLink);
        userData.put("name", name);
        userData.put("surname", surname);

        try {
            Template template = mailData.getFreemarkerConfiguration().getTemplate("activate_acc_mail.ftlh");
            sendEmail(moderator, userData, template);
        } catch (IOException e) {
            throw new RuntimeException("Failed to send activation email", e);
        }
    }

    public void sendLinkToChangeEmail(String validationCode, Moderator moderator) {
        String changeEmailLink = "https://reconstructor.me/activate?validation-code=" + validationCode;

        //TODO тправляем сообщение с приветствием и типа нажмите сюда что бы изменить ваш емаил с такойже ссылкой
        String name = moderator.getName();
        String surname = moderator.getSurname();

        Map<String, Object> userData = new HashMap<>();
        userData.put("changeEmailLink", changeEmailLink);
        userData.put("name", name);
        userData.put("surname", surname);

        try {
            Template template = mailData.getFreemarkerConfiguration().getTemplate("change_email_mail.ftlh");
            sendEmail(moderator, userData, template);
        } catch (IOException e) {
            throw new RuntimeException("Failed to send activation email", e);
        }
    }

    public void sendResetPasswordEmail(String validationCode, Moderator moderator) {
        String resetPasswordLink = "https://reconstructor.me/reset-password?validation-code=" + validationCode;
        ///TODO все тоже самое только с востанавлением пароля

        Map<String, Object> userData = new HashMap<>();
        userData.put("resetPasswordLink", resetPasswordLink);

        try {
            Template template = mailData.getFreemarkerConfiguration().getTemplate("reset_password_mail.ftlh");
            sendEmail(moderator, userData, template);
        } catch (IOException e) {
            throw new RuntimeException("Failed to send password reset email", e);
        }
    }

    public void sendAccountRecoveryEmail(Moderator moderator, int recoveryPeriodMonths) {
        String name = moderator.getName();
        String surname = moderator.getSurname();

        Map<String, Object> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("surname", surname);
        userData.put("recoveryPeriodMonths", recoveryPeriodMonths);

        try {
            Template template = mailData.getFreemarkerConfiguration().getTemplate("account_recovery_mail.ftlh");
            sendEmail(moderator, userData, template);
        } catch (IOException e) {
            throw new RuntimeException("Failed to send account recovery email", e);
        }
    }

    public void sendAccountDeletionEmail(Moderator moderator) {
        String name = moderator.getName();
        String surname = moderator.getSurname();

        Map<String, Object> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("surname", surname);

        try {
            Template template = mailData.getFreemarkerConfiguration().getTemplate(
                    "account_deletion_notification_mail.ftlh");
            sendEmail(moderator, userData, template);
        } catch (IOException e) {
            throw new RuntimeException("Failed to send account deletion notification email", e);
        }
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        return properties;
    }
}
