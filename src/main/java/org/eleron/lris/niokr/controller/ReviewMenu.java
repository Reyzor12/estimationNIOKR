package org.eleron.lris.niokr.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.eleron.lris.niokr.bussines.LoadScenes;

public class ReviewMenu {

    @FXML
    private Button sendBtn;

    @FXML
    private Button addNewUserBtn;

    @FXML
    private Button backBtn;

    private void initialize(){

    }

    @FXML
    public void backToMainMenu(){

        LoadScenes.load("view/MainMenu.fxml");
    }

    @FXML
    public void newUser(){

        LoadScenes.load("view/NewUser.fxml");
    }
}
