package emailClient.service;

import emailClient.enums.EmailSendingResult;
import emailClient.model.EmailAccount;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MessageSenderService extends Service<EmailSendingResult> {

    private final EmailAccount emailAccount;
    private final String subject;
    private final String recipient;
    private final String content;

    public MessageSenderService(EmailAccount emailAccount, String subject, String recipient, String content) {
        this.emailAccount = emailAccount;
        this.subject = subject;
        this.recipient = recipient;
        this.content = content;
    }

    @Override
    protected Task<EmailSendingResult> createTask() {
        return new Task<>() {
            @Override
            protected EmailSendingResult call() {
                try {
                    MimeMessage message = createMessage();
                    setMessageContent(message);
                    sendMessage(message);
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

    private MimeMessage createMessage() throws MessagingException {
        MimeMessage mimeMessage = new MimeMessage(emailAccount.getSession());
        mimeMessage.setFrom(emailAccount.getEmailAddress());
        mimeMessage.addRecipients(Message.RecipientType.TO, recipient);
        mimeMessage.setSubject(subject);
        return mimeMessage;
    }

    private void setMessageContent(MimeMessage message) throws MessagingException {
        Multipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content, "text/html");
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
    }

    private void sendMessage(MimeMessage message) throws MessagingException {
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
