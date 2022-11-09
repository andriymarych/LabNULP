package com.marych.insuranceApp.user;


import com.marych.insuranceApp.Main;
import com.marych.insuranceApp.diiaGov.DiiaGov;
import com.marych.insuranceApp.scanners.UserScanner;
import com.marych.insuranceApp.scanners.jsonScanner.JsonScanner;
import com.marych.insuranceApp.tools.LogStatus;


import java.io.IOException;
import java.util.*;


public class UserList {

    private Map<String, User> userLogins;

    private Map<String, User> userEmails;
    private Map<Integer, User> userIdList;
    private static int userNumber;
    private UserScanner userScanner;
    private JsonScanner jsonScanner;

    public UserList() {
        userLogins = new HashMap<>();
        userIdList = new HashMap<>();
        userEmails = new HashMap<>();
        userScanner = new UserScanner(this);
        userNumber = 1000;
    }

    public UserList(UserScanner userScanner) {
        userLogins = new HashMap<>();
        userIdList = new HashMap<>();
        userEmails = new HashMap<>();
        this.userScanner = userScanner;
        userNumber = 1000;
    }

    public static void addUserNumber() {
        userNumber++;
    }

    public static int getNextUserNumber() {
        userNumber++;
        return userNumber;
    }

    public User login(String login, String password) {
        User user;
        if (userLogins.containsKey(login)) {
            user = userLogins.get(login);
            if (user.verifyPassword(password)) {
                user.setLogStatus(LogStatus.SUCCESS);
            }else{
                user.setLogStatus(LogStatus.PASSWORD);
            }
            return user;
        }
        return null;
    }


    public void addUserLogin(String login, User user) {
        userLogins.put(login, user);
    }

    public Customer userCreate(int documentId) throws IOException {
        DiiaGov diiaDocument;
        diiaDocument = Main.diiaGovDocuments.getDocument(documentId);
        if (diiaDocument != null) {
            Customer customer = userCreation(diiaDocument);
            return customer;
        }
        return null;
    }

    public Map<Integer, User> getUserIdList() {
        return userIdList;
    }

    public void addUserId(int id, User user) {
        addUserNumber();
        userIdList.put(id, user);
    }

    public void addUserEmail(String email, User user) {
        userEmails.put(email, user);
    }

    private void printExitMessage() {
        System.out.println("Повторіть спробу реєстрації через 5 хвилин");
    }

    public Customer userCreation(DiiaGov ID) throws IOException {
        jsonScanner = getJsonScanner();
        Customer newUser;
        String email;
        String login;
        String password;
        int Id;
        email = userScanner.createEmail();
        if (email == null) {
            printExitMessage();
            return null;
        }
        login = userScanner.createLogin();
        password = userScanner.createPassword();
        Id = userScanner.getNextUserId();
        newUser = new Customer(Id, login, password)
                .setFirstName(ID.getFirstName())
                .setLastName(ID.getLastName())
                .setBirthDate(ID.getBirthDate().toString())
                .setITN(ID.getITN())
                .setEmail(email);
        userLogins.put(login, newUser);
        userEmails.put(email, newUser);
        userIdList.put(Id, newUser);
        jsonScanner.addCustomer(newUser);
        addUserNumber();
        System.out.println("Вітаємо, ваший обліковий запис успішно створено");
        return newUser;
    }

    public JsonScanner getJsonScanner() {
        return Objects.requireNonNullElseGet(jsonScanner, JsonScanner::new);
    }

    public void setJsonScanner(JsonScanner jsonScanner) {
        this.jsonScanner = jsonScanner;
    }

    public Map<String, User> getUserLogins() {
        return userLogins;
    }

    public Map<String, User> getUserEmails() {
        return userEmails;
    }
}
