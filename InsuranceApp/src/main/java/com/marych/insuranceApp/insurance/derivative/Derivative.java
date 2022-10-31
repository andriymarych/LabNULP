package com.marych.insuranceApp.insurance.derivative;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marych.insuranceApp.User.Customer;
import com.marych.insuranceApp.User.InsuranceSpecialist;
import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;

import java.time.LocalDate;
import java.util.*;

public class Derivative {
    @JsonProperty("derivativeNo")
    private int derivativeNo;
    @JsonIgnore
    private Map<Integer,InsurancePolicy> policyList;
    @JsonProperty("price")
    private double price;
    @JsonProperty("derivativeHolder")
    private Customer derivativeHolder;
    @JsonProperty("insurer")
    private InsuranceSpecialist insurer;
    @JsonProperty("insuranceCompany")
    private InsuranceCompany insuranceCompany;
    @JsonProperty("date")
    private String date;

    public Derivative(int derivativeNo, Customer derivativeHolder, InsuranceSpecialist insuranceSpecialist) {
        this.derivativeNo = derivativeNo;
        this.derivativeHolder = derivativeHolder;
        this.insurer = insuranceSpecialist;
        this.policyList = new HashMap<>();
        this.date =   LocalDate.now().toString();
    }


    public Derivative setPolicyList(ArrayList<InsurancePolicy> insurancePolicies){
        insurancePolicies.sort(Comparator.comparing( a -> a.getPolicyInfo().getRiskPercentage()));
        Collections.reverse(insurancePolicies);
        for(InsurancePolicy insurancePolicy : insurancePolicies){
            addPolicy(insurancePolicy);
        }
        return this;
    }


    public void addPolicy(InsurancePolicy insurancePolicy) {
        policyList.put(insurancePolicy.getPolicyNo(),insurancePolicy);
    }


    public Derivative setPrice(double price) {
        this.price = price;
        return this;
    }

    public Derivative setInsuranceCompany(InsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
        return this;
    }

    public Derivative setDate(String date) {
        this.date = date;
        return this;
    }

    public int getDerivativeNo() {
        return derivativeNo;
    }

    public Map<Integer, InsurancePolicy> getPolicyList() {
        return policyList;
    }

    public double getPrice() {
        return price;
    }

    public Customer getDerivativeHolder() {
        return derivativeHolder;
    }

    public InsuranceSpecialist getInsurer() {
        return insurer;
    }

    public InsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }

    public String getDate() {
        return date;
    }

    public void showDerivative(){
        System.out.println("|Дериватив № " + derivativeNo + " |");
        System.out.println("Страховий спеціаліст : " + insurer);
        System.out.println("Власник : " + derivativeHolder);
        System.out.println("Сумарна вартість деревативу : " + price);
        System.out.println("Перелік страхових договорів : \n");
        var entrySet = policyList.entrySet();
        for (var entry : entrySet){
            System.out.println(entry.getValue());
            System.out.println();
        }
        System.out.println("Дата створення деривативу: " + " ".repeat(20) + date);
    }
}
