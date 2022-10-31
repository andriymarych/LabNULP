package com.marych.insuranceApp.User;

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
    Map<Integer,InsurancePolicy>  insurancePolicyList;

    @JsonIgnore
    Map<Integer,Derivative> derivativeList;

    public InsuranceSpecialist(int Id, String login, String password) {
        super(login, password);
        this.setUserId(Id);
        insurancePolicyList = new HashMap<>();
        derivativeList = new HashMap<>();
    }
    public InsuranceSpecialist(){
        super();
        insurancePolicyList = new HashMap<>();
        derivativeList = new HashMap<>();
    }

    @Override
    String getFirstName() {
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

    public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
        insurancePolicyList.put(insurancePolicy.getPolicyNo(),insurancePolicy);
    }
    public void addDerivative(Derivative derivative) {
        derivativeList.put(derivative.getDerivativeNo(),derivative);
    }
    public void deleteInsurancePolicy(int policyNo){
        insurancePolicyList.remove(policyNo);
    }
    public void deleteDerivative(int derivativeNo){
        derivativeList.remove(derivativeNo);
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

    @Override
    public String toString() {
        return   firstName + " " + lastName;
    }
}
