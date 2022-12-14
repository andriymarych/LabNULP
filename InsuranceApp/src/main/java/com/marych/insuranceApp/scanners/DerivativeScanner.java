package com.marych.insuranceApp.scanners;

import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.InsuranceSpecialist;
import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.insurance.InsuranceCompanyList;
import com.marych.insuranceApp.insurance.derivative.Derivative;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static com.marych.insuranceApp.Main.insuranceCompanyList;


public class DerivativeScanner {

    private ArrayList<InsurancePolicy> policyList;

    private Customer derivativeHolder;

    private InsuranceSpecialist insuranceSpecialist;
    private InsuranceCompany insuranceCompany;


    public Derivative createDerivative(Customer derivativeHolder) {
        insuranceCompany = InsuranceScanner.selectCompany();
        this.derivativeHolder = derivativeHolder;
        insuranceSpecialist = InsuranceScanner.selectInsurer(insuranceCompany);
        return create();
    }

    private Derivative create() {
        int derivativeNo = selectDerivativeNo();
        if (insuranceCompany == null) {
            return null;
        }

        if (insuranceSpecialist == null) {
            return null;
        }
        policyList = selectInsurancePolicies();
        double price = selectPrice();
        return new Derivative(derivativeNo, derivativeHolder, insuranceSpecialist)
                .setPolicyList(policyList)
                .setInsuranceCompany(insuranceCompany)
                .setPrice(price);
    }

    public Derivative createDerivative(InsuranceSpecialist insuranceSpecialist) {
        insuranceCompany = insuranceCompanyList.asList().get(insuranceSpecialist.getInsuranceCompanyId());
        ;
        this.insuranceSpecialist = insuranceSpecialist;
        derivativeHolder = InsuranceScanner.selectCustomer();
        return create();
    }

    private double selectPrice() {
        double price = 0;
        for (InsurancePolicy insurancePolicy : policyList) {
            price += insurancePolicy.getInsurencePayment();
        }
        return price * 1.03;
    }

    private int selectDerivativeNo() {
        return InsuranceCompanyList.getNextDerivativeNumber();
    }

    private ArrayList<InsurancePolicy> selectInsurancePolicies() {
        ArrayList<InsurancePolicy> derivativeInsurancePolicies = new ArrayList<>();
        Map<Integer, InsurancePolicy> insurancePolicies = derivativeHolder.getInsurancePolicyList();
        Scanner in = new Scanner(System.in);
        int policyNo;
        printPolicyList();
        System.out.println("?????????????? ???????????? ?????????????????? ??????????????????(?????????? ????????????), ?????? ?????????????????????????? ??????????????????:");
        String line = in.nextLine();
        String[] policiesNumbersString = line.split(" ");
        for (String s : policiesNumbersString) {
            policyNo = Integer.parseInt(s);
            if (insurancePolicies.containsKey(policyNo)) {
                derivativeInsurancePolicies.add(insurancePolicies.get(policyNo));
            } else {
                System.out.println("???????????????????? ???????????????? ??? " + policyNo + "???? ??????????");
            }
        }
        return derivativeInsurancePolicies;
    }

    private void printPolicyList() {
        Map<Integer, InsurancePolicy> insurancePolicies;
        ArrayList<Integer> policyNoList;
        insurancePolicies = derivativeHolder.getInsurancePolicyList();
        //policyNoList = derivativeHolder.getPolicyNoList();
        System.out.println("\n???????????? C???????????????? ??????????????????:");
        var entrySet = insurancePolicies.entrySet();
        for (var entry : entrySet) {
            System.out.println(entry.getValue());
            System.out.println();

        }
    }
}
