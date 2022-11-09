package com.marych.insuranceApp.menu.derivativeMenu.showDerCommand;

import com.marych.insuranceApp.Main;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.menu.derivativeMenu.DerivativeMenu;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.User;
import com.marych.insuranceApp.user.InsuranceSpecialist;
import com.marych.insuranceApp.insurance.derivative.Derivative;


import java.util.Map;
import java.util.Objects;

public class ShowAllDerivativesCommand implements MenuItem {

    User user;
    DerivativeMenu derivativeMenu;

    @Override
    public boolean execute() {
        if(showDerivativeList()){
            derivativeMenu = getDerivativeMenu();
            derivativeMenu.execute();
            return true;
        }else{
            derivativeMenu = getDerivativeMenu();
            derivativeMenu.execute();
            return false;
        }
    }
    public boolean showDerivativeList() {
        user = getUser();
        Map<Integer, Derivative> derivativeList = null;
        if (user instanceof Customer customer) {
            derivativeList = customer.getDerivativeList();
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            derivativeList = insuranceSpecialist.getDerivativeList();
        }
        if(derivativeList.size() != 0) {
            System.out.println("\nСписок Деривативів:\n");
            var entrySet = derivativeList.entrySet();
            for (var entry : entrySet) {
                entry.getValue().showDerivative();
                System.out.println("-".repeat(60));
            }
            return true;
        }else{
            System.out.println("\nУ вас ще немає створених деривативів.\n");
        }
        return false;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        if(Main.user !=null){
            return Main.user;
        }else{
            return user;
        }
    }

    public void setDerivativeMenu(DerivativeMenu derivativeMenu) {
        this.derivativeMenu = derivativeMenu;
    }

    public DerivativeMenu getDerivativeMenu() {
        return Objects.requireNonNullElseGet(derivativeMenu, DerivativeMenu::new);
    }
}
