package com.marych.insuranceApp.User;


import com.marych.insuranceApp.DiiaGov.DiiaGov;
import com.marych.insuranceApp.User.jsonScanner.JsonScanner;
import com.marych.insuranceApp.tools.EmailValidation;

import java.io.IOException;
import java.util.*;


import static com.marych.insuranceApp.Main.diiaGovDocuments;


public class UserList {

    private Map<String, User> userLogins;

    private Map<String, User> userEmails;
    private Map<Integer, User>  userIdList;
    private static int userNumber;

    public UserList(){
        userLogins = new HashMap<>();
        userIdList = new HashMap<>();
        userEmails = new HashMap<>();
        userNumber = 1000;
    }

    public static void addUserNumber(){
        userNumber++;
    }
    public static int getNextUserNumber(){
        userNumber++;
        return userNumber;
    }

    public User login() {
        User user;
        String login;
        String password;
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("Логін:");
            login = in.next();
            System.out.println("Пароль:");
            password = in.next();
            if (userLogins.containsKey(login)) {
                user = userLogins.get(login);
                if (user.verifyPassword(password)) {
                    System.out.println("Вітаємо!, " + user.getFirstName());
                    return user;
                } else {
                    System.out.println("Хибний пароль. Спробуйте ще раз.");
                }
            } else {
                System.out.println("Користувача " + login + " не існує. Спробуйте ще раз.");
            }
        }
        return null;
    }

    public void addUserLogin(String login, User user) {
        userLogins.put(login,user);
    }

    public Customer userCreate() throws IOException {
        DiiaGov diiaDocument ;
        int documentNo;
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("Введіть ваший номер ID-Паспорту, " +
                    "щоб поділитися із нами копіями цифрових документів із Дії:");
            documentNo = in.nextInt();
            diiaDocument = diiaGovDocuments.getDocument(documentNo);
            if (diiaDocument != null) {
                return userCreate(diiaDocument);
            }
        }
        printExitMessage();
        System.exit(1);
        return null;
    }

    public Map<Integer,User> getUserIdList() {
        return userIdList;
    }
    public void addUserId(int id, User user){
        addUserNumber();
        userIdList.put(id,user);
    }
    private String createLogin(){
        String login;
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("Введіть Логін:");
            login = in.next();
            if (userLogins.containsKey(login)) {
                System.out.println("Обліковий запис із таким логіном вже існує.");
            } else {
                return login;
            }
        }
        return null;
    }
    public void addUserEmail(String email, User user){
        userEmails.put(email,user);

    }
    private String createEmail(){
        String email ;
        Scanner in = new Scanner(System.in);
        for (int i = 3; i > 0; i--) {
            System.out.println("Введіть електронну пошту:");
            email = in.next();
            if(userEmails.containsKey(email)){
                System.out.println("Обліковий запис із введеною електронною поштою вже існує.");
                i++;
                continue;
            }
            if (EmailValidation.validate(email)) {
                return email;
            } else {
                if (i != 1) {
                    if (i == 3) {
                        System.out.println("Ви ввели невірний адрес електронної пошти." +
                                "\nУ вас залишилось " + (i - 1) + " Cпроби");
                    } else {
                        System.out.println("Ви ввели невірний адрес електронної пошти." +
                                "\nУ вас залишилось " + (i - 1) + " Спроба");
                    }
                } else {
                    System.out.println("Ви вичерпали ліміт введення адресу електронної пошти");
                }
            }
        }
        return null;

    }

    private void printExitMessage() {
        System.out.println("Повторіть спробу реєстрації через 5 хвилин");
    }

    private Customer userCreate(DiiaGov ID) throws IOException {
        Customer newUser;
        String email;
        String login;
        String password;
        int Id;
        Scanner in = new Scanner(System.in);
        email = createEmail();
        if (email == null) {
            printExitMessage();
            System.exit(2);
        }
        login = createLogin();
        System.out.println("Введіть Пароль:");
        password = in.next();
        Id = getNextUserNumber();
        newUser = new Customer(Id, login, password)
                .setFirstName(ID.getFirstName())
                .setLastName(ID.getLastName())
                .setBirthDate(ID.getBirthDate().toString())
                .setITN(ID.getITN())
                .setEmail(email);
        userLogins.put(login,newUser);
        userEmails.put(email,newUser);
        userIdList.put(Id,newUser);
        JsonScanner.addCustomer(newUser);
        addUserNumber();
        System.out.println("Вітаємо, ваший обліковий запис успішно створено");
        return newUser;
    }
}
