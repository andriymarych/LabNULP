package com.marych.insuranceApp.service.creation;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.service.information.AppData;
import com.marych.insuranceApp.service.information.CompanyInformationService;
import com.marych.insuranceApp.service.WindowLoader;
import com.marych.insuranceApp.user.UserSession;
import com.marych.insuranceApp.userInterface.insuranceMenu.policyCreation.insurancePolicies.InsurancePolicyDaoSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public abstract class CreatePolicyControllerService implements Initializable {
    private int policyId;
    @FXML
    private TextField insuranceSumField;
    @FXML
    private TextField riskPercentageField;
    @FXML
    private CheckBox checkBox;
    @FXML
    private ChoiceBox<String> insuranceSpecialists;
    private int infoType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        insuranceSpecialists.getItems().addAll(CompanyInformationService.getInsuranceSpecialists(AppData.getInstance().get("insuranceCompany")));
    }


    public void returnButton(ActionEvent event) {
        WindowLoader.load(event, Objects.requireNonNull(getClass().getResource("../SelectCompanyScene.fxml")));
    }

    public void createInsurancePolicy() {
        policyId = DatabaseHandler.getInstance().getNextPolicyId();
        int holderId = UserSession.getInstance().getUserId();
        int insurerId = Integer.parseInt(insuranceSpecialists.getValue().split(" ")[0]);
        String companyId = Objects.requireNonNull(CompanyInformationService.getInsuranceSpecialist(insurerId)).getCompanyId();
        boolean compulsory = checkBox.isSelected();
        int insuredSum = Integer.parseInt(insuranceSumField.getText());
        short riskPercentage = Short.parseShort(riskPercentageField.getText());
        int insuredPayment = insuredSum * riskPercentage;
        String date = LocalDate.now().toString();
        InsurancePolicyDaoSender sender = new InsurancePolicyDaoSender(policyId,
                holderId, insurerId, companyId);
        sender.setCompulsory(compulsory)
                .setInsuredSum(insuredSum)
                .setInsuredPayment(insuredPayment)
                .setDate(date)
                .setRiskPersentage(riskPercentage)
                .setInfoType(infoType);
        sender.send();
    }

    public int getPolicyId() {
        return policyId;
    }
}
