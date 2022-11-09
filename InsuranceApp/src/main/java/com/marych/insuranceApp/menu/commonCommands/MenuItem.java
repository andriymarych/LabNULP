package com.marych.insuranceApp.menu.commonCommands;

import com.marych.insuranceApp.exceptions.UserWrongLoginException;

import java.io.IOException;

public interface MenuItem {
    boolean execute() throws IOException, UserWrongLoginException;
}
