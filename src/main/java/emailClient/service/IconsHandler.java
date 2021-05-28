package emailClient.service;

import emailClient.App;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class IconsHandler {

    public ImageView getIconForFolder(String folderName) {
        String lowerCaseFolderName = folderName.toLowerCase();
        ImageView imageView;
        try {
            if (lowerCaseFolderName.contains("@")) {
                imageView = new ImageView(new Image(Objects.requireNonNull(App.class.getResourceAsStream("icons/email.png"))));
            } else if (lowerCaseFolderName.contains("inbox")) {
                imageView = new ImageView(new Image(Objects.requireNonNull(App.class.getResourceAsStream("icons/inbox.png"))));
            } else if (lowerCaseFolderName.contains("sent")) {
                imageView = new ImageView(new Image(Objects.requireNonNull(App.class.getResourceAsStream("icons/sent.png"))));
            } else if (lowerCaseFolderName.contains("spam")) {
                imageView = new ImageView(new Image(Objects.requireNonNull(App.class.getResourceAsStream("icons/spam.png"))));
            } else if (lowerCaseFolderName.contains("bin")) {
                imageView = new ImageView(new Image(Objects.requireNonNull(App.class.getResourceAsStream("icons/bin.png"))));
            } else {
                imageView = new ImageView(new Image(Objects.requireNonNull(App.class.getResourceAsStream("icons/folder.png"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);
        return imageView;
    }
}
