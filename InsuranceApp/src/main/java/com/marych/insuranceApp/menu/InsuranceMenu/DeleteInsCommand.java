package com.marych.insuranceApp.menu.insuranceMenu;

import com.marych.insuranceApp.Main;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.scanners.jsonScanner.JsonScanner;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.User;
import com.marych.insuranceApp.user.InsuranceSpecialist;


import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class DeleteInsCommand implements MenuItem {
    private InsurancePolicyMenu insurancePolicyMenu;
    private User user;
    private JsonScanner jsonScanner;

    @Override
    public boolean execute() {
        user = getUser();
        jsonScanner = getJsonScanner();
        if (policyDeletion()) {
            insurancePolicyMenu = getInsurancePolicyMenu();
            insurancePolicyMenu.execute();
            return true;
        }
        return false;
    }

    private boolean policyDeletion() {
        Scanner in = new Scanner(System.in);
        int policyNo;
        for (int i = 0; i < 2; i++) {
            System.out.println("Введіть номер страхового договору, який необхідно видалити : ");
            policyNo = in.nextInt();
            if (deleteInsurancePolicy(policyNo)) {
                return true;
            }
        }
        return false;
    }

    private boolean deleteInsurancePolicy(int policyNo) {
        if (user instanceof Customer customer) {
            Map<Integer, InsurancePolicy> insurancePolicies = customer.getInsurancePolicyList();
            if (insurancePolicies.containsKey(policyNo)) {
                InsurancePolicy insurancePolicy = customer.getInsurancePolicyList().get(policyNo);
                jsonScanner.insuranceDeletion(insurancePolicy);
                return true;
            } else {
                System.out.println("Страхового договору № " + policyNo + " не існує.");
            }
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            Map<Integer, InsurancePolicy> insurancePolicies = insuranceSpecialist.getInsurancePolicyList();
            if (insurancePolicies.containsKey(policyNo)) {
                InsurancePolicy insurancePolicy = insuranceSpecialist.getInsurancePolicyList().get(policyNo);
                jsonScanner.insuranceDeletion(insurancePolicy);
                return true;
            } else {
                System.out.println("Страхового договору № " + policyNo + " не існує.");
            }
        }
        return false;
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

    public void setInsurancePolicyMenu(InsurancePolicyMenu insurancePolicyMenu) {
        this.insurancePolicyMenu = insurancePolicyMenu;
    }

    public InsurancePolicyMenu getInsurancePolicyMenu() {
        return Objects.requireNonNullElseGet(insurancePolicyMenu, InsurancePolicyMenu::new);
    }

    public void setJsonScanner(JsonScanner jsonInfoScanner) {
        this.jsonScanner = jsonInfoScanner;
    }

    public JsonScanner getJsonScanner() {
        return Objects.requireNonNullElseGet(jsonScanner, JsonScanner::new);
    }
}
