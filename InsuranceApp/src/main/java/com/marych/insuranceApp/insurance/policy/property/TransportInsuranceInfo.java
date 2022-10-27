package com.marych.insuranceApp.insurance.policy.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marych.insuranceApp.insurance.policy.PolicyInfo;

public class TransportInsuranceInfo extends PolicyInfo {
    @JsonProperty("ownerFirstName")
    private String ownerFirstName;
    @JsonProperty("ownerLastName")
    private String ownerLastName;
    @JsonProperty("carBrand")
    private  String brand;
    @JsonProperty("carModel")

    private  String model;
    @JsonProperty("licensePlateNumber")
    private String licensePlateNumber;
    public TransportInsuranceInfo(int policyNo) {
        super.setPolicyNo(policyNo);
    }
    public TransportInsuranceInfo setRiskPercentage(double riskPercentage) {
        super.setRiskPercentage(riskPercentage);
        super.setInfoType(3);
        return this;
    }

    public TransportInsuranceInfo setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
        return this;
    }

    public TransportInsuranceInfo setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
        return this;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public String getBrand() {
        return brand;
    }

    public TransportInsuranceInfo setCarBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public TransportInsuranceInfo setCarModel(String model) {
        this.model = model;
        return this;
    }

    public TransportInsuranceInfo setSumInsured(double sumInsured) {
        super.setInsuredSum(sumInsured);
        return this;
    }

    public TransportInsuranceInfo setInsurancePayment(double insurancePayment) {
        super.setInsurancePayment(insurancePayment);
        return this;
    }
    public TransportInsuranceInfo setInfoType(int infoType){
        super.setInfoType(infoType);
        return this;
    }
    public TransportInsuranceInfo setLicensePlateNumber(String licensePlate) {
        if(this.licensePlateNumber == null) {
            this.licensePlateNumber = licensePlate;
        }
        return this;
    }


    public String getInfo() {
        return "Марка авто : " + "\"" + brand + "\"\n"
                + "Модель авто : " + "\""+ model + "\"\n"
                + "Державний номерний знак : " + licensePlateNumber;
    }

    @Override
    public String getType() {
        return "Майнове Страхування (Автомобільне Страхування)";
    }

    public String getCarBrand() {
        return brand;
    }

    public String getCarModel() {
        return model;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }
}
