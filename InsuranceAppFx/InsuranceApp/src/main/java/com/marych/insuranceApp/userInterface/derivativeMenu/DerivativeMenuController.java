package com.marych.insuranceApp.userInterface.derivativeMenu;

import com.marych.insuranceApp.service.information.AppData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DerivativeMenuController {

    @FXML
    private void returnButton(ActionEvent event) {
        loadWindow(event,"../mainMenu/MainScene.fxml");
    }
    @FXML
    private void createDerivativeButton(ActionEvent event) {
        AppData.getInstance().put("nextWindow","Derivative");
        loadWindow(event,"/com/marych/insuranceApp/userInterface/insuranceMenu/policyCreation/SelectCompanyScene.fxml");
    }
    @FXML
    private void viewDerivativeButton(ActionEvent event) {
        loadWindow(event,"../derivativeMenu/ViewDerivativeMenuScene.fxml");
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
