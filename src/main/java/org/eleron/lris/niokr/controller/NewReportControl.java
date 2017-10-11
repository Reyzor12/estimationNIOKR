package org.eleron.lris.niokr.controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.eleron.lris.niokr.bussines.Enter;
import org.eleron.lris.niokr.bussines.LoadScenes;
import org.eleron.lris.niokr.dao.DateOfReportsDAOImplements;
import org.eleron.lris.niokr.model.DateOfReports;
import org.eleron.lris.niokr.model.User;

import java.util.List;

public class NewReportControl {

    private List<Integer> years;

    @FXML
    private Label shortReportNameLabel;

    @FXML
    private Label longReportNameLabel;

    @FXML
    private Label timeReportLabel;

    @FXML
    private Label listUsersLabel;

    @FXML
    private TextField shortReportNameText;

    @FXML
    private TextArea longReportNameText;

    @FXML
    private ComboBox<Integer> startReport;

    @FXML
    private ComboBox<Integer> endReport;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, String> nameCol;

    @FXML
    private TableColumn<User,String> snameCol;

    @FXML
    private TableColumn<User,String> fnameCol;

    @FXML
    private TableColumn<User,Boolean> partyCol;

    @FXML
    private Button saveBtn;

    @FXML
    private Button returnBtn;

    private List<Integer> start;
    private List<Integer> end;

    private int flag;

    @FXML
    private void initialize(){
        if(Enter.getDateOfReports() == null){
            Enter.setDateOfReports(new DateOfReportsDAOImplements().getDates());
        }
        years = Enter.getDateOfReports();
        start = years.subList(0,years.size());
        end = years.subList(0,years.size());
        ObservableList<Integer> date = FXCollections.observableArrayList(years);
        startReport.setItems(date);
        endReport.setItems(date);
        ObservableList<User> users = FXCollections.observableArrayList(Enter.getUsers());
        nameCol.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        snameCol.setCellValueFactory(new PropertyValueFactory<User,String>("sname"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<User,String>("fname"));
//        partyCol.setCellValueFactory(new PropertyValueFactory<User,String>("fname"));
        partyCol.setCellFactory(column->new CheckBoxTableCell());
        usersTable.setItems(users);
        usersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    @FXML
    public void returnToMainMenu(){
        LoadScenes.load("view/MainMenu.fxml");
    }

    @FXML
    public void dateChooserEnd(){
        if(flag == 1) return;
        int index = endReport.getSelectionModel().getSelectedItem();
        if(index != -1){
            flag = 1;
            startReport.setItems(FXCollections.observableArrayList(years.subList(0,years.indexOf(index))));
            flag = 0;
        }
        /*if(index == -1){
            endReport.setItems(FXCollections.observableArrayList(years));
        }else{
            endReport.setItems(FXCollections.observableArrayList(index,years.size()));
        }*/
    }

    @FXML
    public void dateChooserStart(){
        if (flag == 1) return;
        int index = startReport.getSelectionModel().getSelectedItem();
        if(index != -1){
            flag = 1;
            endReport.setItems(FXCollections.observableArrayList(years.subList(years.indexOf(index)+1,years.size())));
            flag = 0;
        }
        /*if(index == -1){
            startReport.setItems(FXCollections.observableArrayList(years));
        } else{
            startReport.setItems(FXCollections.observableArrayList(0,index));
        }*/
    }
}
