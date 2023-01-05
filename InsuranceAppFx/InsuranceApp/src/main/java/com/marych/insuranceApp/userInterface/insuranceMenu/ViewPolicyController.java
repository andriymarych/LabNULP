package com.marych.insuranceApp.userInterface.insuranceMenu;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.user.UserSession;
import com.marych.insuranceApp.service.information.AppData;
import com.marych.insuranceApp.document.policy.ObservableInsurancePolicy;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewPolicyController implements Initializable {

    @FXML
    private TableView<ObservableInsurancePolicy> tableView;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Integer> policyId;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Boolean> compulsory;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Integer> companyId;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Integer> insuredId;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Integer> insurerId;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Double> insuredSum;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Double> insuredPayment;
    @FXML
    private TableColumn<ObservableInsurancePolicy, String> signDate;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Short> riskPercentage;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Short> infoType;
    private ObservableList<ObservableInsurancePolicy> policyList;
    private StringBuilder policiesNoString = new StringBuilder();

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
        policyList = DatabaseHandler.getInstance().getInsurancePolicyData(UserSession.getInstance().getUserId());
        tableView.setItems(policyList);

    }

    @FXML
    private void returnButton(ActionEvent event) {
        loadWindow(event, "../insuranceMenu/PolicyMenuScene.fxml");
    }

    @FXML
    private void viewDetailsButton(ActionEvent event) {
        loadWindow(event, "../insuranceMenu/ViewPolicyDetailsScene.fxml");
    }

    @FXML
    private void addToComparison(ActionEvent event) {
        ObservableList<ObservableInsurancePolicy> comparisonViewList;
        comparisonViewList = tableView.getSelectionModel().getSelectedItems();
        policiesNoString.append(comparisonViewList.get(0).getPolicyId()).append(" ");
    }

    @FXML
    private void showComparison(ActionEvent event) {
        AppData.getInstance().put("PolicyComparisonNo",policiesNoString.toString().trim());
        loadWindow(event, "../insuranceMenu/ComparisonScene.fxml");
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
