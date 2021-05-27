package emailClient.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AttachmentHandler {

    MimeBodyPart mimeBodyPart;

    private final String downloadedFilePath;

    public AttachmentHandler(MimeBodyPart mimeBodyPart) throws MessagingException {
        this.mimeBodyPart = mimeBodyPart;
        String LOCATION_OF_DOWNLOADS = System.getProperty("user.home") + "/Downloads/";
        this.downloadedFilePath = LOCATION_OF_DOWNLOADS + mimeBodyPart.getFileName();
    }

    public void saveAttachmentToDisc() throws MessagingException, IOException {
        mimeBodyPart.saveFile(downloadedFilePath);
    }

    public void openAttachment() throws MessagingException, IOException {
        File attachmentFile = new File(downloadedFilePath);
        Desktop desktop = Desktop.getDesktop();
        if (attachmentFile.exists()) {
            try {
                desktop.open(attachmentFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            saveAttachmentToDisc();
            openAttachment();
        }
    }
}
