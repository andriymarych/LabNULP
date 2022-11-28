package com.marych.insuranceApp.dao;


import com.marych.insuranceApp.workClass.Derivative;
import com.marych.insuranceApp.workClass.InsurancePolicy;
import com.marych.insuranceApp.workClass.PolicyNode;
import com.marych.insuranceApp.workClass.policyType.LiabilityPolicy;
import com.marych.insuranceApp.workClass.policyType.PersonalPolicy;
import com.marych.insuranceApp.workClass.policyType.PropertyPolicy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseHandler {
    private static DatabaseHandler handler;
    private static Connection connection = null;
    private static Statement stmt = null;

    static {
        createConnection();
    }

    private static void createConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/InsuranceDB",
                    "postgres",
                    "9109096682qaz");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = connection.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }
        return result;
    }

    public boolean execUpdate(String qu) {
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(qu);
            return true;
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        }
    }

    public int getNextUserId() {
        try {
            String SQL = "SELECT MAX(id) FROM \"user\" ";
            ResultSet resultSet = execQuery(SQL);
            resultSet.next();
            return resultSet.getInt(1) + 1;
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return 0;
        }
    }

    public int getNextPolicyId() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT MAX(policy_id) FROM \"insurance_policy\" ";
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            return resultSet.getInt(1) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getNextDerivativeId() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT MAX(derivative_id) FROM \"derivative\" ";
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            return resultSet.getInt(1) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean checkLogin(String login) {
        String SQL = "SELECT * FROM \"user\" WHERE login = '" + login + "'";
        ResultSet resultSet = execQuery(SQL);
        try {
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean containsPolicy(int userId, int policyNo) {
        String SQL = "SELECT * FROM \"insurance_policy\" WHERE policy_id = '" + policyNo + "'" +
                "AND holder_id = " + userId;
        ResultSet resultSet = execQuery(SQL);
        try {
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ObservableList<InsurancePolicy> getInsurancePolicyData(int userId) {
        ObservableList<InsurancePolicy> insurancePolicyList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM \"insurance_policy\" WHERE holder_id = " + userId + "ORDER BY policy_id";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                InsurancePolicy insurancePolicy = new InsurancePolicy(
                        resultSet.getInt("policy_id"),
                        resultSet.getBoolean("compulsory"),
                        resultSet.getInt("holder_id"),
                        resultSet.getInt("insurer_id"),
                        resultSet.getInt("company_id")
                );
                insurancePolicy.setInsuredSum(resultSet.getDouble("insured_sum"))
                        .setInsuredPayment(resultSet.getDouble("insured_payment"))
                        .setSignDate(resultSet.getDate("sign_date").toString())
                        .setRiskPercentage(resultSet.getShort("risk_percentage"))
                        .setInfoType(resultSet.getShort("info_type"));
                insurancePolicyList.add(insurancePolicy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insurancePolicyList;
    }

    public ObservableList<Derivative> getDerivativeData(int userId) {
        ObservableList<Derivative> derivativeList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM \"derivative\" WHERE holder_id = " + userId + " ORDER BY derivative_id";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                Derivative derivative = new Derivative(
                        resultSet.getInt("derivative_id"),
                        resultSet.getInt("holder_id"),
                        resultSet.getInt("insurer_id"),
                        resultSet.getInt("company_id")
                );
                derivative.setPrice(resultSet.getDouble("price"))
                        .setSignDate(resultSet.getDate("sign_date").toString());
                derivativeList.add(derivative);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return derivativeList;
    }

    public ObservableList<InsurancePolicy> getInsurancePolicyDataByPrice(String derivativeId, String startSum, String endSum) {
        ObservableList<InsurancePolicy> insurancePolicyList = FXCollections.observableArrayList();
        String SQL = "SELECT  insurance_policy.policy_id, compulsory,holder_id,insurer_id," +
                "company_id,insured_sum,insured_payment,sign_date,risk_percentage,info_type " +
                "FROM insurance_policy " +
                "INNER JOIN derivative_policy_list " +
                "ON insurance_policy.policy_id = derivative_policy_list.policy_id " +
                "WHERE derivative_id = " + derivativeId +
                " AND  insured_sum BETWEEN " + startSum + " AND " + endSum +
                " ORDER BY insurance_policy.policy_id";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                InsurancePolicy insurancePolicy = new InsurancePolicy(
                        resultSet.getInt("policy_id"),
                        resultSet.getBoolean("compulsory"),
                        resultSet.getInt("holder_id"),
                        resultSet.getInt("insurer_id"),
                        resultSet.getInt("company_id")
                );
                insurancePolicy.setInsuredSum(resultSet.getDouble("insured_sum"))
                        .setInsuredPayment(resultSet.getDouble("insured_payment"))
                        .setSignDate(resultSet.getDate("sign_date").toString())
                        .setRiskPercentage(resultSet.getShort("risk_percentage"))
                        .setInfoType(resultSet.getShort("info_type"));
                insurancePolicyList.add(insurancePolicy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insurancePolicyList;
    }

    public ObservableList<PolicyNode> getDerivativePolicyList(String derivativeId) {
        ObservableList<PolicyNode> policyNodeList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM \"derivative_policy_list\" " +
                "WHERE derivative_id = " + derivativeId +
                " ORDER BY policy_id";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                PolicyNode policyNode = new PolicyNode(
                        resultSet.getInt("derivative_id"),
                        resultSet.getInt("policy_id")
                );
                policyNodeList.add(policyNode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return policyNodeList;
    }

    public ObservableList<PersonalPolicy> getPersonalPolicyData(int userId) {
        ObservableList<PersonalPolicy> personalPolicyList = FXCollections.observableArrayList();
        String SQL = "SELECT * " +
                "FROM insurance_policy " +
                "INNER JOIN personal_info " +
                "ON insurance_policy.policy_id = personal_info.policy_id " +
                "WHERE holder_id = " + userId +
                " AND info_type = 1 ";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                PersonalPolicy personalPolicy = new PersonalPolicy(
                        resultSet.getInt(1),
                        resultSet.getString("insured_first_name"),
                        resultSet.getString("insured_last_name"))
                        .setAddress(resultSet.getString("address"))
                        .setBirthDate(resultSet.getString("birth_date"));
                personalPolicyList.add(personalPolicy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personalPolicyList;
    }
    public ObservableList<LiabilityPolicy> getLiabilityPolicyData(int userId){
        ObservableList<LiabilityPolicy> liabilityPolicyList = FXCollections.observableArrayList();
        String SQL = "SELECT * " +
                "FROM insurance_policy " +
                "INNER JOIN liability_info " +
                "ON insurance_policy.policy_id = liability_info.policy_id " +
                "WHERE holder_id = " + userId +
                "AND info_type = 2 ";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                LiabilityPolicy liabilityPolicy = new LiabilityPolicy(
                        resultSet.getInt(1),
                        resultSet.getString("insured_first_name"),
                        resultSet.getString("insured_last_name"),
                        resultSet.getString("insured_company_name"))
                        .setProfessionalActivity(resultSet.getString("professional_activity"))
                        .setPosition(resultSet.getString("position"));
                liabilityPolicyList.add(liabilityPolicy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liabilityPolicyList;
    }
    public ObservableList<PropertyPolicy> getPropertyPolicyData(int userId){
        ObservableList<PropertyPolicy> propertyPolicyList = FXCollections.observableArrayList();
        String SQL = "SELECT  * " +
                "FROM insurance_policy " +
                "INNER JOIN car_property_info " +
                "ON insurance_policy.policy_id = car_property_info.policy_id " +
                "WHERE holder_id = " + userId +
                "AND info_type = 3 ";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                PropertyPolicy propertyPolicy = new PropertyPolicy(
                        resultSet.getInt(1),
                        resultSet.getString("insured_first_name"),
                        resultSet.getString("insured_last_name"))
                        .setCarBrand(resultSet.getString("car_brand"))
                        .setCarModel(resultSet.getString("car_model"))
                        .setLicensePlate(resultSet.getString("license_plate"));
                propertyPolicyList.add(propertyPolicy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propertyPolicyList;
    }
}

