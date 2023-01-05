package com.marych.insuranceApp.document.policy;

public class ObservableInsurancePolicy {
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

    public ObservableInsurancePolicy(int policyId, boolean compulsory, int holderId, int insurerId, int companyId) {
        this.policyId = policyId;
        this.compulsory = compulsory;
        this.holderId = holderId;
        this.insurerId = insurerId;
        this.companyId = companyId;
    }

    public int getPolicyId() {
        return policyId;
    }

    public ObservableInsurancePolicy setPolicyId(int policyId) {
        this.policyId = policyId;
        return this;
    }

    public boolean isCompulsory() {
        return compulsory;
    }

    public ObservableInsurancePolicy setCompulsory(boolean compulsory) {
        this.compulsory = compulsory;
        return this;
    }

    public int getHolderId() {
        return holderId;
    }

    public ObservableInsurancePolicy setHolderId(int holderId) {
        this.holderId = holderId;
        return this;
    }

    public int isInsurerId() {
        return insurerId;
    }

    public ObservableInsurancePolicy setInsurerId(int insurerId) {
        this.insurerId = insurerId;
        return this;
    }

    public short getRiskPercentage() {
        return riskPercentage;
    }

    public ObservableInsurancePolicy setRiskPercentage(short riskPercentage) {
        this.riskPercentage = riskPercentage;
        return this;
    }

    public int getCompanyId() {
        return companyId;
    }

    public ObservableInsurancePolicy setCompanyId(int companyId) {
        this.companyId = companyId;
        return this;
    }

    public double getInsuredSum() {
        return insuredSum;
    }

    public ObservableInsurancePolicy setInsuredSum(double insuredSum) {
        this.insuredSum = insuredSum;
        return this;
    }

    public double getInsuredPayment() {
        return insuredPayment;
    }

    public ObservableInsurancePolicy setInsuredPayment(double insuredPayment) {
        this.insuredPayment = insuredPayment;
        return this;
    }

    public String getSignDate() {
        return signDate;
    }

    public ObservableInsurancePolicy setSignDate(String signDate) {
        this.signDate = signDate;
        return this;
    }

    public short getInfoType() {
        return infoType;
    }

    public ObservableInsurancePolicy setInfoType(short infoType) {
        this.infoType = infoType;
        return this;
    }
}
