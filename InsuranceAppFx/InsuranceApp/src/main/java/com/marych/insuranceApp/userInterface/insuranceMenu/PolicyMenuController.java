package com.marych.insuranceApp.userInterface.insuranceMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PolicyMenuController {

    @FXML
    private void returnButton(ActionEvent event) {
        loadWindow(event,"../mainMenu/MainScene.fxml");
    }
    @FXML
    private void createPolicyButton(ActionEvent event) {
        loadWindow(event,"../insuranceMenu/policyCreation/SelectPolicyScene.fxml");
    }
    @FXML
    private void viewPolicyButton(ActionEvent event) {
        loadWindow(event,"../insuranceMenu/ViewPolicyScene.fxml");
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
