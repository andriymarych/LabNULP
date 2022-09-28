package com.Game;

import com.Game.Menu.Menu;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void sleep(int timeout){
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        menu.runMenu();
    }
}
