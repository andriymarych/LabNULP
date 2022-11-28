package com.marych.insuranceApp.userInterface.insuranceMenu;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.session.UserSession;
import com.marych.insuranceApp.workClass.policyType.LiabilityPolicy;
import com.marych.insuranceApp.workClass.policyType.PersonalPolicy;
import com.marych.insuranceApp.workClass.policyType.PropertyPolicy;
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

public class ViewPolicyDetailsController implements Initializable {
    @FXML
    private TableView<PersonalPolicy> personalView;
    @FXML
    private TableColumn<PersonalPolicy, Integer> personalPolicyId;
    @FXML
    private TableColumn<PersonalPolicy, String> personalFirstName;
    @FXML
    private TableColumn<PersonalPolicy, String> personalLastName;
    @FXML
    private TableColumn<PersonalPolicy, String> personalAddress;
    @FXML
    private TableColumn<PersonalPolicy, String> personalBirthDate;


    @FXML
    private TableView<LiabilityPolicy> liabilityView;
    @FXML
    private TableColumn<LiabilityPolicy, Integer> liabilityPolicyId;
    @FXML
    private TableColumn<LiabilityPolicy, String> liabilityFirstName;
    @FXML
    private TableColumn<LiabilityPolicy, String> liabilityLastName;
    @FXML
    private TableColumn<LiabilityPolicy, String> liabilityCompanyName;
    @FXML
    private TableColumn<LiabilityPolicy, String> liabilityProfessionalActivity;
    @FXML
    private TableColumn<LiabilityPolicy, String> liabilityPosition;


    @FXML
    private TableView<PropertyPolicy> propertyView;
    @FXML
    private TableColumn<PropertyPolicy, Integer> propertyPolicyId;
    @FXML
    private TableColumn<PropertyPolicy, Integer> propertyFirstName;
    @FXML
    private TableColumn<PropertyPolicy, String> propertyLastName;
    @FXML
    private TableColumn<PropertyPolicy, String> propertyCarBrand;
    @FXML
    private TableColumn<PropertyPolicy, String> propertyCarModel;
    @FXML
    private TableColumn<PropertyPolicy, String> propertyLicensePlate;

    private ObservableList<PersonalPolicy> personalList;
    private ObservableList<LiabilityPolicy> liabilityList;
    private ObservableList<PropertyPolicy> propertyList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personalPolicyId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        personalFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        personalLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        personalAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        personalBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        personalList = DatabaseHandler.getInstance().getPersonalPolicyData(UserSession.getInstance().getUserId());
        personalView.setItems(personalList);

        liabilityPolicyId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        liabilityFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        liabilityLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        liabilityCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        liabilityProfessionalActivity.setCellValueFactory(new PropertyValueFactory<>("professionalActivity"));
        liabilityPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        liabilityList = DatabaseHandler.getInstance().getLiabilityPolicyData(UserSession.getInstance().getUserId());
        liabilityView.setItems(liabilityList);

        propertyPolicyId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        propertyFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        propertyLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        propertyCarBrand.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
        propertyCarModel.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        propertyLicensePlate.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
        propertyList = DatabaseHandler.getInstance().getPropertyPolicyData(UserSession.getInstance().getUserId());
        propertyView.setItems(propertyList);
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
