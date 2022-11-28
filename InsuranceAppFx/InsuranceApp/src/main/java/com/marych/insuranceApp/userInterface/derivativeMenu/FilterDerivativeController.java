package com.marych.insuranceApp.userInterface.derivativeMenu;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.session.UserSession;
import com.marych.insuranceApp.workClass.InsurancePolicy;
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

public class FilterDerivativeController implements Initializable {

    @FXML
    private TextField derivativeNoField;
    @FXML
    private TableView<InsurancePolicy> tableView;
    @FXML
    private TableColumn<InsurancePolicy, Integer> policyId;
    @FXML
    private TableColumn<InsurancePolicy, Boolean> compulsory;
    @FXML
    private TableColumn<InsurancePolicy, Integer> companyId;
    @FXML
    private TableColumn<InsurancePolicy, Integer> insuredId;
    @FXML
    private TableColumn<InsurancePolicy, Integer> insurerId;
    @FXML
    private TableColumn<InsurancePolicy, Double> insuredSum;
    @FXML
    private TableColumn<InsurancePolicy, Double> insuredPayment;
    @FXML
    private TableColumn<InsurancePolicy, String> signDate;
    @FXML
    private TableColumn<InsurancePolicy, Short> riskPercentage;
    @FXML
    private TableColumn<InsurancePolicy, Short> infoType;
    @FXML
    private TextField startSumField;
    @FXML
    private TextField endSumField;

    private ObservableList<InsurancePolicy> policyList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        policyId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        compulsory.setCellValueFactory(new PropertyValueFactory<>("compulsory"));
        insuredId.setCellValueFactory(new PropertyValueFactory<>("holderId"));
        insurerId.setCellValueFactory(new PropertyValueFactory<>("insurerId"));
        companyId.setCellValueFactory(new PropertyValueFactory<>("companyId"));
        insuredSum.setCellValueFactory(new PropertyValueFactory<>("insuredSum"));
        insuredPayment.setCellValueFactory(new PropertyValueFactory<>("insuredPayment"));
        signDate.setCellValueFactory(new PropertyValueFactory<>("signDate"));
        riskPercentage.setCellValueFactory(new PropertyValueFactory<>("riskPercentage"));
        infoType.setCellValueFactory(new PropertyValueFactory<>("infoType"));

    }
    @FXML
    private void returnButton(ActionEvent event) {
        loadWindow(event,"../derivativeMenu/ViewDerivativeMenuScene.fxml");
    }
    @FXML
    private void filterButton(ActionEvent event) {
        policyList = DatabaseHandler.getInstance().
                getInsurancePolicyDataByPrice(derivativeNoField.getText(),startSumField.getText(),endSumField.getText());
        tableView.setItems(policyList);
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
