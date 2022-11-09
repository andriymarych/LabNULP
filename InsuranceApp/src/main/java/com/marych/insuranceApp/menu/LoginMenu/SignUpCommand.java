package com.marych.insuranceApp.menu.loginMenu;

import com.marych.insuranceApp.Main;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.menu.mainMenu.MainMenu;
import com.marych.insuranceApp.tools.UserLogger;
import com.marych.insuranceApp.user.UserList;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class SignUpCommand implements MenuItem {
    private UserLogger userLogger;

    private UserList userList;
    private MainMenu mainMenu;
    @Override
    public boolean execute() throws IOException {
        setUserList(Main.userInfo);
        Scanner in = new Scanner(System.in);
        System.out.println("Введіть ваший номер ID-Паспорту, " +
                "щоб поділитися із нами копіями цифрових документів із Дії:");
        int documentNo = in.nextInt();
        Main.user = userList.userCreate(documentNo);
        if(Main.user != null) {
            userLogger = new UserLogger();
            userLogger.info("User id" + Main.user.getUserId() + " has created an account");
            userLogger.info("User id" + Main.user.getUserId() + " is logged in");
            MainMenu mainMenu = getMainMenu();
            mainMenu.run();
            return true;
        }else{
            return false;
        }
    }
    public void setUserList(UserList userList) {
        this.userList = userList;
    }
    public MainMenu getMainMenu(){
        return Objects.requireNonNullElseGet(mainMenu, MainMenu::new);

    }
    public UserList getUserList(){
        return Objects.requireNonNullElseGet(userList, UserList::new);
    }
    public void setMainMenu(MainMenu mainMenu){
        this.mainMenu = mainMenu;
    }
}
