package com.marych.insuranceApp.insurance.policy;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class PolicyInfo {


    @JsonProperty("infoType")
    private int infoType;
    @JsonProperty("riskPercentage")
    private double riskPercentage;
    @JsonProperty("insuredSum")
    private double insuredSum;
    @JsonProperty("insurancePayment")
    private double insurancePayment;

    @JsonProperty("policyNo")
    private int policyNo;


    public PolicyInfo() {
    }

    public abstract String getInfo();
    public abstract String getType();

    public double getRiskPercentage() {
        return riskPercentage;
    }

    public double getSumInsured() {
        return insuredSum;
    }

    public double getInsurancePayment() {
        insurancePayment *= 100;
        insurancePayment = Math.round(insurancePayment);
        insurancePayment /= 100;
        return insurancePayment;
    }

    public int getInfoTypeInt(){
        return infoType;
    }

    public PolicyInfo setInfoType(int infoType) {
        this.infoType = infoType;
        return this;
    }
    public PolicyInfo setRiskPercentage(double riskPercentage) {
        if (this.riskPercentage == 0) {
            this.riskPercentage = riskPercentage;
        }
        return this;
    }

    public int getInfoType() {
        return infoType;
    }

    public PolicyInfo setPolicyNo(int policyNo) {
        this.policyNo = policyNo;
        return this;
    }

    public int getPolicyNo() {
        return policyNo;
    }

    public PolicyInfo setInsuredSum(double sumInsured) {
        if (this.insuredSum == 0) {
            this.insuredSum = sumInsured;
        }
        return this;
    }

    public PolicyInfo setInsurancePayment(double insurancePayment) {
        if (this.insurancePayment == 0) {
            this.insurancePayment = insurancePayment;
        }
        return this;
    }

}
