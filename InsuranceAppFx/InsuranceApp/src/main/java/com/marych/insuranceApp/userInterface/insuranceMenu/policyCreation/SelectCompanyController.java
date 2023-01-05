package com.marych.insuranceApp.userInterface.insuranceMenu.policyCreation;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.service.information.AppData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class SelectCompanyController implements Initializable {

    @FXML
    private ChoiceBox<String> insuranceCompanies;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        insuranceCompanies.getItems().addAll(getInsuranceCompanies());
    }

    @FXML
    private void selectButton(ActionEvent event) {
        String insuranceCompany = insuranceCompanies.getSelectionModel().getSelectedItem();
        AppData appData = AppData.getInstance().clear("insuranceCompany");
        appData.put("insuranceCompany",insuranceCompany);
        selectWindow(event);
    }
    @FXML
    private void returnButton(ActionEvent event) {
        String nextWindow  = AppData.getInstance().get("nextWindow");
        if(nextWindow.equals("Policy")) {
            loadWindow(event,"../policyCreation/SelectPolicyScene.fxml");
        } else if(nextWindow.equals("Derivative")){
            loadWindow(event, "/com/marych/insuranceApp/userInterface/derivativeMenu/DerivativeMenuScene.fxml");
        }
    }
    private ArrayList<String> getInsuranceCompanies(){
        ArrayList<String> insuranceCompanies = new ArrayList<>();
        String query = "SELECT name FROM \"insurance_company\"";
        ResultSet resultSet= DatabaseHandler.getInstance().execQuery(query);
        try {
            while (resultSet.next()){
                insuranceCompanies.add(resultSet.getString("name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return insuranceCompanies;
    }
    private void loadWindow(ActionEvent event,String name){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(name)));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void selectWindow(ActionEvent event){
        String nextWindow  = AppData.getInstance().get("nextWindow");
        if(nextWindow.equals("Policy")) {
            String policyType = AppData.getInstance().get("policyType");
            switch (policyType) {
                case "Особисте":
                    loadWindow(event, "../policyCreation/insurancePolicies/CreatePersonalPolicyScene.fxml");
                case "Майно":
                    loadWindow(event, "../policyCreation/insurancePolicies/CreatePropertyPolicyScene.fxml");
                case "Відповідальність":
                    loadWindow(event, "../policyCreation/insurancePolicies/CreateLiabilityPolicyScene.fxml");
            }
        } else if(nextWindow.equals("Derivative")){
            loadWindow(event, "/com/marych/insuranceApp/userInterface/derivativeMenu/CreateDerivativeScene.fxml");
        }
    }
}
