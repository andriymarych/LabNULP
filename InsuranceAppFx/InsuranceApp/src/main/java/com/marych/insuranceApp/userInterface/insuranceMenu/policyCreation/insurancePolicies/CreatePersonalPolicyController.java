package com.marych.insuranceApp.userInterface.insuranceMenu.policyCreation.insurancePolicies;


import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.service.creation.CreatePolicyControllerService;
import com.marych.insuranceApp.service.WindowLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.util.*;

public class CreatePersonalPolicyController extends CreatePolicyControllerService implements Initializable {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField addressField;


    @FXML
    private void createPolicy(ActionEvent event) {
        createInsurancePolicy();
        addPolicyInfo();
        WindowLoader.load(event,Objects.requireNonNull(getClass().getResource("../SuccessPolicyCreationScene.fxml")));
    }
    private void addPolicyInfo(){
        String query  =  "INSERT INTO \"personal_info\" " + " VALUES (" +
                getPolicyId() + ", '" +
                firstNameField.getText() + "', '" +
                lastNameField.getText() + "', '" +
                addressField.getText() + "', '" +
                datePicker.getValue().toString() + ") ";
        DatabaseHandler.getInstance().execUpdate(query);
    }
}
