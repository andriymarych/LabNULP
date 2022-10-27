package com.marych.insuranceApp.menu.DerivativeMenu.showDerCommand;

import com.marych.insuranceApp.insurance.derivative.DerivativeFilter;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.menu.DerivativeMenu.DerivativeMenuCommand;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.marych.insuranceApp.Main.user;

public class ShowFilteredDerivativeCommand implements MenuItem {
    @Override
    public void execute() throws IOException {
        showFilteredDerivative();
        DerivativeMenuCommand derivativeMenu = new DerivativeMenuCommand();
        derivativeMenu.execute();
    }
    private void showFilteredDerivative(){
        int derivativeNo;
        ArrayList<InsurancePolicy> insurancePolicyList ;
        Scanner in = new Scanner(System.in);
        ShowAllDerivativesCommand.showDerivativeList();
        do {
            System.out.println("Введіть номер деривативу : ");
            derivativeNo = in.nextInt();
            DerivativeFilter derivativeFilter = new DerivativeFilter(user, derivativeNo);
            insurancePolicyList = derivativeFilter.filterPrice();
        } while (insurancePolicyList == null);
        if(insurancePolicyList.size() != 0 ) {
            System.out.println("Відфільтрований список страхових зобовʼязань :\n");
            for (InsurancePolicy insurancePolicy : insurancePolicyList) {
                System.out.println(insurancePolicy);
                System.out.println("-".repeat(60));
            }
        }else{
            System.out.println("Страхових зобовʼязань за вказаним фільтровим параметром не існує.");
        }
    }


}

