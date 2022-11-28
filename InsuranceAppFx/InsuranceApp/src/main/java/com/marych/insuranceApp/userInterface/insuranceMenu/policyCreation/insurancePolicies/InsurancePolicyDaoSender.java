package com.marych.insuranceApp.userInterface.insuranceMenu.policyCreation.insurancePolicies;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.workClass.InsurancePolicy;

import java.sql.SQLException;
import java.sql.Statement;

public class InsurancePolicyDaoSender {
    private int policyId;
    private boolean compulsory;
    private int holderId;
    private int insurerId;
    private int companyId;
    private double insuredSum;
    private double insuredPayment;
    private int riskPercentage;
    private short infoType;
    private String date;

    public InsurancePolicyDaoSender(int policyId, int holderId, int insurerId, int companyId) {
        this.policyId = policyId;
        this.holderId = holderId;
        this.insurerId = insurerId;
        this.companyId = companyId;
    }

    public InsurancePolicyDaoSender setCompulsory(boolean compulsory) {
        this.compulsory = compulsory;
        return this;
    }

    public void send() {
        String query = "INSERT INTO \"insurance_policy\" " + " VALUES (" +
                policyId + ", " +
                compulsory + "," +
                holderId + ", " +
                insurerId + ", " +
                companyId + ", " +
                insuredSum + ", " +
                insuredPayment + ", '"+
                date + "', " +
                riskPercentage + ", " +
                infoType + ")";
        DatabaseHandler.getInstance().execUpdate(query);
    }

    public InsurancePolicyDaoSender setInsuredSum(double insuredSum) {
        this.insuredSum = insuredSum;
        return this;
    }

    public InsurancePolicyDaoSender setInsuredPayment(double insuredPayment) {
        this.insuredPayment = insuredPayment;
        return this;
    }

    public int getRiskPercentage() {
        return riskPercentage;
    }

    public InsurancePolicyDaoSender setRiskPersentage(int riskPersentage) {
        this.riskPercentage = riskPersentage;
        return this;
    }

    public InsurancePolicyDaoSender setInfoType(short infoType) {
        this.infoType = infoType;
        return this;
    }

    public InsurancePolicyDaoSender setDate(String date) {
        this.date = date;
        return this;
    }
}
