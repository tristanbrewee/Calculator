package com.syntra.tristanbrewee.javafx.controllers;

import com.syntra.tristanbrewee.clients.Client1;
import com.syntra.tristanbrewee.generalutils.GeneralUtils;
import com.syntra.tristanbrewee.model.Calculation;
import com.syntra.tristanbrewee.model.Operator;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Client1FxmlController {

    @FXML
    TextField txtNumber1;
    @FXML
    ComboBox<String> cmbOperator;
    @FXML
    TextField txtNumber2;
    @FXML
    Button btnCalculate;
    @FXML
    Button btnShowAllCalculations;
    @FXML
    Button btnSaveAndExit;
    @FXML
    TextArea txtResult;


    public void initialize(){
        setComboboxOptions();
        Client1.connectToServer(Client1.getHostname(), Client1.getPortNumberCalculations());
    }

    private void setComboboxOptions(){
        for (Operator operator: Operator.values()) {
            cmbOperator.getItems().add(operator.toString());
        }
        cmbOperator.getSelectionModel().select("+");
    }

    public void btnCalculatePressed(){
        if (!checkInputs())
            return;
        double number1 = Double.parseDouble(txtNumber1.getText());
        double number2 = Double.parseDouble(txtNumber2.getText());
        String operatorAsString = cmbOperator.getSelectionModel().getSelectedItem();
        Calculation calculation = new Calculation(number1, number2, operatorAsString);
        if (Client1.calculationsContainsCalculation(calculation)){
            txtResult.setText("This calculation is already in your history! Press \"Show All Calculations\"");
            return;
        }
        calculation = Client1.process(calculation);
        txtResult.setText(calculation.toString());
    }

    private boolean checkInputs(){
        if (!GeneralUtils.isDouble(txtNumber1.getText())){
            txtResult.setText("Number 1 isn't a number!");
            return false;
        }
        if (!GeneralUtils.isDouble(txtNumber2.getText())){
            txtResult.setText("Number 2 isn't a number!");
            return false;
        }
        if (GeneralUtils.isPowerOperator(cmbOperator.getSelectionModel().getSelectedItem())){
            if (!GeneralUtils.isWholeNumber(txtNumber2.getText())){
                txtResult.setText("When selecting the power operator, number 2 must be a whole number (Integer)!");
                return false;
            }
        }
        return true;
    }

    public void BtnShowAllCalculationsPressed(){
        StringBuilder result = new StringBuilder();
        Client1.getCalculations()
                .stream()
                .sorted()
                .forEachOrdered(e -> result.append(e.toString() + "\n"));
        if (result.length() == 0){
            txtResult.setText("Your history is empty! Add some calculations first.");
            return;
        }
        txtResult.setText(result.toString());
    }

    public void BtnSaveAndExitPressed(){
        Client1.safeToFile();
        System.exit(0);
    }
}
