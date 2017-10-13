package org.eleron.lris.niokr.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.eleron.lris.niokr.bussines.LoadScenes;
import org.eleron.lris.niokr.bussines.ReportBussines;
import org.eleron.lris.niokr.model.Report;

import java.util.List;

public class MainMenu {

    @FXML
    private Button findBtn;

    @FXML
    private Button editReportBtn;

    @FXML
    private Button fillReportBtn;

    @FXML
    private Button supportBtn;

    @FXML
    private Button addNewUser;

    @FXML
    private Button addNewReport;

    @FXML
    private TableView<Report> tableView;

    @FXML
    private TableColumn<Report,String> columnName;

    @FXML
    private TextField findReportField;

    @FXML
    public void initialize(){

        refreshTable();


    }

    @FXML
    public void addMessage(){
        LoadScenes.load("view/ReviewMenu.fxml");
    }

    @FXML
    public void addUser(){
        LoadScenes.load("view/NewUser.fxml");
    }

    @FXML
    public void addReport(){

        LoadScenes.load("view/NewReportWindow.fxml");
    }

    public void findReport(String request){
        tableView.setRowFactory(item->new TableRow<Report>(){
            public void updateItem(Report report, boolean okey){
                super.updateItem(report,okey);
                if(request!="" && report.getNameShort().matches(request)){
                    setStyle("-fx-background-color: tomato;");
                } else{
                    setStyle("");
                }
            }
        });
        /*for(int i=0;i<tableView.getItems().size();i++){
            if(tableView.getItems().get(i).getNameShort().matches(request)){
                tableView.getColumns().
            }
        }*/
    }

    public void refreshTable(){
        ObservableList<Report> reportsObs = FXCollections.observableArrayList(ReportBussines.loadReportMain());
        columnName.setCellValueFactory(new PropertyValueFactory<Report,String>("nameShort"));
        tableView.setItems(reportsObs);
    }

    @FXML
    public void findReport(){
        findReport(findReportField.getText());
    }
}
