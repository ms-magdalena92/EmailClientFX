package emailClient.service;

import emailClient.enums.EmailSendingResult;
import emailClient.model.EmailAccount;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MessageSenderService extends Service<EmailSendingResult> {

    private MimeMessage message;

    private final EmailAccount emailAccount;

    private final String subject;

    private final String recipient;

    private final String content;

    private final List<File> attachments;

    public MessageSenderService(EmailAccount emailAccount, String subject, String recipient, String content, List<File> attachments) {
        this.emailAccount = emailAccount;
        this.subject = subject;
        this.recipient = recipient;
        this.content = content;
        this.attachments = attachments;
    }

    @Override
    protected Task<EmailSendingResult> createTask() {
        return new Task<>() {
            @Override
            protected EmailSendingResult call() {
                try {
                    createMessage();
                    setMessageContent();
                    addAttachments();
                    sendMessage();
                    return EmailSendingResult.SUCCESS;
                } catch (MessagingException e) {
                    e.printStackTrace();
                    return EmailSendingResult.FAILED_BY_PROVIDER;
                } catch (Exception e) {
                    e.printStackTrace();
                    return EmailSendingResult.FAILED_BY_UNEXPECTED_ERROR;
                }
            }
        };
    }

    private void createMessage() throws MessagingException {
        message = new MimeMessage(emailAccount.getSession());
        message.setFrom(emailAccount.getEmailAddress());
        message.addRecipients(Message.RecipientType.TO, recipient);
        message.setSubject(subject);
    }

    private void setMessageContent() throws MessagingException {
        Multipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content, "text/html");
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
    }

    private void addAttachments() throws MessagingException, IOException {
        if (attachments.size() > 0) {
            for (File file : attachments) {
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(file.getAbsolutePath());
                mimeBodyPart.setDataHandler(new DataHandler(source));
                mimeBodyPart.setFileName(file.getName());
                Multipart multipart = (Multipart) message.getContent();
                multipart.addBodyPart(mimeBodyPart);
            }
        }
    }

    private void sendMessage() throws MessagingException {
        Transport transport = emailAccount.getSession().getTransport();
        transport.connect(
                emailAccount.getProperties().getProperty("outgoingHost"),
                emailAccount.getEmailAddress(),
                emailAccount.getPassword()
        );
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
