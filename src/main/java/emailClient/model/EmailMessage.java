package emailClient.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.mail.Message;
import javax.mail.internet.MimeBodyPart;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailMessage {

    private final SimpleStringProperty subject;

    private final List<String> senders;

    private final List<String> recipients;

    private final SimpleObjectProperty<EmailMessageSize> size;

    private final SimpleObjectProperty<Date> date;

    private final Message message;

    private boolean isRead;

    private List<MimeBodyPart> attachments = new ArrayList<>();

    private boolean hasAttachment = false;

    public EmailMessage(String subject, List<String> senders, List<String> recipients, int size, Date date,
                        Message message,
                        boolean isRead) {
        this.subject = new SimpleStringProperty(subject);
        this.senders = new ArrayList<>(senders);
        this.recipients = new ArrayList<>(recipients);
        this.size = new SimpleObjectProperty<>(new EmailMessageSize(size));
        this.date = new SimpleObjectProperty<>(date);
        this.message = message;
        this.isRead = isRead;
    }

    public String getSubject() {
        return subject.get();
    }

    public String getSenders() {
        return senders.toString().replace("[", "").replace("]", "");
    }

    public String getRecipients() {
        return recipients.toString().replace("[", "").replace("]", "");
    }

    public EmailMessageSize getSize() {
        return size.get();
    }

    public Date getDate() {
        return date.get();
    }

    public Message getMessage() {
        return message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean hasAttachment() {
        return hasAttachment;
    }

    public List<MimeBodyPart> getAttachments() {
        return attachments;
    }

    public void addAttachment(MimeBodyPart mimeBodyPart) {
        hasAttachment = true;
        attachments.add(mimeBodyPart);
    }
}
