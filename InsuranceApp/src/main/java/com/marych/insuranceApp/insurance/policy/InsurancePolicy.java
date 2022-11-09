package com.marych.insuranceApp.insurance.policy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.InsuranceSpecialist;
import com.marych.insuranceApp.insurance.InsuranceCompany;

import java.time.LocalDate;

public class InsurancePolicy {
    @JsonProperty("policyNo")
    private  int policyNo;
    @JsonProperty("compulsory")
    private boolean compulsory;
    @JsonIgnore
    private PolicyInfo policyInfo;
    @JsonProperty("policyHolder")
    private Customer policyHolder;
    @JsonProperty("insurer")
    private InsuranceSpecialist insurer;
    @JsonProperty("insuranceCompany")
    private InsuranceCompany insuranceCompany;
    @JsonProperty("date")
    private String date;


    public InsurancePolicy(int policyNo, Customer policyHolder, InsuranceSpecialist insurer) {
        this.policyNo = policyNo;
        this.policyHolder = policyHolder;
        this.insurer = insurer;
        this.date =   LocalDate.now().toString();
    }

    public InsurancePolicy() {
    }

    public InsurancePolicy setDate(String date) {
        this.date = date;
        return this;
    }
    public void setPolicyInfo(PolicyInfo policyInfo) {
        this.policyInfo = policyInfo;
    }
    public InsurancePolicy setCompulsory(boolean compulsory) {
        this.compulsory = compulsory;
        return this;
    }
    public double getInsurencePayment(){
        return policyInfo.getInsurancePayment();
    }

    public InsurancePolicy setInsuranceCompany(InsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
        return this;
    }
    public int getInfoType(){
        return policyInfo.getInfoTypeInt();
    }

    public String getDate() {
        return date;
    }

    public boolean getCompulsory(){
        return compulsory;
    }

    public PolicyInfo getPolicyInfo() {
        return policyInfo;
    }

    public Customer getPolicyHolder() {
        return policyHolder;
    }

    public InsuranceSpecialist getInsurer() {
        return insurer;
    }

    public InsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }

    @Override
    public String toString() {
        LocalDate localDate = LocalDate.now();
        String compulsoryForm;
        if (compulsory) {
            compulsoryForm = "Обовʼязкова";
        } else {
            compulsoryForm = "Небовʼязкова";
        }
        double riskPercentage = policyInfo.getRiskPercentage();
        String riskPercentageStr;
        if(riskPercentage < 0.3){
            riskPercentageStr = "Низький";
        }else if (riskPercentage < 0.75){
            riskPercentageStr = "Середній";
        }else {
            riskPercentageStr = "Високий";
        }
        return "|Страховий договір №" + policyNo + "|\n" +
                "Форма проведення страхування : " + compulsoryForm + "\n" +
                "Обʼєкт Страхування :\n" + policyInfo.getType() + "\n" +
                policyInfo.getInfo() + "\n" +
                "Страхувальник : " + policyHolder + "\n" +
                "Страховик : " + insurer + "\n" +
                "Рівень ризику : " + policyInfo.getRiskPercentage() + " (" + riskPercentageStr +")\n" +
                "Страхувальна Компанія : " + insuranceCompany + "\n" +
                "Страхова сума : " + policyInfo.getSumInsured() + "\n" +
                "Страховий платіж : " + policyInfo.getInsurancePayment() + "\n" +
                "Дата" + " ".repeat(20) + localDate;
    }

    public int getPolicyNo() {
        return policyNo;
    }
}
