package com.marych.insuranceApp.userInterface.insuranceMenu;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewPolicyController implements Initializable {

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
    private ObservableList<InsurancePolicy> policyList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        policyId.setCellValueFactory(new PropertyValueFactory<InsurancePolicy,Integer>("policyId"));
        compulsory.setCellValueFactory(new PropertyValueFactory<InsurancePolicy,Boolean>("compulsory"));
        insuredId.setCellValueFactory(new PropertyValueFactory<InsurancePolicy,Integer>("holderId"));
        insurerId.setCellValueFactory(new PropertyValueFactory<InsurancePolicy,Integer>("insurerId"));
        companyId.setCellValueFactory(new PropertyValueFactory<InsurancePolicy,Integer>("companyId"));
        insuredSum.setCellValueFactory(new PropertyValueFactory<InsurancePolicy,Double>("insuredSum"));
        insuredPayment.setCellValueFactory(new PropertyValueFactory<InsurancePolicy,Double>("insuredPayment"));
        signDate.setCellValueFactory(new PropertyValueFactory<InsurancePolicy,String>("signDate"));
        riskPercentage.setCellValueFactory(new PropertyValueFactory<InsurancePolicy,Short>("riskPercentage"));
        infoType.setCellValueFactory(new PropertyValueFactory<InsurancePolicy,Short>("infoType"));
        policyList = DatabaseHandler.getInstance().getInsurancePolicyData(UserSession.getInstance().getUserId());
        tableView.setItems(policyList);

    }
    @FXML
    private void returnButton(ActionEvent event) {
        loadWindow(event,"../insuranceMenu/PolicyMenuScene.fxml");
    }
    @FXML
    private void viewDetailsButton(ActionEvent event) {
        loadWindow(event,"../insuranceMenu/ViewPolicyDetailsScene.fxml");
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
