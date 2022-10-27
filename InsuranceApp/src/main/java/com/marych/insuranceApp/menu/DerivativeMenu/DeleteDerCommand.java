package com.marych.insuranceApp.menu.DerivativeMenu;

import com.marych.insuranceApp.User.Customer;
import com.marych.insuranceApp.User.InsuranceSpecialist;
import com.marych.insuranceApp.User.jsonScanner.JsonScanner;
import com.marych.insuranceApp.insurance.derivative.Derivative;
import com.marych.insuranceApp.menu.InsuranceMenu.InsurancePolicyMenu;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;

import java.util.Scanner;

import static com.marych.insuranceApp.Main.user;

public class DeleteDerCommand implements MenuItem {
    public void execute() {
        derivativeDeletion();
        DerivativeMenu derivativeMenu = new DerivativeMenu();
        derivativeMenu.execute();

    }
    private void derivativeDeletion(){
        Scanner in = new Scanner(System.in);
        int derivativeNo;

        do {
            System.out.println("Введіть номер деривативу, який необхідно видалити : ");
            derivativeNo = in.nextInt();
        }
        while (!deleteDerivative(derivativeNo));
        System.out.println("Дериватив № " + derivativeNo + " успішно видалено.");
    }
    private boolean deleteDerivative(int derivativeNo) {
        Derivative derivative = null;
        if (user instanceof Customer customer) {
            if (customer.getDerivativeNoList().contains(derivativeNo)) {
                derivative = customer.getDerivativeList().get(derivativeNo);
            } else {
                System.out.println("Деривативу № " + derivativeNo + " не існує.");
                return false;
            }
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            if (insuranceSpecialist.getDerivativeNoList().contains(derivativeNo)) {
                derivative = insuranceSpecialist.getDerivativeList().get(derivativeNo);
            } else {
                System.out.println("Деривативу № " + derivativeNo + " не існує.");
                return false;
            }
        }
        assert derivative != null;
        JsonScanner.derivativeDeletion(derivative,derivative.getDerivativeHolder().getUserId());
        return true;

    }
}
