package com.marych.insuranceApp.document.derivative;

public class Derivative {
    private int derivativeId;
    private int holderId;
    private int insurerId;
    private int companyId;
    private double price;
    private String signDate;

    public Derivative(int derivativeId, int holderId, int insurerId, int companyId) {
        this.derivativeId = derivativeId;
        this.holderId = holderId;
        this.insurerId = insurerId;
        this.companyId = companyId;
    }

    public double getPrice() {
        return price;
    }

    public Derivative setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getSignDate() {
        return signDate;
    }

    public Derivative setSignDate(String signDate) {
        this.signDate = signDate;
        return this;
    }

    public int getDerivativeId() {
        return derivativeId;
    }

    public int getHolderId() {
        return holderId;
    }

    public int getInsurerId() {
        return insurerId;
    }

    public int getCompanyId() {
        return companyId;
    }
}
