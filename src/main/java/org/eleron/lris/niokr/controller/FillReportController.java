package org.eleron.lris.niokr.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.eleron.lris.niokr.bussines.Enter;
import org.eleron.lris.niokr.model.Report;

import java.util.Calendar;

public class FillReportController {

    private Report report;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button sendBtn;

    @FXML
    private Label periodLabel;

    @FXML
    private Label departmentLabel;

    @FXML
    private Label ownerLabel;

    @FXML
    private Label nameReportLabel;

    @FXML
    private Label perYearLabel;

    @FXML
    private TextArea progressTArea;

    @FXML
    private TextArea troubleTArea;

    @FXML
    private void initialize(){
        init();
    }

    private void init(){
        report = Enter.getConsideredReport();
        if(report == null) return;
        periodLabel.setText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) +
                "   (Крайняя дата отправления: 20 " +
                String.valueOf(Calendar.getInstance().get(Calendar.MONTH)));
    }
}
