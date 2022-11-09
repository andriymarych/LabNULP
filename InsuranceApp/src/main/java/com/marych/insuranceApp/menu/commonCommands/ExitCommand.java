package com.marych.insuranceApp.menu.commonCommands;

import com.marych.insuranceApp.Main;
import com.marych.insuranceApp.tools.LogStatus;
import com.marych.insuranceApp.tools.UserLogger;

public class ExitCommand implements MenuItem {
    @Override
    public boolean execute() {
        UserLogger userLogger = new UserLogger();
        if(Main.user.getLogStatus() != LogStatus.PASSWORD) {
            userLogger.info("User id" + Main.user.getUserId() + " is logged out");
        }
        System.exit(0);
        return true;
    }
}
