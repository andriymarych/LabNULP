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

public class CreatePropertyPolicyController extends CreatePolicyControllerService implements Initializable {
    @FXML
    private TextField ownerFirstName;
    @FXML
    private TextField ownerLastName;
    @FXML
    private CheckBox checkBox;
    @FXML
    private ChoiceBox<String> insuranceSpecialists;
    @FXML
    private TextField carBrandField;
    @FXML
    private TextField carModelField;
    @FXML
    private TextField insuranceSumField;
    @FXML
    private TextField licensePlateField;
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
        WindowLoader.load(event, Objects.requireNonNull(getClass().getResource("../SuccessPolicyCreationScene.fxml")));
    }

    private void addPolicyInfo() {
        String query = "INSERT INTO \"car_property_info\" " + " VALUES (" +
                getPolicyId() + ", '" +
                ownerFirstName.getText() + "', '" +
                ownerLastName.getText() + "', '" +
                carBrandField.getText() + "', '" +
                carModelField.getText() + "', '" +
                licensePlateField.getText() + "') ";
        DatabaseHandler.getInstance().execUpdate(query);
    }

}
