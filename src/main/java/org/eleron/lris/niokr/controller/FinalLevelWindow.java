package org.eleron.lris.niokr.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.bussines.LoadScenes;
import org.eleron.lris.niokr.model.Department;

public class FinalLevelWindow {

    private final static Logger log = Logger.getLogger(FinalLevelWindow.class);

    @FXML
    private Label allDepartments;

    @FXML
    private Label receiveDepartments;

    @FXML
    private Label notReceiveDepartments;

    @FXML
    private TableView<Department> departmentTableView;

    @FXML
    private ChoiceBox yearsChoiceBox;

    @FXML
    private ChoiceBox monthsChoiceBox;

    @FXML
    public void backToDepartment(){}

    @FXML
    public void settingsOpen(){

        LoadScenes.load("view/ReviewMenu.fxml");
    }

    @FXML
    public void reportForDepartment(){}

    @FXML
    public void reportForAllDepartments(){}

    public void initialize(){
        System.out.println("Hello world");
    }
}
