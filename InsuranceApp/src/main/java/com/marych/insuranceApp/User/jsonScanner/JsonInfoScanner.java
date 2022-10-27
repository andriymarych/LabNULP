package com.marych.insuranceApp.User.jsonScanner;


import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import com.marych.insuranceApp.insurance.policy.PolicyInfo;
import com.marych.insuranceApp.insurance.policy.liability.ProfessionalActivity;
import com.marych.insuranceApp.insurance.policy.personal.LifeInsuranceInfo;
import com.marych.insuranceApp.insurance.policy.property.TransportInsuranceInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class JsonInfoScanner {

    static PolicyInfo getInfo(int number) {
        String insurancePath = "/Users/andriymarych/Desktop/Прикладне Програмування/Code/InsuranceApp/src/main/Json/PolicyInfo.json";
        try {
            JSONArray jsonArray = new JSONArray(new String(Files.readAllBytes(Paths.get(insurancePath))));
            JSONObject insurancePolicyObject = jsonArray.getJSONObject(number);
            int infoType = insurancePolicyObject.getInt("infoType");
            switch (infoType) {
                case 1:
                    return getLifeInsuranceInfo(insurancePolicyObject);
                case 2:
                    return getProfessionalActivityInfo(insurancePolicyObject);
                case 3:
                    return getTransportInsuranceInfo(insurancePolicyObject);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addPolicyInfo(InsurancePolicy insurancePolicy) throws IOException {
        int infoType = insurancePolicy.getInfoType();
        switch (infoType) {
            case 1 -> addLifeInsuranceInfo(insurancePolicy);
            case 2 -> addProfessionalActivityInfo(insurancePolicy);
            case 3 -> addTransportInsuranceInfo(insurancePolicy);
        }
    }

    private static JSONObject addGeneralInfo(PolicyInfo policyInfo) {
        JSONObject obj = new JSONObject();
        obj.put("policyNo", policyInfo.getPolicyNo());
        obj.put("infoType", policyInfo.getInfoTypeInt());
        obj.put("riskPercentage", policyInfo.getRiskPercentage());
        obj.put("insuredSum", policyInfo.getSumInsured());
        obj.put("insurancePayment", policyInfo.getInsurancePayment());
        return obj;
    }

    private static PolicyInfo getGeneralInfo(JSONObject insurancePolicyObject) {
        int infoType = insurancePolicyObject.getInt("infoType");
        int policyNo = insurancePolicyObject.getInt("policyNo");
        double riskPercentage = insurancePolicyObject.getDouble("riskPercentage");
        double insuredSum = insurancePolicyObject.getDouble("insuredSum");
        double insurancePayment = insurancePolicyObject.getDouble("insurancePayment");
        return switch (infoType) {
            case 1 -> new LifeInsuranceInfo(policyNo)
                    .setInfoType(infoType)
                    .setRiskPercentage(riskPercentage)
                    .setInsurancePayment(insurancePayment)
                    .setSumInsured(insuredSum);
            case 2 -> new ProfessionalActivity(policyNo)
                    .setInfoType(infoType)
                    .setRiskPercentage(riskPercentage)
                    .setInsurancePayment(insurancePayment)
                    .setSumInsured(insuredSum);
            case 3 -> new TransportInsuranceInfo(policyNo)
                    .setInfoType(infoType)
                    .setRiskPercentage(riskPercentage)
                    .setInsurancePayment(insurancePayment)
                    .setSumInsured(insuredSum);
            default -> null;
        };
    }

    private static void addLifeInsuranceInfo(InsurancePolicy insurancePolicy) throws IOException {
        String PolicyInfoPath = "/Users/andriymarych/Desktop/Прикладне Програмування/Code/InsuranceApp/src/main/Json/PolicyInfo.json";
        LifeInsuranceInfo lifeInsuranceInfo = (LifeInsuranceInfo) insurancePolicy.getPolicyInfo();
        Path infoPath = Path.of(PolicyInfoPath);
        JSONArray root = new JSONArray(new String(Files.readAllBytes(infoPath)));
        JSONObject obj = addGeneralInfo(insurancePolicy.getPolicyInfo());
        obj.put("firstName", lifeInsuranceInfo.getFirstName());
        obj.put("lastName", lifeInsuranceInfo.getLastName());
        obj.put("birthDate", lifeInsuranceInfo.getBirthDate());
        obj.put("address", lifeInsuranceInfo.getAddress());
        root.put(obj);
        Files.write(infoPath, root.toString().getBytes());

    }

    private static LifeInsuranceInfo getLifeInsuranceInfo(JSONObject insurancePolicyObject) {
        LifeInsuranceInfo lifeInsuranceInfo = (LifeInsuranceInfo) getGeneralInfo(insurancePolicyObject);
        String firstName = insurancePolicyObject.getString("firstName");
        String lastName = insurancePolicyObject.getString("lastName");
        String birthDate = insurancePolicyObject.getString("birthDate");
        String address = insurancePolicyObject.getString("address");
        return lifeInsuranceInfo
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthDate(birthDate)
                .setAddress(address);

    }

    private static void addProfessionalActivityInfo(InsurancePolicy insurancePolicy) throws IOException {
        String PolicyInfoPath = "/Users/andriymarych/Desktop/Прикладне Програмування/Code/InsuranceApp/src/main/Json/PolicyInfo.json";
        ProfessionalActivity professionalActivity = (ProfessionalActivity) insurancePolicy.getPolicyInfo();
        Path infoPath = Path.of(PolicyInfoPath);
        JSONArray root = new JSONArray(new String(Files.readAllBytes(infoPath)));
        JSONObject obj = addGeneralInfo(insurancePolicy.getPolicyInfo());
        obj.put("firstName", professionalActivity.getFirstName());
        obj.put("lastName", professionalActivity.getLastName());
        obj.put("professionalActivity", professionalActivity.getProfessionalActivity());
        obj.put("companyName", professionalActivity.getCompanyName());
        obj.put("position", professionalActivity.getPosition());
        root.put(obj);
        Files.write(infoPath, root.toString().getBytes());

    }

    private static ProfessionalActivity getProfessionalActivityInfo(JSONObject insurancePolicyObject) {
        ProfessionalActivity professionalActivityInfo = (ProfessionalActivity) getGeneralInfo(insurancePolicyObject);
        String firstName = insurancePolicyObject.getString("firstName");
        String lastName = insurancePolicyObject.getString("lastName");
        String professionalActivity = insurancePolicyObject.getString("professionalActivity");
        String companyName = insurancePolicyObject.getString("companyName");
        String position = insurancePolicyObject.getString("position");
        return professionalActivityInfo
                .setFirstName(firstName)
                .setLastName(lastName)
                .setProfessionalActivity(professionalActivity)
                .setCompanyName(companyName)
                .setPosition(position);
    }

    private static void addTransportInsuranceInfo(InsurancePolicy insurancePolicy) throws IOException {
        String PolicyInfoPath = "/Users/andriymarych/Desktop/Прикладне Програмування/Code/InsuranceApp/src/main/Json/PolicyInfo.json";
        TransportInsuranceInfo transportInsuranceInfo = (TransportInsuranceInfo) insurancePolicy.getPolicyInfo();
        Path infoPath = Path.of(PolicyInfoPath);
        JSONArray root = new JSONArray(new String(Files.readAllBytes(infoPath)));
        JSONObject obj = addGeneralInfo(insurancePolicy.getPolicyInfo());
        obj.put("firstName", transportInsuranceInfo.getOwnerFirstName());
        obj.put("lastName", transportInsuranceInfo.getOwnerLastName());
        obj.put("carBrand", transportInsuranceInfo.getCarBrand());
        obj.put("carModel", transportInsuranceInfo.getCarModel());
        obj.put("licensePlateNumber", transportInsuranceInfo.getLicensePlateNumber());
        root.put(obj);
        Files.write(infoPath, root.toString().getBytes());
    }
    private static TransportInsuranceInfo getTransportInsuranceInfo(JSONObject insurancePolicyObject) {
        TransportInsuranceInfo transportInsuranceInfo = (TransportInsuranceInfo) getGeneralInfo(insurancePolicyObject);
        String firstName = insurancePolicyObject.getString("firstName");
        String lastName = insurancePolicyObject.getString("lastName");
        String carBrand = insurancePolicyObject.getString("carBrand");
        String carModel = insurancePolicyObject.getString("carModel");
        String licensePlateNumber = insurancePolicyObject.getString("licensePlateNumber");
        return transportInsuranceInfo.setOwnerFirstName(firstName)
                .setOwnerLastName(lastName)
                .setCarBrand(carBrand)
                .setCarModel(carModel)
                .setLicensePlateNumber(licensePlateNumber);
    }
}



