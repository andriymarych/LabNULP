package com.marych.insuranceApp.userInterface.insuranceMenu.policyCreation.insurancePolicies;


import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.service.WindowLoader;
import com.marych.insuranceApp.service.creation.CreatePolicyControllerService;
import com.marych.insuranceApp.service.information.CompanyInformationService;
import com.marych.insuranceApp.service.information.AppData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreateLiabilityPolicyController extends CreatePolicyControllerService implements Initializable {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private CheckBox checkBox;
    @FXML
    private ChoiceBox<String> insuranceSpecialists;

    @FXML
    private TextField companyNameField;
    @FXML
    private TextField professionalActivityField;
    @FXML
    private TextField positionField;
    @FXML
    private TextField insuranceSumField;
    @FXML
    private TextField riskPercentageField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        insuranceSpecialists.getItems().addAll(CompanyInformationService.getInsuranceSpecialists(AppData.getInstance().get("insuranceCompany")));
    }
    @FXML
    private void createPolicy(ActionEvent event) {
        createInsurancePolicy();
        addPolicyInfo();
        WindowLoader.load(event,Objects.requireNonNull(getClass().getResource("../SuccessPolicyCreationScene.fxml")));
    }

    private void addPolicyInfo(){
        String query  =  "INSERT INTO \"liability_info\" " + " VALUES (" +
                getPolicyId() + ", '" +
                firstNameField.getText() + "', '" +
                lastNameField.getText() + "', '" +
                companyNameField.getText() + "', '" +
                professionalActivityField.getText() + "', '" +
                positionField.getText() + "') ";
        DatabaseHandler.getInstance().execUpdate(query);
    }
}
