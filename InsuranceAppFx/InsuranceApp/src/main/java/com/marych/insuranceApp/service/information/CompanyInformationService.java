package com.marych.insuranceApp.service.information;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.user.InsuranceSpecialist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyInformationService {

    public static ArrayList<String> getInsuranceSpecialists(String companyName) {
        ArrayList<String> insuranceSpecialists = new ArrayList<>();
        String query = "SELECT CONCAT(first_name,' ',last_name) as fullName, user_id, insurance_company_id  " +
                "FROM insurance_specialist " +
                "INNER JOIN insurance_company " +
                "ON insurance_specialist.insurance_company_id = insurance_company.company_id " +
                "WHERE insurance_company.name  = '" + companyName + "'";
        ResultSet resultSet = DatabaseHandler.getInstance().execQuery(query);
        try {
            while (resultSet.next()) {
                insuranceSpecialists.add(resultSet.getString("user_id") + " " + resultSet.getString("fullName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insuranceSpecialists;
    }
    public static InsuranceSpecialist getInsuranceSpecialist(int userId) {
        String query = "SELECT * " +
                "FROM insurance_specialist " +
                "WHERE user_id =  " + userId;
        ResultSet resultSet = DatabaseHandler.getInstance().execQuery(query);
        try {
            if (resultSet.next()) {
                return new InsuranceSpecialist(userId,resultSet.getString("first_name"),
                        resultSet.getString("last_name"))
                        .setCompanyId(resultSet.getString("insurance_company_id"))
                        .setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return null;
    }
}
