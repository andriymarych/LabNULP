package com.marych.insuranceApp.workClass.policyType;

public class LiabilityPolicy {
    private int policyId;
    private String firstName;
    private String lastName;
    private String companyName;
    private String professionalActivity;
    private String position;

    public LiabilityPolicy(int policyId, String firstName, String lastName, String companyName) {
        this.policyId = policyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
    }

    public int getPolicyId() {
        return policyId;
    }

    public LiabilityPolicy setPolicyId(int policyId) {
        this.policyId = policyId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public LiabilityPolicy setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public LiabilityPolicy setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public LiabilityPolicy setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getProfessionalActivity() {
        return professionalActivity;
    }

    public LiabilityPolicy setProfessionalActivity(String professionalActivity) {
        this.professionalActivity = professionalActivity;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public LiabilityPolicy setPosition(String position) {
        this.position = position;
        return this;
    }
}
