package com.marych.insuranceApp.menu.InsuranceMenu;

import com.marych.insuranceApp.menu.commonCommands.MenuItem;

import java.io.IOException;

public class InsuranceMenuCommand implements MenuItem {
    @Override
    public void execute() throws IOException {
        InsurancePolicyMenu insurancePolicyMenu = new InsurancePolicyMenu();
        insurancePolicyMenu.execute();
    }
}
