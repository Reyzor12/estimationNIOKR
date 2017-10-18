package org.eleron.lris.niokr.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.eleron.lris.niokr.bussines.Enter;
import org.eleron.lris.niokr.bussines.LoadScenes;
import org.eleron.lris.niokr.bussines.ReportBussines;
import org.eleron.lris.niokr.model.Report;
import org.eleron.lris.niokr.util.AlertUtil;

import java.util.function.Predicate;

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

    private ObservableList<Report> reportsObs;

    @FXML
    public void initialize(){

        refreshTable();
        filter = new FilteredList<>(reportsObs, e->true);

    }

    FilteredList<Report> filter ;

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

    @FXML
    public void editReport(){

        if(tableView.getSelectionModel().getSelectedIndex() == -1){
            AlertUtil.getAlert("Не выбран ни один НИОКР для редактирования!");
        } else{
            Enter.setConsideredReport(tableView.getSelectionModel().getSelectedItem());
            LoadScenes.load("view/NewReportWindow.fxml");
        }
    }

    @FXML
    public void fillReport(){
        if(tableView.getSelectionModel().getSelectedIndex() == -1){
            AlertUtil.getAlert("Не выбран ни один НИОКР для заполнения!");
        } else{
            Enter.setConsideredReport(tableView.getSelectionModel().getSelectedItem());
            LoadScenes.load("view/FillReport.fxml");
        }
    }

    public void refreshTable(){
        reportsObs = FXCollections.observableArrayList(ReportBussines.loadReportMain());
        columnName.setCellValueFactory(new PropertyValueFactory<Report,String>("nameShort"));
        tableView.setItems(reportsObs);
    }




    @FXML
    public void search(KeyEvent event){

        findReportField.textProperty().addListener((observable,oldValue,newValue)->{

            filter.setPredicate((Predicate<? super Report>) (Report report)->{

                if(newValue.isEmpty() || newValue==null){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(report.getNameShort().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }

                return false;
            });

        });
        SortedList<Report> sort = new SortedList(filter);
        sort.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sort);
    }

}
