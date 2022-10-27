package com.marych.insuranceApp.menu.DerivativeMenu.showDerCommand;

import com.marych.insuranceApp.User.Customer;
import com.marych.insuranceApp.User.InsuranceSpecialist;
import com.marych.insuranceApp.insurance.derivative.Derivative;
import com.marych.insuranceApp.menu.DerivativeMenu.DerivativeMenu;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;


import java.util.ArrayList;
import java.util.Map;

import static com.marych.insuranceApp.Main.user;

public class ShowAllDerivativesCommand implements MenuItem {

    @Override
    public void execute() {
        showDerivativeList();
        DerivativeMenu derivativeMenu = new DerivativeMenu();
        derivativeMenu.execute();
    }
    public static void showDerivativeList() {
        Map<Integer, Derivative> derivativeList = null;
        ArrayList<Integer> derivativeNoList = null;
        if (user instanceof Customer customer) {
            derivativeList = customer.getDerivativeList();
            derivativeNoList = customer.getDerivativeNoList();
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            derivativeList = insuranceSpecialist.getDerivativeList();
            derivativeNoList = insuranceSpecialist.getDerivativeNoList();
        }
        if(derivativeList.size() != 0) {
            System.out.println("\nСписок Деривативів:\n");
            for (int i = 0; i < derivativeList.size(); i++) {
                derivativeList.get(derivativeNoList.get(i)).showDerivative();
                System.out.println("-".repeat(60));
            }
        }else{
            System.out.println("\nУ вас ще немає створених деривативів.\n");
        }
    }
}
