package com.marych.insuranceApp.menu.InsuranceMenu;

import com.marych.insuranceApp.User.Customer;
import com.marych.insuranceApp.User.InsuranceSpecialist;
import com.marych.insuranceApp.User.jsonScanner.JsonScanner;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;

import java.util.Map;
import java.util.Scanner;

import static com.marych.insuranceApp.Main.user;

public class DeleteInsCommand implements MenuItem {
    @Override
    public void execute() {
        policyDeletion();
        InsurancePolicyMenu insurancePolicyMenu = new InsurancePolicyMenu();
        insurancePolicyMenu.execute();

    }
    private void policyDeletion(){
        Scanner in = new Scanner(System.in);
        int policyNo;
        do {
            System.out.println("Введіть номер страхового договору, який необхідно видалити : ");
            policyNo = in.nextInt();
        }
        while (!deleteInsurancePolicy(policyNo));
    }

    private boolean deleteInsurancePolicy(int policyNo) {
        if (user instanceof Customer customer) {
            Map<Integer,InsurancePolicy> insurancePolicies = customer.getInsurancePolicyList();
            if (insurancePolicies.containsKey(policyNo)) {
                InsurancePolicy insurancePolicy = customer.getInsurancePolicyList().get(policyNo);
                JsonScanner.insuranceDeletion(insurancePolicy);
                return true;
            } else {
                System.out.println("Страхового договору № " + policyNo + " не існує.");
            }
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            Map<Integer,InsurancePolicy> insurancePolicies = insuranceSpecialist.getInsurancePolicyList();
            if (insurancePolicies.containsKey(policyNo)) {
                InsurancePolicy insurancePolicy = insuranceSpecialist.getInsurancePolicyList().get(policyNo);
                JsonScanner.insuranceDeletion(insurancePolicy);
                return true;
            } else {
                System.out.println("Страхового договору № " + policyNo + " не існує.");
            }
        }
        return false;
    }
}
