package com.marych.insuranceApp.document.policy;

public abstract class InsurancePolicy {
    private int policyId;
    private boolean compulsory;
    private int holderId;
    private int insurerId;
    private int companyId;
    private double insuredSum;
    private double insuredPayment;
    private String signDate;
    private short riskPercentage;
    private short infoType;

    public InsurancePolicy(int policyId, boolean compulsory, int holderId, int insurerId, int companyId) {
        this.policyId = policyId;
        this.compulsory = compulsory;
        this.holderId = holderId;
        this.insurerId = insurerId;
        this.companyId = companyId;
    }

    public int getPolicyId() {
        return policyId;
    }

    public InsurancePolicy setPolicyId(int policyId) {
        this.policyId = policyId;
        return this;
    }

    public boolean isCompulsory() {
        return compulsory;
    }

    public InsurancePolicy setCompulsory(boolean compulsory) {
        this.compulsory = compulsory;
        return this;
    }

    public int getHolderId() {
        return holderId;
    }

    public InsurancePolicy setHolderId(int holderId) {
        this.holderId = holderId;
        return this;
    }

    public int isInsurerId() {
        return insurerId;
    }

    public InsurancePolicy setInsurerId(int insurerId) {
        this.insurerId = insurerId;
        return this;
    }

    public short getRiskPercentage() {
        return riskPercentage;
    }

    public InsurancePolicy setRiskPercentage(short riskPercentage) {
        this.riskPercentage = riskPercentage;
        return this;
    }

    public int getCompanyId() {
        return companyId;
    }

    public InsurancePolicy setCompanyId(int companyId) {
        this.companyId = companyId;
        return this;
    }

    public double getInsuredSum() {
        return insuredSum;
    }

    public InsurancePolicy setInsuredSum(double insuredSum) {
        this.insuredSum = insuredSum;
        return this;
    }

    public double getInsuredPayment() {
        return insuredPayment;
    }

    public InsurancePolicy setInsuredPayment(double insuredPayment) {
        this.insuredPayment = insuredPayment;
        return this;
    }

    public String getSignDate() {
        return signDate;
    }

    public InsurancePolicy setSignDate(String signDate) {
        this.signDate = signDate;
        return this;
    }

    public short getInfoType() {
        return infoType;
    }

    public InsurancePolicy setInfoType(short infoType) {
        this.infoType = infoType;
        return this;
    }
}
