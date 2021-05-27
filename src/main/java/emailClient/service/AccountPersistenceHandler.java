package emailClient.service;

import emailClient.model.ValidAccountCredentials;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountPersistenceHandler {

    private final String VALID_ACCOUNT_CREDENTIALS_LOCATION = System.getProperty("user.home") + File.separator + "validAccounts.ser";

    public List<ValidAccountCredentials> loadFromPersistence() {
        List<ValidAccountCredentials> resultList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(VALID_ACCOUNT_CREDENTIALS_LOCATION);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<ValidAccountCredentials> persistedList = (List<ValidAccountCredentials>) objectInputStream.readObject();
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
            objectOutputStream.writeObject(validAccounts);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
