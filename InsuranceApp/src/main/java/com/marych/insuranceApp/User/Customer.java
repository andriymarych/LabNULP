package com.marych.insuranceApp.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marych.insuranceApp.insurance.derivative.Derivative;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;

import java.util.HashMap;
import java.util.Map;

public class Customer extends User {
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("birthDate")
    private String birthDate;
    @JsonProperty("email")
    private String email;
    @JsonProperty("ITN")
    private int ITN;
    @JsonIgnore
    private Map<Integer,InsurancePolicy> insurancePolicyList;
    @JsonIgnore
    private Map<Integer,Derivative> derivativeList;

    public Customer(int Id, String login, String password) {
        super(login, password);
        this.setUserId(Id);
        insurancePolicyList = new HashMap<>();
        derivativeList = new HashMap<>();
    }

    public Customer() {
        super();
        insurancePolicyList =  new HashMap<>();
        derivativeList = new HashMap<>();
    }

    public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
        insurancePolicyList.put(insurancePolicy.getPolicyNo(),insurancePolicy);
    }

    public void addDerivative(Derivative derivative) {
        derivativeList.put(derivative.getDerivativeNo(),derivative);
    }


    public Customer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Customer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Customer setBirthDate(String birthdate) {
        this.birthDate = birthdate;
        return this;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public Customer setITN(int ITN) {
        this.ITN = ITN;
        return this;
    }


    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public int getITN() {
        return ITN;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public Map<Integer, InsurancePolicy> getInsurancePolicyList() {
        return insurancePolicyList;
    }

    public void deleteInsurancePolicy(int policyNo){
        insurancePolicyList.remove(policyNo);
    }
    public void deleteDerivative(int derivativeNo){
        derivativeList.remove(derivativeNo);
    }

    public Map<Integer, Derivative> getDerivativeList() {
        return derivativeList;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
