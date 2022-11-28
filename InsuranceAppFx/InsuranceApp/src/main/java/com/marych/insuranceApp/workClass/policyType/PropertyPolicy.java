package com.marych.insuranceApp.workClass.policyType;

public class PropertyPolicy {
    private int policyId;
    private String firstName;
    private String lastName;
    private String carBrand;
    private String carModel;
    private String licensePlate;

    public PropertyPolicy(int policyId, String firstName, String lastName) {
        this.policyId = policyId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getPolicyId() {
        return policyId;
    }

    public PropertyPolicy setPolicyId(int policyId) {
        this.policyId = policyId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PropertyPolicy setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PropertyPolicy setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public PropertyPolicy setCarBrand(String carBrand) {
        this.carBrand = carBrand;
        return this;
    }

    public String getCarModel() {
        return carModel;
    }

    public PropertyPolicy setCarModel(String carModel) {
        this.carModel = carModel;
        return this;
    }

    public String getLicensePlate() {
        return licensePlate;

    }

    public PropertyPolicy setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }
}
