package com.marych.insuranceApp.service.diia;

import com.marych.insuranceApp.dao.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiiaCopy {

    private int diiaId;
    private int diiaSign;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String ITN;

    public DiiaCopy(int diiaId){
        ResultSet resultSet = DatabaseHandler.getInstance().
                execQuery("SELECT * FROM \"diia\" " + "WHERE id = '" + diiaId + "'");
        try {
            resultSet.next();
            diiaSign = resultSet.getInt("diia_sign");
            firstName = resultSet.getString("first_name");
            lastName = resultSet.getString("last_name");
            birthDate = resultSet.getString("birth_date");
            ITN = resultSet.getString("ITN");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getITN() {
        return ITN;
    }

    public int getDiiaId() {
        return diiaId;
    }

    public int getDiiaSign() {
        return diiaSign;
    }
}
