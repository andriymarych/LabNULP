package com.marych.insuranceApp.menu.DerivativeMenu;

import com.marych.insuranceApp.User.Customer;
import com.marych.insuranceApp.User.InsuranceSpecialist;
import com.marych.insuranceApp.User.jsonScanner.JsonScanner;
import com.marych.insuranceApp.insurance.derivative.Derivative;
import com.marych.insuranceApp.menu.InsuranceMenu.InsurancePolicyMenu;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;

import java.util.Map;
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
        Derivative derivative ;
        Map<Integer,Derivative> derivativeMap ;
        if (user instanceof Customer customer) {
            derivativeMap = customer.getDerivativeList();
            if (derivativeMap.containsKey(derivativeNo)) {
                derivative = customer.getDerivativeList().get(derivativeNo);
                JsonScanner.derivativeDeletion(derivative,derivative.getDerivativeHolder().getUserId());
            } else {
                System.out.println("Деривативу № " + derivativeNo + " не існує.");
                return false;
            }
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            derivativeMap = insuranceSpecialist.getDerivativeList();
            if (derivativeMap.containsKey(derivativeNo)) {
                derivative = insuranceSpecialist.getDerivativeList().get(derivativeNo);
                JsonScanner.derivativeDeletion(derivative,derivative.getDerivativeHolder().getUserId());
            } else {
                System.out.println("Деривативу № " + derivativeNo + " не існує.");
                return false;
            }
        }
        return true;
    }
}
