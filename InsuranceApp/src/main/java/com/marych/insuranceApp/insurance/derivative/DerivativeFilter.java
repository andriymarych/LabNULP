package com.marych.insuranceApp.insurance.derivative;

import com.marych.insuranceApp.User.Customer;
import com.marych.insuranceApp.User.InsuranceSpecialist;
import com.marych.insuranceApp.User.User;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class DerivativeFilter {
    private final User user;
    Scanner in = new Scanner(System.in);
    private Derivative derivative ;
    private int derivativeNo;
    public DerivativeFilter(User user, int derivativeNo) {
        this.user = user;
        this.derivativeNo = derivativeNo;
        Map<Integer, Derivative> derivativeList ;
        if(user instanceof Customer customer){
            derivativeList = customer.getDerivativeList();
            if(derivativeList.containsKey(derivativeNo)) {
                derivative = derivativeList.get(derivativeNo);
            }
        }else if(user instanceof InsuranceSpecialist insuranceSpecialist){
            derivativeList = insuranceSpecialist.getDerivativeList();
            derivative =  derivativeList.get(derivativeNo);
        }
    }

    public ArrayList<InsurancePolicy> filterPrice(){
        if(derivative == null){
            System.out.println("Деривативу №" + derivativeNo + " не існує, повторіть спробу.");
            return null;
        }
        InsurancePolicy insurancePolicy;
        double sumInsured;
        int startSumInsured;
        int endSumInsured;
        Map<Integer,InsurancePolicy> policyListMap;
        ArrayList<InsurancePolicy> policyList = new ArrayList<>();
        ArrayList<InsurancePolicy> filteredPolicyList = new ArrayList<>();
        policyListMap = derivative.getPolicyList();
        System.out.println("Введіть діапазон значень страхових сум зобовʼязань у деривативі:");
        System.out.println("Від :");
        startSumInsured = in.nextInt();
        System.out.println("До :");
        endSumInsured = in.nextInt();
        var keySet = policyListMap.keySet();
        for (var policyNo : keySet) {
            policyList.add(derivative.getPolicyList().get(policyNo));
        }
        for (InsurancePolicy policy : policyList) {
            insurancePolicy = policy;
            sumInsured = insurancePolicy.getPolicyInfo().getSumInsured();
            if (sumInsured >= startSumInsured && sumInsured <= endSumInsured) {
                filteredPolicyList.add(insurancePolicy);
            }
        }
        return filteredPolicyList;
    }
}
