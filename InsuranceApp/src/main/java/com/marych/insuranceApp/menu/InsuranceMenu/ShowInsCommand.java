package com.marych.insuranceApp.menu.insuranceMenu;

import com.marych.insuranceApp.Main;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.User;
import com.marych.insuranceApp.user.InsuranceSpecialist;

import java.util.Map;
import java.util.Objects;

public class ShowInsCommand implements MenuItem {
    Map<Integer, InsurancePolicy> insurancePolicies;
    User user ;
    InsurancePolicyMenu insurancePolicyMenu;
    @Override
    public boolean execute() {
        user = getUser();
        if(printPolicyList()) {
            insurancePolicyMenu = getInsurancePolicyMenu();
            insurancePolicyMenu.execute();
            return true;
        }else{
            insurancePolicyMenu = getInsurancePolicyMenu();
            insurancePolicyMenu.execute();
            return false;
        }
    }
    private boolean printPolicyList(){
        if (user instanceof Customer customer) {
            insurancePolicies = customer.getInsurancePolicyList();
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            insurancePolicies = insuranceSpecialist.getInsurancePolicyList();
        }
        if(insurancePolicies.size() != 0) {
            System.out.println("\nСписок страхових договорів:\n");
            var entrySet = insurancePolicies.entrySet();
            for (var entry : entrySet) {
                System.out.println(entry.getValue());
                System.out.println();
            }
            return true;
        }else{
            System.out.println("\nУ вас відсутні страхові договори.\n");
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

}
