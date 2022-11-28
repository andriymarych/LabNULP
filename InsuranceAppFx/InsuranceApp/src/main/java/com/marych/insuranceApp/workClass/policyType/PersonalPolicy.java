package com.marych.insuranceApp.workClass.policyType;

public class PersonalPolicy {
    private int policyId;
    private String firstName;
    private String lastName;
    private String address;
    private String birthDate;

    public PersonalPolicy(int policyId, String firstName, String lastName) {
        this.policyId = policyId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getPolicyId() {
        return policyId;
    }

    public PersonalPolicy setPolicyId(int policyId) {
        this.policyId = policyId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PersonalPolicy setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PersonalPolicy setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public PersonalPolicy setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public PersonalPolicy setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
