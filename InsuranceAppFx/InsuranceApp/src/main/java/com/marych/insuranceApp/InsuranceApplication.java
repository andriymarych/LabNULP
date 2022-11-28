package com.marych.insuranceApp;

import com.marych.insuranceApp.dao.DatabaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;

public class InsuranceApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("userInterface/loginMenu/LoginScene.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Insurance App");
        Image image  = new Image("file:/Users/andriymarych/Desktop/Applied Programming/Insurance app/InsuranceAppFx/InsuranceApp/src/main/resources/com/marych/insuranceApp/images/AppIcon.png");
        stage.setScene(scene);
        stage.getIcons().add(image);
        stage.show();
    }

    public static void main(String[] args) {
        DatabaseHandler.getInstance();
        launch();
    }
}