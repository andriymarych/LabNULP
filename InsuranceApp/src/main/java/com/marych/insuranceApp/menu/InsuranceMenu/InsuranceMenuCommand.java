package com.marych.insuranceApp.menu.insuranceMenu;

import com.marych.insuranceApp.menu.commonCommands.MenuItem;

import java.io.IOException;
import java.util.Objects;

public class InsuranceMenuCommand implements MenuItem {
    InsurancePolicyMenu insurancePolicyMenu;

    @Override
    public boolean execute() throws IOException {
        insurancePolicyMenu = getInsurancePolicyMenu();
        insurancePolicyMenu.execute();
        return true;
    }

    public void setInsurancePolicyMenu(InsurancePolicyMenu insurancePolicyMenu) {
        this.insurancePolicyMenu = insurancePolicyMenu;
    }

    public InsurancePolicyMenu getInsurancePolicyMenu() {
        return Objects.requireNonNullElseGet(insurancePolicyMenu, InsurancePolicyMenu::new);
    }
}
