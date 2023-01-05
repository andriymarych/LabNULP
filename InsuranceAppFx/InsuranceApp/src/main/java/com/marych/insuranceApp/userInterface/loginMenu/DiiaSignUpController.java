package com.marych.insuranceApp.userInterface.loginMenu;


import com.marych.insuranceApp.service.information.AppData;
import com.marych.insuranceApp.service.diia.DiiaCopy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DiiaSignUpController {

    @FXML
    private TextField diiaIdField;
    @FXML
    private TextField diiaSignField;
    @FXML
    private Label errorLabel;

    @FXML
    private void signUpButton(ActionEvent event){
        int diiaId = Integer.parseInt(diiaIdField.getText());
        int diiaSign = Integer.parseInt(diiaSignField.getText());
        if (validateDiiaDocument(diiaId,diiaSign)) {
            AppData.getInstance().put("diiaId",String.valueOf(diiaId));
            loadWindow(event,"SignUpScene.fxml");
        }else{
            errorLabel.setText("Ви ввели невірні дані цифрового документу");
        }
    }
    @FXML
    private void returnButton(ActionEvent event){
        loadWindow(event,"../loginMenu/LoginScene.fxml");
    }

    private boolean validateDiiaDocument(int diiaId,int diiaSign) {
        DiiaCopy diiaCopy = new DiiaCopy(diiaId);
        return diiaSign == diiaCopy.getDiiaSign();
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
