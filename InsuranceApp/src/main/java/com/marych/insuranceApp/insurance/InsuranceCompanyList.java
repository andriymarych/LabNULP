package com.marych.insuranceApp.insurance;



import java.util.LinkedHashMap;
import java.util.Map;

public class InsuranceCompanyList {

    private Map<Integer, InsuranceCompany> insuranceCompanies;
    private static int insurancePolicyNumber ;
    private static int derivativeNumber;


    public InsuranceCompanyList() {
        insuranceCompanies = new LinkedHashMap<>();
    }
    public void add(InsuranceCompany insuranceCompany){
        insuranceCompanies.put(insuranceCompany.getCompanyId(),insuranceCompany);
    }
    public void print(){
        insuranceCompanies.forEach((key, value) -> System.out.println(key + " " +value));
    }
    public void addPolicyNumber(){
        insurancePolicyNumber++;
    }

    public static void setPolicyNumber(int insurancePolicyNumber) {
        InsuranceCompanyList.insurancePolicyNumber = insurancePolicyNumber;
    }

    public static void setDerivativeNumber(int derivativeNumber) {
        InsuranceCompanyList.derivativeNumber = derivativeNumber;
    }

    public void addDerivativeNumber(){
        derivativeNumber++;
    }
    public static int getNextDerivativeNumber(){
        derivativeNumber++;
        return derivativeNumber;
    }
    public static int getNextPolicyNumber(){
        insurancePolicyNumber++;
        return insurancePolicyNumber;
    }

    public Map<Integer, InsuranceCompany> asList() {
        return insuranceCompanies;
    }
}
