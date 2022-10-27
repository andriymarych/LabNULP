package com.marych.insuranceApp.menu.InsuranceMenu;

import com.marych.insuranceApp.User.Customer;
import com.marych.insuranceApp.User.InsuranceSpecialist;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.menu.commonCommands.MenuItem;

import java.util.ArrayList;
import java.util.Map;

import static com.marych.insuranceApp.Main.user;

public class ShowInsCommand implements MenuItem {
    Map<Integer, InsurancePolicy> insurancePolicies;
    ArrayList<Integer> policyNoList;
    @Override
    public void execute() {
        printPolicyList();
        InsurancePolicyMenu insurancePolicyMenu = new InsurancePolicyMenu();
        insurancePolicyMenu.execute();
    }
    private void printPolicyList(){
        if (user instanceof Customer customer) {
            insurancePolicies = customer.getInsurancePolicyList();
            policyNoList = customer.getPolicyNoList();
        } else if (user instanceof InsuranceSpecialist insuranceSpecialist) {
            insurancePolicies = insuranceSpecialist.getInsurancePolicyList();
            policyNoList = insuranceSpecialist.getPolicyNoList();
        }
        if(insurancePolicies.size() != 0) {
            System.out.println("\nСписок страхових договорів:\n");
            for (int i = 0; i < insurancePolicies.size(); i++) {
                System.out.println(insurancePolicies.get(policyNoList.get(i)));
                System.out.println();
            }
        }else{
            System.out.println("\nУ вас ще немає створених страхових договорів");
        }
    }

}
