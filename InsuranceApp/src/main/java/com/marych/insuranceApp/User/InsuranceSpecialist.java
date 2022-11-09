package com.marych.insuranceApp.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marych.insuranceApp.insurance.derivative.Derivative;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;

import java.util.HashMap;
import java.util.Map;

public class InsuranceSpecialist extends User {
    @JsonProperty("firstName")
    String firstName;
    @JsonProperty("lastName")
    String lastName;
    @JsonProperty("email")
    String email;
    @JsonProperty("insuranceCompanyId")
    int insuranceCompanyId;
    @JsonIgnore
    Map<Integer, InsurancePolicy> insurancePolicyList;

    @JsonIgnore
    Map<Integer, Derivative> derivativeList;

    public InsuranceSpecialist(int Id, String login, String password) {
        super(login, password);
        this.setUserId(Id);
        this.setUserRole(1);
        insurancePolicyList = new HashMap<>();
        derivativeList = new HashMap<>();
    }

    public InsuranceSpecialist() {
        super();
        insurancePolicyList = new HashMap<>();
        derivativeList = new HashMap<>();
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public InsuranceSpecialist setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Map<Integer, InsurancePolicy> getInsurancePolicyList() {
        return insurancePolicyList;
    }


    public Map<Integer, Derivative> getDerivativeList() {
        return derivativeList;
    }

    public boolean addInsurancePolicy(InsurancePolicy insurancePolicy) {
        if (insurancePolicy != null) {
            insurancePolicyList.put(insurancePolicy.getPolicyNo(), insurancePolicy);
            return true;
        }
        return false;
    }

    public boolean addDerivative(Derivative derivative) {
        if (derivative != null) {
            derivativeList.put(derivative.getDerivativeNo(), derivative);
            return true;
        }
        return false;
    }

    public boolean deleteInsurancePolicy(int policyNo) {
        if (insurancePolicyList.containsKey(policyNo)) {
            insurancePolicyList.remove(policyNo);
            return true;
        }
        return false;
    }

    public boolean deleteDerivative(int derivativeNo) {
        if (derivativeList.containsKey(derivativeNo)) {
            derivativeList.remove(derivativeNo);
            return true;
        }
        return false;
    }

    public InsuranceSpecialist setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setInsuranceCompanyId(int insuranceCompanyNo) {
        this.insuranceCompanyId = insuranceCompanyNo;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getInsuranceCompanyId() {
        return insuranceCompanyId;
    }

    public Derivative getDerivative(int derivativeNo){
        return derivativeList.getOrDefault(derivativeNo,null);
    }
    public InsurancePolicy getInsurancePolicy(int policyNo){
        return insurancePolicyList.getOrDefault(policyNo, null);

    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
