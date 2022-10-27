package com.marych.insuranceApp.insurance.policy.personal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marych.insuranceApp.insurance.policy.PolicyInfo;

import java.time.LocalDate;

public class LifeInsuranceInfo extends PolicyInfo {
    @JsonProperty("firstName")
    private  String firstName;
    @JsonProperty("lastName")
    private  String lastName;
    @JsonProperty("birthDate")
    private String birthDate;
    @JsonProperty("address")
    private String address;

    public LifeInsuranceInfo(int policyNo) {
        super.setPolicyNo(policyNo);
        setInfoType(1);
    }

    public LifeInsuranceInfo setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public LifeInsuranceInfo setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LifeInsuranceInfo setRiskPercentage(double riskPercentage) {
        super.setRiskPercentage(riskPercentage);
        return this;
    }
  /*  public LifeInsuranceInfo setInfoId(int infoId){
        super.setInfoId(infoId);
        return this;
    }*/
    public LifeInsuranceInfo setInfoType(int infoType){
        super.setInfoType(infoType);
        return this;
    }

    public LifeInsuranceInfo setSumInsured(double sumInsured) {
        super.setInsuredSum(sumInsured);
        return this;
    }

    public LifeInsuranceInfo setInsurancePayment(double insurancePayment) {
        super.setInsurancePayment(insurancePayment);
        return this;
    }

    public LifeInsuranceInfo setAddress(String address) {
        if (this.address == null) {
            this.address = address;
        }
        return this;
    }
    public LifeInsuranceInfo setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    @Override
    public String getType() {
        return "Особисте Страхування (Страхування життя) ";
    }

    public String getInfo() {
        return "Застрахована особа : " + firstName + " " + lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }
}
