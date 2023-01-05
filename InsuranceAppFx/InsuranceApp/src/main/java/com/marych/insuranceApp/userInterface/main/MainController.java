package com.marych.insuranceApp.userInterface.main;

import com.marych.insuranceApp.user.UserSession;
import com.marych.insuranceApp.service.information.AppLogger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Label dateTime;
    @FXML
    private Button exitButton;

    @FXML
    private AnchorPane scenePane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initClock();
    }

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    @FXML
    private void InsurancePolicyMenuButton(ActionEvent event){
        loadWindow(event,"../insuranceMenu/PolicyMenuScene.fxml");
    }
    @FXML
    private void DerivativeMenuButton(ActionEvent event) {
        loadWindow(event,"../derivativeMenu/DerivativeMenuScene.fxml");
    }
    @FXML
    private void ExitButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Вихід");
        alert.setHeaderText("Схоже на те, що ви хочете вийти");
        alert.setContentText("Ви дійсно бажаєте вийти?");
        if(alert.showAndWait().get() == ButtonType.OK){
            AppLogger.info("User "+ UserSession.getInstance().getLogin()+ "(id"+ UserSession.getInstance().getUserId() + ") is logged out");
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        }
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
