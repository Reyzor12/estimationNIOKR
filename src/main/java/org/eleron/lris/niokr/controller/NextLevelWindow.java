package org.eleron.lris.niokr.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.bussines.ReportBussines;
import org.eleron.lris.niokr.model.Report;
import org.eleron.lris.niokr.model.User;

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
    }
}
