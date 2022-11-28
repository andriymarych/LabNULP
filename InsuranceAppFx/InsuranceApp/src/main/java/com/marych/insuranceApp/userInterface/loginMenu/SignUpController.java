package com.marych.insuranceApp.userInterface.loginMenu;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.utils.AppData;
import com.marych.insuranceApp.utils.DiiaCopy;
import com.marych.insuranceApp.utils.EmailValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class SignUpController {

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private Label errorLabel;

    @FXML
    private void signUpButton(ActionEvent event) {
        String login = loginField.getText();
        if (!DatabaseHandler.getInstance().checkLogin(login)) {
            errorLabel.setText("Користувач із таким логіном уже існує");
        } else if (passwordField.getText().equals("")) {
            errorLabel.setText("Введіть пароль");
        } else if (!EmailValidation.validate(emailField.getText())) {
            errorLabel.setText("Ви ввели невірний email");
        } else {
            addUserData();
            loadMainWindow(event);
        }
    }

    private void addUserData() {
        int userId = DatabaseHandler.getInstance().getNextUserId();
        int diiaId = Integer.parseInt(AppData.getInstance().get("diiaId"));
        DiiaCopy diiaCopy = new DiiaCopy(diiaId);
        String query = "INSERT INTO \"user\" " + " VALUES (" +
                userId + ", '" +
                loginField.getText() + "','" +
                passwordField.getText() + "', " + 1 + ")";
        DatabaseHandler.getInstance().execUpdate(query);
        query = "INSERT INTO \"customer\" " + " VALUES (" +
                userId + ", '" +
                diiaCopy.getFirstName() + "','" +
                diiaCopy.getLastName() + "', '" +
                diiaCopy.getBirthDate() + "', " +
                diiaCopy.getITN() + ", '" +
                emailField.getText() + "')";
        DatabaseHandler.getInstance().execUpdate(query);
    }

    private void loadMainWindow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../mainMenu/MainScene.fxml")));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
