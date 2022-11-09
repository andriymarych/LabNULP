package com.marych.insuranceApp.insurance.policy.liability;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marych.insuranceApp.insurance.policy.PolicyInfo;

public class ProfessionalActivity extends PolicyInfo {

    @JsonProperty("firstName")
    private  String firstName;
    @JsonProperty("lastName")
    private  String lastName;
    @JsonProperty("professionalActivity")
    private  String professionalActivity;
    @JsonProperty("position")
    private  String position;
    @JsonProperty("companyName")
    private  String companyName;

    public ProfessionalActivity(int policyNo) {
        super.setPolicyNo(policyNo);
    }

    public ProfessionalActivity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ProfessionalActivity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ProfessionalActivity setProfessionalActivity(String professionalActivity) {
        this.professionalActivity = professionalActivity;
        return this;
    }

    public ProfessionalActivity setPosition(String position) {
        this.position = position;
        return this;
    }
    public ProfessionalActivity setInfoType(int infoType){
        super.setInfoType(infoType);
        return this;
    }

    public ProfessionalActivity setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String getInfo() {
        return "Сфера професійної діяльності : " + professionalActivity + "\n"
                + "Посада : " + position + "\n" + "Назва підприємства, установи, організації: " + companyName;
    }

    @Override
    public String getType() {
        return "Страхування Відповідальності ( Професійне страхування)";
    }

    public ProfessionalActivity setRiskPercentage(double riskPercentage) {
        super.setRiskPercentage(riskPercentage);
        return this;
    }

    public ProfessionalActivity setSumInsured(double sumInsured) {
        super.setInsuredSum(sumInsured);
        return this;
    }

    public ProfessionalActivity setInsurancePayment(double insurancePayment) {
        super.setInsurancePayment(insurancePayment);
        return this;
    }
    public double getRiskPercentage(){
        return super.getRiskPercentage();
    }

    public String getProfessionalActivity() {
        return professionalActivity;
    }

    public String getPosition() {
        return position;
    }
}
