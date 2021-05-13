package emailClient.factory;

import emailClient.model.EmailMessage;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

public class EmailMessageFactory {

    public EmailMessage createMessage(Message message) throws MessagingException {
        boolean isRead = message.getFlags().contains(Flags.Flag.SEEN);
        List<String> senders = getSenders(message.getFrom());
        List<String> recipients = getRecipients(message.getRecipients(MimeMessage.RecipientType.TO));

        return new EmailMessage(
                message.getSubject(),
                senders,
                recipients,
                message.getSize(),
                message.getSentDate(),
                message,
                isRead
        );
    }

    private List<String> getSenders(Address[] sendersAddresses) {
        List<String> senders = new ArrayList<>();
        for (Address sender : sendersAddresses) {
            senders.add(sender.toString());
        }

        return senders;
    }

    private List<String> getRecipients(Address[] recipientsAddresses) {
        List<String> recipients = new ArrayList<>();
        for (Address sender : recipientsAddresses) {
            recipients.add(sender.toString());
        }

        return recipients;
    }
}
