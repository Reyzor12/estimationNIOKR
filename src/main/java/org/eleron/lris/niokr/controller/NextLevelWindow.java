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
import org.eleron.lris.niokr.bussines.ReportBussines;
import org.eleron.lris.niokr.dao.ReportDAO;
import org.eleron.lris.niokr.dao.ReportDAOImplements;
import org.eleron.lris.niokr.model.Report;
import org.eleron.lris.niokr.model.User;
import org.eleron.lris.niokr.util.AlertUtil;

public class NextLevelWindow {

    /*
    * Logger
    * */

    private static final Logger log = Logger.getLogger(NextLevelWindow.class);

    /*
    * Fields
    * */

    private ObservableList<Report> reportObservableList;

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
}
