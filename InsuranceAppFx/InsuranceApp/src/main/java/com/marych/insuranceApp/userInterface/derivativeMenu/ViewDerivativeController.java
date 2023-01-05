package com.marych.insuranceApp.userInterface.derivativeMenu;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.user.UserSession;
import com.marych.insuranceApp.document.derivative.Derivative;
import com.marych.insuranceApp.document.policy.PolicyNode;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewDerivativeController implements Initializable {
    @FXML
    private TableView<Derivative> tableView;
    @FXML
    private TableView<PolicyNode> policyListView;
    @FXML
    private TableColumn<Derivative, Integer> derivativeId;
    @FXML
    private TableColumn<Derivative, Integer> companyId;
    @FXML
    private TableColumn<Derivative, Integer> insuredId;
    @FXML
    private TableColumn<Derivative, Integer> insurerId;
    @FXML
    private TableColumn<Derivative, Double> derivativePrice;
    @FXML
    private TableColumn<Derivative, String> signDate;

    @FXML
    private TableColumn<PolicyNode, Integer> policyViewId;
    @FXML
    private TextField derivativeNoField;

    private ObservableList<Derivative> derivativeList;
    private ObservableList<PolicyNode> policyList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        policyViewId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        derivativeId.setCellValueFactory(new PropertyValueFactory<>("derivativeId"));
        insuredId.setCellValueFactory(new PropertyValueFactory<>("holderId"));
        insurerId.setCellValueFactory(new PropertyValueFactory<>("insurerId"));
        companyId.setCellValueFactory(new PropertyValueFactory<>("companyId"));
        derivativePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        signDate.setCellValueFactory(new PropertyValueFactory<>("signDate"));
        derivativeList = DatabaseHandler.getInstance().getDerivativeData(UserSession.getInstance().getUserId());
        tableView.setItems(derivativeList);
    }

    @FXML
    private void returnButton(ActionEvent event) {
        loadWindow(event, "../derivativeMenu/ViewDerivativeMenuScene.fxml");
    }

    @FXML
    private void viewPolicyList(ActionEvent event) {
        policyList = DatabaseHandler.getInstance().getDerivativePolicyList(derivativeNoField.getText());
        policyListView.setItems(policyList);
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
