package com.marych.insuranceApp.userInterface.insuranceMenu.policyCreation.insurancePolicies;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.session.UserSession;
import com.marych.insuranceApp.utils.AppData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class CreatePersonalPolicyController implements Initializable {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField addressField;
    @FXML
    private TextField insuranceSumField;
    @FXML
    private TextField riskPercentageField;
    @FXML
    private CheckBox checkBox;
    @FXML
    private ChoiceBox<String> insuranceSpecialists;
    private int companyId;
    private int insurerId;
    private int policyId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        insuranceSpecialists.getItems().addAll(getInsuranceSpecialists());
    }

    @FXML
    private void returnButton(ActionEvent event) {
        loadWindow(event,"../SelectCompanyScene.fxml");
    }

    @FXML
    private void createPolicy(ActionEvent event) {
        addInsurancePolicy();
        addPolicyInfo();
        loadWindow(event,"../SuccessPolicyCreationScene.fxml");
    }

    private void loadWindow(ActionEvent event, String name) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(name)));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> getInsuranceSpecialists() {
        ArrayList<String> insuranceSpecialists = new ArrayList<>();
        String companyName = AppData.getInstance().get("insuranceCompany");
        String query = "SELECT CONCAT(first_name,' ',last_name) as fullName, user_id, company_id  " +
                "FROM insurance_specialist " +
                "INNER JOIN insurance_company " +
                "ON insurance_specialist.insurance_company_id = insurance_company.company_id " +
                "WHERE insurance_company.name  = '" + companyName + "'";
        ResultSet resultSet = DatabaseHandler.getInstance().execQuery(query);
        try {
            while (resultSet.next()) {
                insuranceSpecialists.add(resultSet.getString("fullName"));
                companyId = resultSet.getInt("company_id");
                insurerId = resultSet.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insuranceSpecialists;
    }

    private void addInsurancePolicy() {
        policyId = DatabaseHandler.getInstance().getNextPolicyId();
        boolean compulsory = checkBox.isSelected();
        int holderId = UserSession.getInstance().getUserId();
        int insuredSum = Integer.parseInt(insuranceSumField.getText());
        short riskPercentage = Short.parseShort(riskPercentageField.getText());
        double insuredPayment = insuredSum * riskPercentage;
        String date = LocalDate.now().toString();
        short infoType = 1;
        InsurancePolicyDaoSender sender = new InsurancePolicyDaoSender(policyId,
                holderId,insurerId,companyId);
        sender.setCompulsory(compulsory)
                .setInsuredSum(insuredSum)
                .setInsuredPayment(insuredPayment)
                .setDate(date)
                .setRiskPersentage(riskPercentage)
                .setInfoType(infoType);
        sender.send();
    }
    private void addPolicyInfo(){
        String query  =  "INSERT INTO \"personal_info\" " + " VALUES (" +
                policyId + ", '" +
                firstNameField.getText() + "', '" +
                lastNameField.getText() + "', '" +
                addressField.getText() + "', '" +
                datePicker.getValue().toString() + ") ";
        DatabaseHandler.getInstance().execUpdate(query);
    }
}
