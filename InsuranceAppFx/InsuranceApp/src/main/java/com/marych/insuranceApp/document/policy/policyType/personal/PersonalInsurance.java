package com.marych.insuranceApp.document.policy.policyType.personal;

public class PersonalInsurance {
    private int policyId;
    private String firstName;
    private String lastName;
    private String address;
    private String birthDate;

    public PersonalInsurance(int policyId, String firstName, String lastName) {
        this.policyId = policyId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getPolicyId() {
        return policyId;
    }

    public PersonalInsurance setPolicyId(int policyId) {
        this.policyId = policyId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PersonalInsurance setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PersonalInsurance setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public PersonalInsurance setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public PersonalInsurance setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
