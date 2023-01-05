package com.marych.insuranceApp.userInterface.insuranceMenu.policyCreation;

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
import java.util.Objects;
import java.util.ResourceBundle;

public class SelectPolicyController implements Initializable {

    @FXML
    private ChoiceBox<String>  policyTypes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        policyTypes.getItems().addAll("Особисте","Майно","Відповідальність");
    }

    @FXML
    private void selectButton(ActionEvent event) {
        String policyType = policyTypes.getSelectionModel().getSelectedItem();
        AppData appData = AppData.getInstance().clear();
        appData.put("policyType",policyType);
        appData.put("nextWindow","Policy");
        loadWindow(event,"../policyCreation/SelectCompanyScene.fxml");
    }
    @FXML
    private void returnButton(ActionEvent event) {
        loadWindow(event,"../PolicyMenuScene.fxml");
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



}
