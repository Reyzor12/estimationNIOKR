package org.eleron.lris.niokr.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.eleron.lris.niokr.bussines.LoadScenes;

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

    public void initialize(){

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
}
