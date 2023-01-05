package com.marych.insuranceApp.userInterface.derivativeMenu;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreateDerivativeController implements Initializable {

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
    private TableColumn<ObservableInsurancePolicy, Short> infoType;
    private ObservableList<ObservableInsurancePolicy> policyList;
    @FXML
    private ChoiceBox<String> insuranceSpecialists;
    @FXML
    private TextField policyNoField;
    @FXML
    private Label errorLabel;
    private int derivativeCompanyId;
    private int derivativeInsurerId;
    private int derivativeId;
    String[] policyNoArray;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        insuranceSpecialists.getItems().addAll(getInsuranceSpecialists());
        policyId.setCellValueFactory(new PropertyValueFactory<ObservableInsurancePolicy, Integer>("policyId"));
        compulsory.setCellValueFactory(new PropertyValueFactory<ObservableInsurancePolicy, Boolean>("compulsory"));
        insuredId.setCellValueFactory(new PropertyValueFactory<ObservableInsurancePolicy, Integer>("holderId"));
        insurerId.setCellValueFactory(new PropertyValueFactory<ObservableInsurancePolicy, Integer>("insurerId"));
        companyId.setCellValueFactory(new PropertyValueFactory<ObservableInsurancePolicy, Integer>("companyId"));
        insuredSum.setCellValueFactory(new PropertyValueFactory<ObservableInsurancePolicy, Double>("insuredSum"));
        insuredPayment.setCellValueFactory(new PropertyValueFactory<ObservableInsurancePolicy, Double>("insuredPayment"));
        signDate.setCellValueFactory(new PropertyValueFactory<ObservableInsurancePolicy, String>("signDate"));
        infoType.setCellValueFactory(new PropertyValueFactory<ObservableInsurancePolicy, Short>("infoType"));
        policyList = DatabaseHandler.getInstance().getInsurancePolicyData(UserSession.getInstance().getUserId());
        tableView.setItems(policyList);
    }

    @FXML
    private void returnButton(ActionEvent event) {
        loadWindow(event, "/com/marych/insuranceApp/userInterface/insuranceMenu/policyCreation/SelectCompanyScene.fxml");
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

    private ArrayList<String> getInsuranceSpecialists() {
        ArrayList<String> insuranceSpecialists = new ArrayList<>();
        String companyName = AppData.getInstance().get("insuranceCompany");
        String query = "SELECT CONCAT(first_name,' ',last_name) as fullName, user_id, company_id  " +
                "FROM insurance_specialist " +
                "INNER JOIN insurance_company " +
                "ON insurance_specialist.insurance_company_id = insurance_company.company_id " +
                "WHERE insurance_company.name  = '" + companyName + "'";
        ResultSet resultSet = DatabaseHandler.getInstance().execQuery(query);
        try {
            while (resultSet.next()) {
                insuranceSpecialists.add(resultSet.getString("fullName"));
                derivativeCompanyId = resultSet.getInt("company_id");
                derivativeInsurerId = resultSet.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insuranceSpecialists;
    }

    @FXML
    private void createDerivative(ActionEvent event) {
        if (checkPolicyList()) {
            derivativeId = DatabaseHandler.getInstance().getNextDerivativeId();
            addDerivative();
            addPolicyList();
            loadWindow(event, "../derivativeMenu/SuccessDerivativeCreationScene.fxml");
        }
    }

    private void addDerivative() {
        double price = selectPrice()*1.02;
        int userId = UserSession.getInstance().getUserId();
        String query = "INSERT INTO \"derivative\" " + " VALUES (" +
                derivativeId + ", " +
                userId + ", " +
                derivativeInsurerId + ", " +
                derivativeCompanyId + ", " +
                price + ", '" +
                LocalDate.now() + "') ";
        DatabaseHandler.getInstance().execUpdate(query);
    }

    private double selectPrice() {
        StringBuilder query = new StringBuilder("SELECT SUM(insured_payment) " +
                "FROM insurance_policy " +
                "WHERE policy_id IN (");
        for (String policyNo : policyNoArray) {
            query.append(policyNo).append(",");
        }
        query.deleteCharAt(query.length() - 1);
        query.append(")");
        ResultSet resultSet = DatabaseHandler.getInstance().execQuery(query.toString());
        try {
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void addPolicyList() {
        StringBuilder SQL = new StringBuilder("INSERT INTO \"derivative_policy_list\" " + " VALUES ");
        for (String policyNo : policyNoArray) {
            SQL.append("(")
                    .append(derivativeId)
                    .append(", ")
                    .append(policyNo)
                    .append("),");
        }
        SQL.deleteCharAt(SQL.length() - 1);
        DatabaseHandler.getInstance().execUpdate(SQL.toString());

    }

    private boolean checkPolicyList() {
        int userId = UserSession.getInstance().getUserId();
        int policyNo;
        policyNoArray = policyNoField.getText().split(" ");
        for (String s : policyNoArray) {
            if(policyNoArray.length < 2){
                errorLabel.setText("Введіть мінімум 2 страхових договори для формування деривативу");
            }
            policyNo = Integer.parseInt(s);
            if (!DatabaseHandler.getInstance().containsPolicy(userId, policyNo)) {
                errorLabel.setText("Страхового договору № " + policyNo + " не існує");
                return false;
            }
        }
        return true;
    }
}
