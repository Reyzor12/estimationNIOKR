package org.eleron.lris.niokr.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.eleron.lris.niokr.bussines.Enter;
import org.eleron.lris.niokr.bussines.LoadScenes;
import org.eleron.lris.niokr.dao.ReportDAO;
import org.eleron.lris.niokr.dao.ReportDAOImplements;
import org.eleron.lris.niokr.model.Report;
import org.eleron.lris.niokr.util.AlertUtil;
import org.eleron.lris.niokr.util.DateUtil;

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
    private Spinner<Integer> monthSpiner;

    @FXML
    private void initialize(){
        init();
    }

    private void init(){
        report = Enter.getConsideredReport();
        if(report == null) return;
        String periodString = "%s %d     (Крайняя дата отправки: 20 %s %d г.)";
        periodLabel.setText(String.format(periodString, DateUtil.getCurrentMonth(),
                Calendar.getInstance().get(Calendar.YEAR),
                DateUtil.getCurrentMonthR(),
                Calendar.getInstance().get(Calendar.YEAR)));
        departmentLabel.setText(Enter.getcUser().getDepartment().getName());
        ownerLabel.setText(report.getOwner().toString());

        String reportString = "СЧ ОКР \"%s\", шифр \"%s\"";
        nameReportLabel.setText(String.format(reportString, report.getNameLong(),report.getNameShort()));
        String yearString = "Процент выполнения ОКР за текущий год: %d";
        perYearLabel.setText(String.format(yearString,report.getPersentOfYear())+ "% (текущий месяц не учитывается)");
        int monthPer = report.getPersentOfMonth()==null?0:report.getPersentOfMonth();
        SpinnerValueFactory<Integer> spinnervf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,monthPer);
        monthSpiner.setValueFactory(spinnervf);
        if (report.getText() != null) progressTArea.setText(report.getText());
        if (report.getTrouble() != null) troubleTArea.setText(report.getTrouble());
    }

    @FXML
    public void returnMehod(){
        LoadScenes.load("view/MainMenu.fxml");
    }

    @FXML
    public void fillReport(){
        if(progressTArea.getText().isEmpty()) {
           AlertUtil.getAlert("Не все поля были заполнены!");
           return;
        }
        report.setText(progressTArea.getText());
        report.setTrouble(troubleTArea.getText());
        report.setPersentOfMonth(monthSpiner.getValue());
        ReportDAO reportDAO = new ReportDAOImplements();
        reportDAO.updateReport(report);
        LoadScenes.load("view/MainMenu.fxml");
    }
}
