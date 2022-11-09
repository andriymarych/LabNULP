package com.marych.insuranceApp.scanners.jsonScanner;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.marych.insuranceApp.diiaGov.DiiaGov;

import com.marych.insuranceApp.user.Customer;
import com.marych.insuranceApp.user.InsuranceSpecialist;
import com.marych.insuranceApp.insurance.InsuranceCompany;
import com.marych.insuranceApp.insurance.InsuranceCompanyList;
import com.marych.insuranceApp.insurance.derivative.Derivative;
import com.marych.insuranceApp.insurance.policy.InsurancePolicy;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.marych.insuranceApp.Main.*;

public class JsonScanner {

    public JsonScanner() {
    }

    public void execute() throws IOException {
    }

    public static void getData() {
        getCustomerDate();
        getDiiaData();
        HashMap<Integer, ArrayList<InsuranceSpecialist>> insuranceSpecialistList = getSpecialistData();
        getCompaniesData(insuranceSpecialistList);
        readInsurancePolicy();
        readDerivative();
    }

    private static void getCustomerDate() {
        String customersPath = "/Users/andriymarych/Desktop/Applied Programming/Insurance app/v1.1/InsuranceApp/src/main/Json/Customers.json";
        try {
            JSONArray jsonArray = new JSONArray(new String(Files.readAllBytes(Paths.get(customersPath))));
            ObjectMapper mapper = new ObjectMapper();
            byte[] jsonData;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                jsonData = object.toString().getBytes();
                Customer customer = mapper.readValue(jsonData, Customer.class);
                userInfo.addUserLogin(customer.getLogin(), customer);
                userInfo.addUserId(customer.getUserId(), customer);
                userInfo.addUserEmail(customer.getEmail(), customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getDiiaData() {
        String customersPath = "/Users/andriymarych/Desktop/Applied Programming/Insurance app/v1.1/InsuranceApp/src/main/Json/Diia.json";
        try {
            JSONArray jsonArray = new JSONArray(new String(Files.readAllBytes(Paths.get(customersPath))));
            ObjectMapper mapper = new ObjectMapper();
            byte[] jsonData;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                jsonData = object.toString().getBytes();
                DiiaGov idDocument = mapper.readValue(jsonData, DiiaGov.class);
                diiaGovDocuments.addIdDocument(idDocument);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HashMap<Integer, ArrayList<InsuranceSpecialist>> getSpecialistData() {
        String companiesPath = "/Users/andriymarych/Desktop/Applied Programming/Insurance app/v1.1/InsuranceApp/src/main/Json/Specialists.json";
        HashMap<Integer, ArrayList<InsuranceSpecialist>> insuranceSpecialistList = new HashMap<>();
        try {
            JSONArray jsonArray = new JSONArray(new String(Files.readAllBytes(Paths.get(companiesPath))));
            ObjectMapper mapper = new ObjectMapper();
            byte[] jsonData;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                jsonData = object.toString().getBytes();
                InsuranceSpecialist insuranceSpecialist = mapper.readValue(jsonData, InsuranceSpecialist.class);
                int companyNo = insuranceSpecialist.getInsuranceCompanyId();
                insuranceSpecialistList.computeIfAbsent(companyNo, k -> new ArrayList<>()).add(insuranceSpecialist);
                userInfo.addUserId(insuranceSpecialist.getUserId(), insuranceSpecialist);
                userInfo.addUserLogin(insuranceSpecialist.getLogin(), insuranceSpecialist);
                userInfo.addUserEmail(insuranceSpecialist.getEmail(), insuranceSpecialist);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return insuranceSpecialistList;
    }

    private static void getCompaniesData(HashMap<Integer, ArrayList<InsuranceSpecialist>> insuranceSpecialistList) {
        String customersPath = "/Users/andriymarych/Desktop/Applied Programming/Insurance app/v1.1/InsuranceApp/src/main/Json/Companies.json";
        try {
            JSONArray jsonArray = new JSONArray(new String(Files.readAllBytes(Paths.get(customersPath))));
            ObjectMapper mapper = new ObjectMapper();
            byte[] jsonData;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                jsonData = object.toString().getBytes();
                InsuranceCompany insuranceCompany = mapper.readValue(jsonData, InsuranceCompany.class);
                int companyId = insuranceCompany.getCompanyId();
                insuranceCompany.addSpecialist(insuranceSpecialistList.get(companyId));
                insuranceCompanyList.add(insuranceCompany);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  boolean addCustomer(Customer customer) throws IOException {
        String customersPath = "/Users/andriymarych/Desktop/Applied Programming/Insurance app/v1.1/InsuranceApp/src/main/Json/Customers.json";
        JSONArray root = new JSONArray(new String(Files.readAllBytes(Paths.get(customersPath))));
        JSONObject obj = new JSONObject();
        Random random = new Random();
        int userId;
        do {
            userId = 1000 + random.nextInt(1000);
        } while (userInfo.getUserIdList().containsKey(userId));
        obj.put("userId", customer.getUserId());
        obj.put("userRole", customer.getUserRole());
        obj.put("login", customer.getLogin());
        obj.put("password", customer.getPassword());
        obj.put("firstName", customer.getFirstName());
        obj.put("lastName", customer.getLastName());
        obj.put("birthDate", customer.getBirthDate().toString());
        obj.put("email", customer.getEmail());
        obj.put("ITN", customer.getITN());
        root.put(obj);
        Files.write(Paths.get(customersPath), root.toString().getBytes());
        return true;
    }

    private static void readInsurancePolicy() {
        String customersPath = "/Users/andriymarych/Desktop/Applied Programming/Insurance app/v1.1/InsuranceApp/src/main/Json/InsurancePolicy.json";
        try {
            JSONArray jsonArray = new JSONArray(new String(Files.readAllBytes(Paths.get(customersPath))));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject insurancePolicyObject = jsonArray.getJSONObject(i);
                int policyNo = insurancePolicyObject.getInt("policyNo");
                boolean compulsory = insurancePolicyObject.getBoolean("compulsory");
                int policyHolderId = insurancePolicyObject.getInt("policyHolder");
                int insurerId = insurancePolicyObject.getInt("insurer");
                int insuranceCompanyid = insurancePolicyObject.getInt("insuranceCompany");
                String date = insurancePolicyObject.getString("date");
                Customer customer = (Customer) userInfo.getUserIdList().get(policyHolderId);
                InsuranceSpecialist insuranceSpecialist = (InsuranceSpecialist) userInfo.getUserIdList().get(insurerId);
                InsuranceCompany insuranceCompany = insuranceCompanyList.asList().get(insuranceCompanyid);
                InsurancePolicy insurancePolicy = new InsurancePolicy(policyNo, customer, insuranceSpecialist)
                        .setDate(date).setCompulsory(compulsory).setInsuranceCompany(insuranceCompany);
                insurancePolicy.setPolicyInfo(JsonInfoScanner.getInfo(i));
                customer.addInsurancePolicy(insurancePolicy);
                insuranceSpecialist.addInsurancePolicy(insurancePolicy);
                insuranceCompany.addInsurancePolicy(customer, insurancePolicy);
                if (i == jsonArray.length() - 1) {
                    InsuranceCompanyList.setPolicyNumber(policyNo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  boolean addInsurancePolicy(InsurancePolicy insurancePolicy) throws IOException {
        String PolicyPath = "/Users/andriymarych/Desktop/Applied Programming/Insurance app/v1.1/InsuranceApp/src/main/Json/InsurancePolicy.json";
        Path insurancePolicyPath = Path.of(PolicyPath);
        JSONArray root = new JSONArray(new String(Files.readAllBytes(insurancePolicyPath)));
        JSONObject obj = new JSONObject();
        obj.put("policyNo", insurancePolicy.getPolicyNo());
        obj.put("compulsory", insurancePolicy.getCompulsory());
        obj.put("policyHolder", insurancePolicy.getPolicyHolder().getUserId());
        obj.put("insurer", insurancePolicy.getInsurer().getUserId());
        obj.put("insuranceCompany", insurancePolicy.getInsuranceCompany().getCompanyId());
        obj.put("date", insurancePolicy.getDate());
        root.put(obj);
        Files.write(insurancePolicyPath, root.toString().getBytes());
        return true;
    }

    public boolean addDerivative(Derivative derivative) throws IOException {
        var keySet = derivative.getPolicyList().keySet();
        ArrayList<Integer> policyNoList = new ArrayList<>();
        for (var key : keySet) {
            policyNoList.add(derivative.getPolicyList().get(key).getPolicyNo());
        }
        String derivativePathStr = "/Users/andriymarych/Desktop/Applied Programming/Insurance app/v1.1/InsuranceApp/src/main/Json/Derivative.json";
        Path derivativePath = Path.of(derivativePathStr);
        JSONArray root = new JSONArray(new String(Files.readAllBytes(derivativePath)));
        JSONObject obj = new JSONObject();
        obj.put("derivativeNo", derivative.getDerivativeNo());
        obj.put("derivativeHolder", derivative.getDerivativeHolder().getUserId());
        obj.put("insurer", derivative.getInsurer().getUserId());
        obj.put("policyList", policyNoList);
        obj.put("insuranceCompanyId", derivative.getInsuranceCompany().getCompanyId());
        obj.put("price", derivative.getPrice());
        obj.put("date", derivative.getDate());
        root.put(obj);
        Files.write(derivativePath, root.toString().getBytes());
        return true;
    }

    private static void readDerivative() {
        String derivativePath = "/Users/andriymarych/Desktop/Applied Programming/Insurance app/v1.1/InsuranceApp/src/main/Json/Derivative.json";
        try {
            JSONArray jsonArray = new JSONArray(new String(Files.readAllBytes(Paths.get(derivativePath))));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject insurancePolicyObject = jsonArray.getJSONObject(i);
                int derivativeNo = insurancePolicyObject.getInt("derivativeNo");
                String date = insurancePolicyObject.getString("date");
                double price = insurancePolicyObject.getDouble("price");
                Customer derivativeHolder = (Customer) userInfo.getUserIdList().get(insurancePolicyObject.getInt("derivativeHolder"));
                InsuranceSpecialist insurer = (InsuranceSpecialist) userInfo.getUserIdList().get(insurancePolicyObject.getInt("insurer"));
                InsuranceCompany insuranceCompany = insuranceCompanyList.asList().get(insurancePolicyObject.getInt("insuranceCompanyId"));
                JSONArray policyList = insurancePolicyObject.getJSONArray("policyList");
                ArrayList<InsurancePolicy> insurancePolicies = new ArrayList<>();
                for (int j = 0; j < policyList.length(); j++) {
                    int policyNo = policyList.getInt(j);
                    insurancePolicies.add(derivativeHolder.getInsurancePolicyList().get(policyNo));
                }
                Derivative derivative = new Derivative(derivativeNo, derivativeHolder, insurer)
                        .setInsuranceCompany(insuranceCompany)
                        .setPolicyList(insurancePolicies)
                        .setPrice(price)
                        .setDate(date);
                derivativeHolder.addDerivative(derivative);
                insurer.addDerivative(derivative);
                insuranceCompany.addDerivative(derivativeHolder, derivative);
                insuranceCompanyList.addDerivativeNumber();
                if (i == jsonArray.length() - 1) {
                    InsuranceCompanyList.setDerivativeNumber(derivativeNo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean  insuranceDeletion(InsurancePolicy insurancePolicy) {
        int policyNo = insurancePolicy.getPolicyNo();
        String policyPathStr = "/Users/andriymarych/Desktop/Applied Programming/Insurance app/v1.1/InsuranceApp/src/main/Json/InsurancePolicy.json";
        String infoPathStr = "/Users/andriymarych/Desktop/Applied Programming/Insurance app/v1.1/InsuranceApp/src/main/Json/PolicyInfo.json";
        String derivativePathStr = "/Users/andriymarych/Desktop/Applied Programming/Insurance app/v1.1/InsuranceApp/src/main/Json/Derivative.json";
        if (insuranceDeletionDerivative(derivativePathStr, policyNo)) {
            policyDeletionSystem(policyNo,insurancePolicy);
            policyDeletionFile(policyPathStr, policyNo);
            policyDeletionFile(infoPathStr, policyNo);
        }
        return true;
    }
    public  boolean derivativeDeletion(Derivative derivative,int customerId) {
        int derivativeNo = derivative.getDerivativeNo();
        String derivativePath = "/Users/andriymarych/Desktop/Applied Programming/Insurance app/v1.1/InsuranceApp/src/main/Json/Derivative.json";
        derivativeDeletionSystem(derivativeNo,customerId);
        derivativeDeletionFile(derivativePath,derivativeNo);
        return true;
    }
    private static void policyDeletionFile(String path, int policyNo) {
        try {
            Path policyPath = Path.of(path);
            JSONArray jsonArray = new JSONArray(new String(Files.readAllBytes(policyPath)));
            JSONArray newJsonArray = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                if (object.getInt("policyNo") != policyNo) {
                    newJsonArray.put(object);
                }
            }
            Files.write(policyPath, newJsonArray.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void derivativeDeletionFile(String path, int derivativeNo) {
        try {
            Path derivativePath = Path.of(path);
            JSONArray jsonArray = new JSONArray(new String(Files.readAllBytes(derivativePath)));
            JSONArray newJsonArray = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                if (object.getInt("derivativeNo") != derivativeNo) {
                    newJsonArray.put(object);
                }
            }
            Files.write(derivativePath, newJsonArray.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void policyDeletionSystem(int policyNo, InsurancePolicy insurancePolicy) {
        Customer customer = insurancePolicy.getPolicyHolder();
        InsuranceSpecialist insuranceSpecialist = insurancePolicy.getInsurer();
        InsuranceCompany insuranceCompany = insurancePolicy.getInsuranceCompany();
        customer.deleteInsurancePolicy(policyNo);
        insuranceSpecialist.deleteInsurancePolicy(policyNo);
        insuranceCompany.getInsurancePolicyList(customer).remove(insurancePolicy);
        System.out.println("Страховий договір № " + policyNo + " успішно видалено.");
    }
    private static void derivativeDeletionSystem(int derivativeNo,int customerNo) {
        Customer customer = (Customer) userInfo.getUserIdList().get(customerNo);
        Derivative derivative = customer.getDerivativeList().get(derivativeNo);
        InsuranceSpecialist insuranceSpecialist = derivative.getInsurer();
        InsuranceCompany insuranceCompany = derivative.getInsuranceCompany();
        customer.deleteDerivative(derivativeNo);
        insuranceSpecialist.deleteDerivative(derivativeNo);
        insuranceCompany.getDerivativeList(customer).remove(derivative);
    }

    private static boolean insuranceDeletionDerivative(String derivativePath, int policyNo) {
        Scanner in = new Scanner(System.in);
        String option = null;
        boolean add;
        try {
            Path policyPath = Path.of(derivativePath);
            JSONArray jsonArray = new JSONArray(new String(Files.readAllBytes(policyPath)));
            JSONArray newJsonArray = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                add = true;
                JSONObject derivativeObject = jsonArray.getJSONObject(i);
                JSONArray policyList = derivativeObject.getJSONArray("policyList");
                for (int j = 0; j < policyList.length(); j++) {
                    if (policyNo == policyList.getInt(j)) {
                        System.out.println("Страховий договір № " + policyNo + " включений у дериватив № " + derivativeObject.getInt("derivativeNo"));
                        System.out.println("Після видалення страхового договору, даний дериватив також буде видалено.");
                        System.out.println("Бажаєте продовжити видалення страхового договору?( Введіть так/ні )");
                        option = in.next();
                        if (option.equals("так")) {
                            add = false;
                            int derivativeNo = derivativeObject.getInt("derivativeNo");
                            int customerId = derivativeObject.getInt("derivativeHolder");
                            derivativeDeletionSystem(derivativeNo,customerId);
                        }else{
                            return false;
                        }
                    }
                }
                if (add) {
                    newJsonArray.put(derivativeObject);
                }
            }
            Files.write(policyPath, newJsonArray.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert option != null;
        return true;
    }

}
