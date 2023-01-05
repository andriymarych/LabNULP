package com.marych.insuranceApp.dao;


import com.marych.insuranceApp.document.derivative.Derivative;
import com.marych.insuranceApp.document.policy.ObservableInsurancePolicy;
import com.marych.insuranceApp.document.policy.PolicyNode;
import com.marych.insuranceApp.document.policy.policyType.liability.ProfessionalActivityInsurance;
import com.marych.insuranceApp.document.policy.policyType.personal.PersonalInsurance;
import com.marych.insuranceApp.document.policy.policyType.property.PropertyInsurance;
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

    public ObservableList<ObservableInsurancePolicy> getInsurancePolicyData(int userId) {
        ObservableList<ObservableInsurancePolicy> observableInsurancePolicyList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM \"insurance_policy\" WHERE holder_id = " + userId + "ORDER BY policy_id";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                ObservableInsurancePolicy observableInsurancePolicy = new ObservableInsurancePolicy(
                        resultSet.getInt("policy_id"),
                        resultSet.getBoolean("compulsory"),
                        resultSet.getInt("holder_id"),
                        resultSet.getInt("insurer_id"),
                        resultSet.getInt("company_id")
                );
                observableInsurancePolicy.setInsuredSum(resultSet.getDouble("insured_sum"))
                        .setInsuredPayment(resultSet.getDouble("insured_payment"))
                        .setSignDate(resultSet.getDate("sign_date").toString())
                        .setRiskPercentage(resultSet.getShort("risk_percentage"))
                        .setInfoType(resultSet.getShort("info_type"));
                observableInsurancePolicyList.add(observableInsurancePolicy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableInsurancePolicyList;
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

    public ObservableList<ObservableInsurancePolicy> getInsurancePolicyDataByPrice(String derivativeId, String startSum, String endSum) {
        ObservableList<ObservableInsurancePolicy> observableInsurancePolicyList = FXCollections.observableArrayList();
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
                ObservableInsurancePolicy observableInsurancePolicy = new ObservableInsurancePolicy(
                        resultSet.getInt("policy_id"),
                        resultSet.getBoolean("compulsory"),
                        resultSet.getInt("holder_id"),
                        resultSet.getInt("insurer_id"),
                        resultSet.getInt("company_id")
                );
                observableInsurancePolicy.setInsuredSum(resultSet.getDouble("insured_sum"))
                        .setInsuredPayment(resultSet.getDouble("insured_payment"))
                        .setSignDate(resultSet.getDate("sign_date").toString())
                        .setRiskPercentage(resultSet.getShort("risk_percentage"))
                        .setInfoType(resultSet.getShort("info_type"));
                observableInsurancePolicyList.add(observableInsurancePolicy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableInsurancePolicyList;
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

    public ObservableList<PersonalInsurance> getPersonalPolicyData(int userId) {
        ObservableList<PersonalInsurance> personalInsuranceList = FXCollections.observableArrayList();
        String SQL = "SELECT * " +
                "FROM insurance_policy " +
                "INNER JOIN personal_info " +
                "ON insurance_policy.policy_id = personal_info.policy_id " +
                "WHERE holder_id = " + userId +
                " AND info_type = 1 ";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                PersonalInsurance personalInsurance = new PersonalInsurance(
                        resultSet.getInt(1),
                        resultSet.getString("insured_first_name"),
                        resultSet.getString("insured_last_name"))
                        .setAddress(resultSet.getString("address"))
                        .setBirthDate(resultSet.getString("birth_date"));
                personalInsuranceList.add(personalInsurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personalInsuranceList;
    }
    public ObservableList<ProfessionalActivityInsurance> getLiabilityPolicyData(int userId){
        ObservableList<ProfessionalActivityInsurance> professionalActivityInsuranceList = FXCollections.observableArrayList();
        String SQL = "SELECT * " +
                "FROM insurance_policy " +
                "INNER JOIN liability_info " +
                "ON insurance_policy.policy_id = liability_info.policy_id " +
                "WHERE holder_id = " + userId +
                "AND info_type = 2 ";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                ProfessionalActivityInsurance professionalActivityInsurance = new ProfessionalActivityInsurance(
                        resultSet.getInt(1),
                        resultSet.getString("insured_first_name"),
                        resultSet.getString("insured_last_name"),
                        resultSet.getString("insured_company_name"))
                        .setProfessionalActivity(resultSet.getString("professional_activity"))
                        .setPosition(resultSet.getString("position"));
                professionalActivityInsuranceList.add(professionalActivityInsurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professionalActivityInsuranceList;
    }
    public ObservableList<ProfessionalActivityInsurance> getLiabilityPolicyList(int userId, String policyNoList){
        ObservableList<ProfessionalActivityInsurance> professionalActivityInsuranceList = FXCollections.observableArrayList();
        StringBuilder policyNoBuilder = new StringBuilder();
        policyNoBuilder.append("(");
        for (String policyNo : policyNoList.split(" ")) {
            policyNoBuilder.append(policyNo)
                    .append(", ");
        }
        policyNoBuilder.deleteCharAt(policyNoBuilder.length() - 1);
        policyNoBuilder.deleteCharAt(policyNoBuilder.length() - 1);
        policyNoBuilder.append(")");
        String SQL = "SELECT * " +
                "FROM insurance_policy " +
                "INNER JOIN liability_info " +
                "ON insurance_policy.policy_id = liability_info.policy_id " +
                "WHERE holder_id = " + userId +
                " AND info_type = 2 " +
                "AND liability_info.policy_id IN " + policyNoBuilder;
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                ProfessionalActivityInsurance professionalActivityInsurance = new ProfessionalActivityInsurance(
                        resultSet.getInt(1),
                        resultSet.getString("insured_first_name"),
                        resultSet.getString("insured_last_name"),
                        resultSet.getString("insured_company_name"))
                        .setProfessionalActivity(resultSet.getString("professional_activity"))
                        .setPosition(resultSet.getString("position"));
                professionalActivityInsuranceList.add(professionalActivityInsurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professionalActivityInsuranceList;
    }
    public ObservableList<PropertyInsurance> getPropertyPolicyData(int userId){
        ObservableList<PropertyInsurance> propertyInsuranceList = FXCollections.observableArrayList();
        String SQL = "SELECT  * " +
                "FROM insurance_policy " +
                "INNER JOIN car_property_info " +
                "ON insurance_policy.policy_id = car_property_info.policy_id " +
                "WHERE holder_id = " + userId +
                "AND info_type = 3 ";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                PropertyInsurance propertyInsurance = new PropertyInsurance(
                        resultSet.getInt(1),
                        resultSet.getString("insured_first_name"),
                        resultSet.getString("insured_last_name"))
                        .setCarBrand(resultSet.getString("car_brand"))
                        .setCarModel(resultSet.getString("car_model"))
                        .setLicensePlate(resultSet.getString("license_plate"));
                propertyInsuranceList.add(propertyInsurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propertyInsuranceList;
    }


}

