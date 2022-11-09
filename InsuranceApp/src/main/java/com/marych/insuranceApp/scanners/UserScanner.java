package com.marych.insuranceApp.scanners;

import com.marych.insuranceApp.tools.EmailValidation;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.UserList;

import java.util.Scanner;

import static com.marych.insuranceApp.user.UserList.getNextUserNumber;

public class UserScanner {
    private UserList userList;
    private Customer newUser;
    private String email;
    private String login;
    private String password;
    private int Id;

    public UserScanner(UserList userList) {
        this.userList = userList;

    }


    public String createLogin() {
        String login;
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("Введіть Логін:");
            login = in.next();
            if (userList.getUserLogins().containsKey(login)) {
                System.out.println("Обліковий запис із таким логіном вже існує.");
            } else {
                return login;
            }
        }
        return null;
    }

    public String createEmail() {
        String email;
        Scanner in = new Scanner(System.in);

        for(int i = 0; i < 3 ; i++){
            System.out.println("Введіть електронну пошту:");
            email = in.next();
            if (userList.getUserEmails().containsKey(email)) {
                System.out.println("Обліковий запис із введеною електронною поштою вже існує.");
                return null;
            }
            if (EmailValidation.validate(email)) {
                return email;
            } else {
                System.out.println("Ви ввели невірний адрес електронної пошти.");
            }
        }

        return null;
    }

    public String createPassword() {
        Scanner in = new Scanner(System.in);
        String password;
        System.out.println("Введіть Пароль:");
        password = in.next();
        return password;
    }
    public int getNextUserId(){
        return getNextUserNumber();
    }
}
