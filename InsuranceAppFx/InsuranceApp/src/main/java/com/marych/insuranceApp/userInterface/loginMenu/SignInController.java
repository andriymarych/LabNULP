package com.marych.insuranceApp.userInterface.loginMenu;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.user.UserSession;
import com.marych.insuranceApp.service.information.AppLogger;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SignInController {
    private byte loginAttemptsNumber;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label errorLabel;

    @FXML
    private void signInButton(ActionEvent event) {
        int userId;
        String login = usernameField.getText();
        String password = passwordField.getText();
        if (validateUser(login) == null) {
            errorLabel.setText("Користувача із логіном " + login + " не існує");
        } else if (password.equals(validateUser(login))) {
            loginAttemptsNumber = 0;
            UserSession.createInstance(login);
            userId = getUserId();
            UserSession.getInstance().setUserId(userId);
            AppLogger.info("User " + login + "(Id" + userId + ") is logged in");
            loadWindow(event, "../mainMenu/MainScene.fxml");
        } else {
            UserSession.createInstance(login);
            loginAttemptsNumber++;
            errorLabel.setText("Ви ввели невірний пароль");
            if (loginAttemptsNumber == 3) {
                AppLogger.error("User " + login + "(Id" + getUserId() + ") entered the wrong password three times");
            }
        }
    }

    @FXML
    private void returnButton(ActionEvent event) {
        loadWindow(event, "../loginMenu/LoginScene.fxml");
    }

    private String validateUser(String login) {
        try {
            String query = "SELECT * FROM \"user\" " + "WHERE login = '" + login + "'";
            ResultSet resultSet = DatabaseHandler.getInstance().execQuery(query);
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getUserId() {
        String query = "SELECT user_id " +
                "FROM customer " +
                "INNER JOIN \"user\" " +
                "ON \"user\".id = customer.user_id " +
                "WHERE \"user\".login = '" + UserSession.getInstance().getLogin() + "'";
        ResultSet resultSet = DatabaseHandler.getInstance().execQuery(query);
        try {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void loadWindow(ActionEvent event, String name) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(name)));
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
