package com.marych.insuranceApp.userInterface.insuranceMenu;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.user.UserSession;
import com.marych.insuranceApp.service.information.AppData;
import com.marych.insuranceApp.document.policy.policyType.liability.ProfessionalActivityInsurance;
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

public class ComparisonController implements Initializable {
    @FXML
    private TableView<ProfessionalActivityInsurance> liabilityView;
    @FXML
    private TableColumn<ProfessionalActivityInsurance, Integer> liabilityPolicyId;
    @FXML
    private TableColumn<ProfessionalActivityInsurance, String> liabilityFirstName;
    @FXML
    private TableColumn<ProfessionalActivityInsurance, String> liabilityLastName;
    @FXML
    private TableColumn<ProfessionalActivityInsurance, String> liabilityCompanyName;
    @FXML
    private TableColumn<ProfessionalActivityInsurance, String> liabilityProfessionalActivity;
    @FXML
    private TableColumn<ProfessionalActivityInsurance, String> liabilityPosition;

    private ObservableList<ProfessionalActivityInsurance> liabilityList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        liabilityPolicyId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        liabilityFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        liabilityLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        liabilityCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        liabilityProfessionalActivity.setCellValueFactory(new PropertyValueFactory<>("professionalActivity"));
        liabilityPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        liabilityList = DatabaseHandler.getInstance().getLiabilityPolicyList(UserSession.getInstance().getUserId(),AppData.getInstance().get("PolicyComparisonNo"));
        liabilityView.setItems(liabilityList);
    }
    @FXML
    private void returnButton(ActionEvent event) {
        loadWindow(event, "../insuranceMenu/ViewPolicyScene.fxml");
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
