package org.eleron.lris.niokr.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.eleron.lris.niokr.bussines.LoadScenes;
import org.eleron.lris.niokr.model.User;

public class NewReportControl {

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
    private ComboBox<String> endReport;

    @FXML
    private TableView usersTable;

    @FXML
    private Button saveBtn;

    @FXML
    private Button returnBtn;

    @FXML
    private void initialize(){

    }

    @FXML
    public void returnToMainMenu(){
        LoadScenes.load("view/MainMenu.fxml");
    }
}
