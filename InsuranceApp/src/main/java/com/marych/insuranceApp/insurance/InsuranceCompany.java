package com.marych.insuranceApp.insurance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.InsuranceSpecialist;
import com.marych.insuranceApp.insurance.derivative.Derivative;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InsuranceCompany {
    @JsonProperty("companyId")
    int companyId;
    @JsonProperty("name")
    String name;
    @JsonProperty("address")
    String address;
    @JsonIgnore
    Map<Customer, ArrayList<InsurancePolicy>> insurancePolicyList;
    @JsonIgnore
    Map<Integer, InsuranceSpecialist> specialistsList;

    @JsonIgnore
    Map<Customer, ArrayList<Derivative>> derivativeList;

    public InsuranceCompany() {
        insurancePolicyList = new HashMap<>();
        specialistsList = new HashMap<>();
        derivativeList = new HashMap<>();

    }

    public InsuranceCompany(int companyId, String name, String address) {
        this.companyId = companyId;
        this.name = name;
        this.address = address;

        insurancePolicyList = new HashMap<>();
        specialistsList = new HashMap<>();
        derivativeList = new HashMap<>();
    }

    public void addSpecialist(ArrayList<InsuranceSpecialist> insuranceSpecialistList) {
        for (InsuranceSpecialist insuranceSpecialist : insuranceSpecialistList) {
            specialistsList.put(insuranceSpecialist.getUserId(), insuranceSpecialist);
        }
    }

    public ArrayList<InsurancePolicy> getInsurancePolicyList(Customer customer) {
        return insurancePolicyList.get(customer);
    }

    public void addInsurancePolicy(Customer customer, InsurancePolicy insurancePolicy) {
        if (!insurancePolicyList.containsKey(customer)) {
            insurancePolicyList.put(customer, new ArrayList<>());
            insurancePolicyList.get(customer).add(insurancePolicy);
        } else {
            insurancePolicyList.get(customer).add(insurancePolicy);
        }
    }

    public void addDerivative(Customer customer, Derivative derivative) {
        if (!derivativeList.containsKey(customer)) {
            derivativeList.put(customer, new ArrayList<>());
            derivativeList.get(customer).add(derivative);
        } else {
            derivativeList.get(customer).add(derivative);
        }
    }

    public ArrayList<Derivative> getDerivativeList(Customer customer) {
        return derivativeList.get(customer);
    }

    public Map<Integer, InsuranceSpecialist> getSpecialistsList() {
        return specialistsList;
    }

    public void printSpecialists() {
        System.out.println("Список спеціалістів страхової компанії " + getName() + " :");
        specialistsList.forEach((key, value) -> System.out.println(key + " " + value));
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "\"" + name + "\"";
    }

}
