package com.marych.insuranceApp.menu.derivativeMenu.showDerCommand;

import com.marych.insuranceApp.Main;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.derivative.DerivativeFilter;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.menu.derivativeMenu.DerivativeMenu;
import com.marych.insuranceApp.user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ShowFilteredDerivativeCommand implements MenuItem {
    User user;
    DerivativeMenu derivativeMenu;
    DerivativeFilter derivativeFilter;
    ShowAllDerivativesCommand showAllDerivativesCommand;
    @Override
    public boolean execute() throws IOException {
        user = getUser();
        showFilteredDerivative();
        derivativeMenu = getDerivativeMenu();
        derivativeMenu.execute();
        return true;
    }
    public ArrayList<InsurancePolicy> showFilteredDerivative(){
        showAllDerivativesCommand = getShowAllDerivativesCommand();
        int derivativeNo;
        ArrayList<InsurancePolicy> insurancePolicyList ;
        Scanner in = new Scanner(System.in);
        showAllDerivativesCommand.showDerivativeList();
        do {
            System.out.println("Введіть номер деривативу : ");
            derivativeNo = in.nextInt();
            derivativeFilter = getDerivativeFilter(derivativeNo);
            insurancePolicyList = derivativeFilter.filterPrice();
        } while (insurancePolicyList == null);
        if(insurancePolicyList.size() != 0 ) {
            System.out.println("Відфільтрований список страхових зобовʼязань :\n");
            for (InsurancePolicy insurancePolicy : insurancePolicyList) {
                System.out.println(insurancePolicy);
                System.out.println("-".repeat(60));
            }
            return insurancePolicyList;
        }else{
            System.out.println("Страхових зобовʼязань за вказаним фільтровим параметром не існує.");
        }
        return null;
    }

    public void setDerivativeMenu(DerivativeMenu derivativeMenu) {
        this.derivativeMenu = derivativeMenu;
    }

    public DerivativeMenu getDerivativeMenu() {
        return Objects.requireNonNullElseGet(derivativeMenu,DerivativeMenu::new);
    }

    public void setDerivativeFilter(DerivativeFilter derivativeFilter) {
        this.derivativeFilter = derivativeFilter;
    }

    public DerivativeFilter getDerivativeFilter(int derivativeNo) {
        return Objects.requireNonNullElseGet(derivativeFilter, () -> new DerivativeFilter(Main.user, derivativeNo));
    }

    public void setShowAllDerivativesCommand(ShowAllDerivativesCommand showAllDerivativesCommand) {
        this.showAllDerivativesCommand = showAllDerivativesCommand;
    }

    public ShowAllDerivativesCommand getShowAllDerivativesCommand() {
        return Objects.requireNonNullElseGet(showAllDerivativesCommand,ShowAllDerivativesCommand::new);
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

