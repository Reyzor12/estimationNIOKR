package org.eleron.lris.niokr.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.eleron.lris.niokr.bussines.LoadScenes;

public class MainMenu {

    @FXML
    private Button supportBtn;

    @FXML
    private Button addNewUser;

    public void initialize(){

    }

    @FXML
    public void addReport(){
        LoadScenes.load("view/ReviewMenu.fxml");
    }

    @FXML
    public void addUser(){
        LoadScenes.load("view/NewUser.fxml");
    }
}
