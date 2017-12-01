package org.eleron.lris.niokr.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.bussines.Enter;
import org.eleron.lris.niokr.bussines.MicrosoftReports;
import org.eleron.lris.niokr.bussines.ReportBussines;
import org.eleron.lris.niokr.dao.ReportDAO;
import org.eleron.lris.niokr.dao.ReportDAOImplements;
import org.eleron.lris.niokr.model.Report;
import org.eleron.lris.niokr.model.User;
import org.eleron.lris.niokr.util.AlertUtil;
import org.eleron.lris.niokr.util.DateUtil;

import java.awt.Desktop;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class NextLevelWindow {

    /*
    * Logger
    * */

    private static final Logger log = Logger.getLogger(NextLevelWindow.class);

    /*
    * Fields
    * */

    private List<String> commonData;

    private ObservableList<Report> reportObservableList;

    private static final String REPORT_PATH = "templates/reports/";

    /*
    * FXML Fields
    * */

    @FXML
    private TableView<Report> tableView;

    @FXML
    private TableColumn<Report,String> nameColumn;

    @FXML
    private TableColumn<Report,Integer> monthColumn;

    @FXML
    private TableColumn<Report,Integer> yearColumn;

    @FXML
    private TableColumn<Report,User> ownerColumn;

    @FXML
    private TableColumn<Report,Integer> statusColumn;

    @FXML
    private TextArea progressText;

    @FXML
    private TextArea problemText;

    /*
    * Init method
    * */

    @FXML
    private void initialize(){
        loadReports();
    }

    /*
    * Functions load reports
    * */

    public void loadReports(){
        commonData = Arrays.asList(
                "Жихареву С.Н.",
                DateUtil.getCurrentMonthR().substring(0,DateUtil.getCurrentMonthR().length()-1) + "е",
                Integer.toString(Calendar.getInstance().get(Calendar.YEAR)),
                Enter.getcUser().getDepartment().getName(),
                Enter.getcUser().getDepartment().getHead());
        reportObservableList = FXCollections.observableArrayList();
        for(Report report : ReportBussines.loadReportMain()){
            if(report!=null?(report.getStatus()==1||report.getStatus()==2):false){
                reportObservableList.add(report);
            }
        }
        nameColumn.setCellValueFactory(new PropertyValueFactory<Report,String>("nameShort"));
        monthColumn.setCellValueFactory(new PropertyValueFactory<Report,Integer>("persentOfMonth"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Report,Integer>("persentOfYear"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<Report,User>("owner"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Report,Integer>("status"));
        tableView.setItems(reportObservableList);
        tableView.setRowFactory(tv->{
            TableRow<Report> row = new TableRow<>();
            row.setOnMouseClicked(event->{
               if(event.getClickCount() == 1 && (!row.isEmpty())){
                   Report report = row.getItem();
                   progressText.setText(report.getText());
                   problemText.setText(report.getTrouble());
               }
            });
            return row;
        });
    }

    /*
    * save changes
    * */

    @FXML
    public void saveChanges(){
        Report report = tableView.getSelectionModel().getSelectedItem();
        if (report != null) {
            report.setText(progressText.getText());
            report.setTrouble(problemText.getText());
            ReportDAO reportDAO = new ReportDAOImplements();
            reportDAO.updateReport(report);
            log.info("update report = " + report);
            AlertUtil.getInformation("Изменения успешно сохранены");
        } else {
            AlertUtil.getAlert("Не выбран ни один НИОКР!");
        }
    }

    /*
    * Approve button
    * */

    @FXML
    private void approveReport(){
        Report report = tableView.getSelectionModel().getSelectedItem();
        if(report != null ) {
            if (report.getStatus() == 1) {
                report.setStatus(2);
                ReportDAO reportDAO = new ReportDAOImplements();
                reportDAO.updateReport(report);
                AlertUtil.getInformation("Отчет по НИОКР одобрен и отправлен");
                tableView.refresh();
                log.info("update report = " + report);
            } else {
                AlertUtil.getAlert("Отчет по НИОКР уже отправлен");
            }
        }else{
            AlertUtil.getAlert("Не выбран ни один НИОКР для отправки");
        }
    }

    @FXML
    private void rejectReport(){
        Report report = tableView.getSelectionModel().getSelectedItem();
        if(report != null){
            if(report.getStatus() == 1 || report.getStatus() == 2){
                report.setStatus(report.getStatus()-1);
                ReportDAO reportDAO = new ReportDAOImplements();
                reportDAO.updateReport(report);
                AlertUtil.getInformation("НИОКР успешно отозван");
                if(report.getStatus()==0){
                    tableView.getItems().remove(report);
                    progressText.clear();
                    problemText.clear();
                }
                tableView.refresh();
                log.info("update report = " + report);
            } else {
                AlertUtil.getAlert("Данный НИОКР нельзя отозвать");
            }
        }else{
            AlertUtil.getAlert("Не выбран ни один НИОКР");
        }
    }

    @FXML
    private void showReport(){
        if(tableView.getSelectionModel().getSelectedItem() != null){
            String path = NextLevelWindow.class.getClassLoader().getResource(REPORT_PATH + "StyleDoc.docx").getPath();
            String savePath = path.replace("StyleDoc.docx","departmentTemp.docx");
//            MicrosoftReports.fromReportToWord(tableView.getSelectionModel().getSelectedItem(),path,savePath);
            MicrosoftReports.formReportTo(commonData, reportObservableList,savePath,path);
            if (Desktop.isDesktopSupported()) {
                File file = new File(savePath);
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException e) {
                    log.error("Не удалось выполнить функцию showReport() класса NextLevelWindow");
                    e.printStackTrace();
                }
            }else{
                AlertUtil.getInformation("Нельзя открыть word документ на вышей ОС");
            }
        } else{
            AlertUtil.getAlert("Не выбран ни один НИОКР");
        }
    }
}
