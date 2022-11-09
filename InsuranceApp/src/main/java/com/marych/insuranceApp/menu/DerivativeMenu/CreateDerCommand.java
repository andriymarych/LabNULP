package com.marych.insuranceApp.menu.derivativeMenu;

import com.marych.insuranceApp.Main;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.tools.UserLogger;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.User;
import com.marych.insuranceApp.user.InsuranceSpecialist;
import com.marych.insuranceApp.scanners.jsonScanner.JsonScanner;
import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.insurance.derivative.Derivative;
import com.marych.insuranceApp.scanners.DerivativeScanner;

import java.io.IOException;
import java.util.Objects;

public class CreateDerCommand implements MenuItem {
    Derivative derivative;

    User user;

    DerivativeScanner derivativeScanner;

    JsonScanner jsonScanner;
    DerivativeMenu derivativeMenu;

    @Override
    public boolean execute() throws IOException {
        user = getUser();
        jsonScanner = getJsonScanner();
        derivativeScanner = getDerivativeScanner();
        createDerivative();
        derivativeMenu = getDerivativeMenu();
        derivativeMenu.execute();
        return true;
    }

    private void createDerivative() throws IOException {
        if (user instanceof Customer customer) {
            derivative = derivativeScanner.createDerivative(customer);
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            derivative = derivativeScanner.createDerivative(insuranceSpecialist);
        }
        if (derivative != null) {
            derivative.showDerivative();
            addData();
        }
    }

    public void addData() throws IOException {
        UserLogger userLogger = new UserLogger();
        userLogger.info("User id" + user.getUserId() + " created derivative No" + derivative.getDerivativeNo());
        InsuranceCompany insuranceCompany = derivative.getInsuranceCompany();
        Customer customer = derivative.getDerivativeHolder();
        InsuranceSpecialist insuranceSpecialist = derivative.getInsurer();
        customer.addDerivative(derivative);
        insuranceSpecialist.addDerivative(derivative);
        insuranceCompany.addDerivative(customer, derivative);
        jsonScanner.addDerivative(derivative);
    }

    public void setDerivativeMenu(DerivativeMenu derivativeMenu) {
        this.derivativeMenu = derivativeMenu;
    }

    public DerivativeMenu getDerivativeMenu() {
        return Objects.requireNonNullElseGet(derivativeMenu, DerivativeMenu::new);
    }

    public void setDerivativeScanner(DerivativeScanner derivativeScanner) {
        this.derivativeScanner = derivativeScanner;
    }

    public JsonScanner getJsonScanner() {
        return Objects.requireNonNullElseGet(jsonScanner, JsonScanner::new);
    }

    public void setJsonScanner(JsonScanner jsonScanner) {
        this.jsonScanner = jsonScanner;
    }

    public DerivativeScanner getDerivativeScanner() {
        return Objects.requireNonNullElseGet(derivativeScanner, DerivativeScanner::new);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        if (Main.user != null) {
            return Main.user;
        } else {
            return user;
        }
    }
}
