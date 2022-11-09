package com.marych.insuranceApp.menu.derivativeMenu;

import com.marych.insuranceApp.Main;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.scanners.jsonScanner.JsonScanner;
import com.marych.insuranceApp.tools.UserLogger;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.User;
import com.marych.insuranceApp.user.InsuranceSpecialist;
import com.marych.insuranceApp.insurance.derivative.Derivative;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;


public class DeleteDerCommand implements MenuItem {
    private DerivativeMenu derivativeMenu;
    private User user;
    private JsonScanner jsonScanner;

    public boolean execute() {
        user = getUser();
        jsonScanner = getJsonScanner();
        if(derivativeDeletion()){
            derivativeMenu =getDerivativeMenu();
            derivativeMenu.execute();
            return true;
        }
        return false;
    }
    private boolean derivativeDeletion(){
        Scanner in = new Scanner(System.in);
        int derivativeNo;
        for(int i = 0; i < 2; i++){
            System.out.println("Введіть номер деривативу, який необхідно видалити : ");
            derivativeNo = in.nextInt();
            if(deleteDerivative(derivativeNo)){
                System.out.println("Дериватив № " + derivativeNo + " успішно видалено.");
                return true;
            }
        }
        return false;
    }
    private boolean deleteDerivative(int derivativeNo) {
        Derivative derivative = null;
        UserLogger userLogger = new UserLogger();
        Map<Integer,Derivative> derivativeMap ;
        if (user instanceof Customer customer) {
            derivativeMap = customer.getDerivativeList();
            if (derivativeMap.containsKey(derivativeNo)) {
                derivative = customer.getDerivativeList().get(derivativeNo);
                jsonScanner.derivativeDeletion(derivative,derivative.getDerivativeHolder().getUserId());
            } else {
                System.out.println("Деривативу № " + derivativeNo + " не існує.");
                return false;
            }
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            derivativeMap = insuranceSpecialist.getDerivativeList();
            if (derivativeMap.containsKey(derivativeNo)) {
                derivative = insuranceSpecialist.getDerivativeList().get(derivativeNo);
                jsonScanner.derivativeDeletion(derivative,derivative.getDerivativeHolder().getUserId());
            } else {
                System.out.println("Деривативу № " + derivativeNo + " не існує.");
                return false;
            }
        }
        assert derivative != null;
        userLogger.info("User id" + user.getUserId() + " deleted derivative No" + derivative.getDerivativeNo());
        return true;
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

    public void setDerivativeMenuMenu(DerivativeMenu derivativeMenu) {
        this.derivativeMenu = derivativeMenu;
    }

    public DerivativeMenu getDerivativeMenu() {
        return Objects.requireNonNullElseGet(derivativeMenu, DerivativeMenu::new);
    }

    public void setJsonScanner(JsonScanner jsonInfoScanner) {
        this.jsonScanner = jsonInfoScanner;
    }

    public JsonScanner getJsonScanner() {
        return Objects.requireNonNullElseGet(jsonScanner, JsonScanner::new);
    }
}
