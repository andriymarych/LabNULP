package com.marych.insuranceApp.menu.loginMenu;

import com.marych.insuranceApp.Main;
import com.marych.insuranceApp.menu.commonCommands.ExitCommand;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.menu.mainMenu.MainMenu;
import com.marych.insuranceApp.tools.EmailErrorSender;
import com.marych.insuranceApp.tools.LogStatus;
import com.marych.insuranceApp.tools.UserLogger;
import com.marych.insuranceApp.user.User;
import com.marych.insuranceApp.user.UserList;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class SignInCommand implements MenuItem {
    private UserList userList;
    private MainMenu mainMenu;
    private UserLogger userLogger;
    @Override
    public boolean execute() throws IOException {
        userLogger = new UserLogger();
        userList = getUserList();
        int userId = signIn();
        if(userId != 0){
            userLogger.info("User id" + userId + " is logged in");
            mainMenu = getMainMenu();
            mainMenu.run();
            return true;
        }else{
            ExitCommand exitCommand = new ExitCommand();
            exitCommand.execute();
        }
        return false;
    }
    public MainMenu getMainMenu(){
        return Objects.requireNonNullElseGet(mainMenu, MainMenu::new);

    }
    public void setMainMenu(MainMenu mainMenu){
        this.mainMenu = mainMenu;
    }

    public void setUserList(UserList userList) {
        this.userList = userList;
    }

    public UserList getUserList() {
        if(Main.userInfo != null){
            return Main.userInfo;
        }else return Objects.requireNonNullElseGet(userList, UserList::new);
    }

    public int signIn(){
        String login = null;
        String password;
        Scanner in = new Scanner(System.in);
        for(int i = 3; i >= 0 ; i--) {
            for(int j = 2; j >= 1; j--){
                if(Main.user == null) {
                    System.out.println("Логін:");
                    login = in.next();
                }
                System.out.println("Пароль:");
                password = in.next();
                Main.user = userList.login(login, password);
                if(Main.user != null && Main.user.getLogStatus() != LogStatus.PASSWORD) {
                    return Main.user.getUserId();
                }else if (Main.user != null && Main.user.getLogStatus() == LogStatus.PASSWORD){
                    System.out.println("Невірно введений пароль.");
                    System.out.println("У вас залишилось спроб : " + j);
                }else{
                    System.out.println("Невірно введений логін.");
                    if(i != 0) {
                        System.out.println("У вас залишилось спроб : " + i);
                    }else{
                        System.out.println("Ви вичерпали ліміт введення логіну");
                    }
                    break;
                }
                if(j == 1 && Main.user.getLogStatus() == LogStatus.PASSWORD){
                    userLogger.error("User id" + Main.user.getUserId() +" entered the wrong password three times");
                }
            }
            if(Main.user != null){
                return 0;
            }
        }
        return 0;
    }
}
