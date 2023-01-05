package com.marych.insuranceApp.document.policy.policyType.liability;

public class ProfessionalActivityInsurance /*extends LiabilityInsurance */{
    private int policyId;
    private String firstName;
    private String lastName;
    private String companyName;
    private String professionalActivity;
    private String position;

    public ProfessionalActivityInsurance(int policyId, String firstName, String lastName, String companyName) {
        this.policyId = policyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
    }
 /*   public ProfessionalActivityInsurance(int policyId, boolean compulsory, int holderId, int insurerId, int companyId) {
        super(policyId,compulsory,holderId,insurerId,companyId);
        this.policyId = policyId;

    }*/
    public int getPolicyId() {
        return policyId;
    }

    public ProfessionalActivityInsurance setPolicyId(int policyId) {
        this.policyId = policyId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ProfessionalActivityInsurance setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfessionalActivityInsurance setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public ProfessionalActivityInsurance setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getProfessionalActivity() {
        return professionalActivity;
    }

    public ProfessionalActivityInsurance setProfessionalActivity(String professionalActivity) {
        this.professionalActivity = professionalActivity;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public ProfessionalActivityInsurance setPosition(String position) {
        this.position = position;
        return this;
    }
}
