package com.marych.insuranceApp.user;

public class InsuranceSpecialist {
    private int userId;
    private String firstName;
    private String lastName;
    private String CompanyId;
    private String email;

    public InsuranceSpecialist(int userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUserId() {
        return userId;
    }

    public InsuranceSpecialist setUserId(int userId) {
        this.userId = userId;
        return this;
    }
    public String getFullName(){
        return firstName + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public InsuranceSpecialist setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public InsuranceSpecialist setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public InsuranceSpecialist setCompanyId(String companyId) {
        CompanyId = companyId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public InsuranceSpecialist setEmail(String email) {
        this.email = email;
        return this;
    }
}
