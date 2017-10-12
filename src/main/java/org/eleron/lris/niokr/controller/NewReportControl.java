package org.eleron.lris.niokr.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.eleron.lris.niokr.bussines.*;
import org.eleron.lris.niokr.dao.DateOfReportsDAOImplements;
import org.eleron.lris.niokr.model.Report;
import org.eleron.lris.niokr.model.User;
import org.eleron.lris.niokr.util.AlertUtil;

import java.util.ArrayList;
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
    private TableView<Person> usersTable;

    @FXML
    private TableColumn<Person, String> nameCol;

    @FXML
    private TableColumn<Person,String> snameCol;

    @FXML
    private TableColumn<Person,String> fnameCol;

    @FXML
    private TableColumn<Person,Boolean> partyCol;

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
        ObservableList<Person> persons = FXCollections.observableArrayList(UserBussines.toPerson(Enter.getUsers()));
        nameCol.setCellValueFactory(new PropertyValueFactory<Person,String>("name"));
        snameCol.setCellValueFactory(new PropertyValueFactory<Person,String>("sname"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<Person,String>("fname"));
        partyCol.setCellValueFactory(new PropertyValueFactory<Person,Boolean>("check"));
        partyCol.setCellFactory(column->new CheckBoxTableCell());
        usersTable.setItems(persons);

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

    }

    @FXML
    public void addReport(){
        String name = shortReportNameText.getText();
        String fullName = longReportNameText.getText();
        Integer start = startReport.getSelectionModel().getSelectedItem();
        Integer end = endReport.getSelectionModel().getSelectedItem();
        Person[] persons = new Person[usersTable.getItems().size()];
        List<User> users = new ArrayList<User>();
        for (Person person: usersTable.getItems().toArray( persons)) {
            if(person.isCheck()){
                users.add(person.getUser());
            }
        }
        if(!ReportBussines.check(name,fullName,start,end,users)){
            AlertUtil.getAlert("Не все поля были заполнены или не было выбрано ни одного исполнителя!");
        }else{
            ReportBussines.saveNewReportDB(name,fullName,start,end,users);
            LoadScenes.load("view/MainMenu.fxml");
        }
    }

}
