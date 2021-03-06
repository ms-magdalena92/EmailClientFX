package emailClient.service;

import emailClient.model.ValidAccountCredentials;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AccountPersistenceHandler {

    private final String VALID_ACCOUNT_CREDENTIALS_LOCATION = System.getProperty("user.home") + File.separator + "validAccounts.ser";

    private final TextEncoderDecoder encoderDecoder = new TextEncoderDecoder();

    public List<ValidAccountCredentials> loadFromPersistence() {
        List<ValidAccountCredentials> resultList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(VALID_ACCOUNT_CREDENTIALS_LOCATION);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<ValidAccountCredentials> persistedList = (List<ValidAccountCredentials>) objectInputStream.readObject();
            decodePasswords(persistedList);
            resultList.addAll(persistedList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public void saveToPersistence(List<ValidAccountCredentials> validAccounts) {
        try {
            File file = new File(VALID_ACCOUNT_CREDENTIALS_LOCATION);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            encodePasswords(validAccounts);
            objectOutputStream.writeObject(validAccounts);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePersistenceFile() {
        Path fileToDeletePath = Paths.get(VALID_ACCOUNT_CREDENTIALS_LOCATION);
        try {
            Files.deleteIfExists(fileToDeletePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void decodePasswords(List<ValidAccountCredentials> persistedList) {
        for (ValidAccountCredentials validAccount : persistedList) {
            validAccount.setPassword(encoderDecoder.decode(validAccount.getPassword()));
        }
    }

    private void encodePasswords(List<ValidAccountCredentials> persistedList) {
        for (ValidAccountCredentials validAccount : persistedList) {
            validAccount.setPassword(encoderDecoder.encode(validAccount.getPassword()));
        }
    }
}
