package org.eleron.lris.niokr.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.bussines.Enter;
import org.eleron.lris.niokr.bussines.LoadScenes;
import org.eleron.lris.niokr.bussines.UserBussines;
import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.util.ValidationUtil;

public class NewUser {

    /*
    * Logger
    * */

    private static final Logger log = Logger.getLogger(NewUser.class);

    /*
    * FXML Fields
    * */

    @FXML
    private TextField nameField;

    @FXML
    private TextField snameField;

    @FXML
    private TextField fnameField;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button addBtn;

    /*
    * Init method
    * */

    @FXML
    private void initialize(){

        ValidationUtil.validUserField(nameField);
        ValidationUtil.validUserField(snameField);
        ValidationUtil.validUserField(fnameField);
    }

    /*
    * FXML Methods
    * */

    @FXML
    public void goBack(){
        switch(Enter.getcUser().getRole()){
            case 1: LoadScenes.load("view/MainMenu.fxml");break;
            case 2: LoadScenes.load("view/NextLevelWindow.fxml");break;
            case 3: LoadScenes.load("view/FinalLevelWindow.fxml");break;
            default: LoadScenes.load("view/ErrorPage.fxml");
        }
    }

    @FXML
    public void addUser(){

        String name = nameField.getText();
        String sname = snameField.getText();
        String fname = fnameField.getText();
        Department department = Enter.getcUser().getDepartment();
        try{
            log.info("add new user name-" +name+ " sname-" + sname
                    + " fname-" + fname);
            UserBussines.formedUser(name,sname,fname,department);
            if(UserBussines.getUser()!= null){
                Enter.reloadUsersList();
                switch(Enter.getcUser().getRole()){
                    case 1: LoadScenes.load("view/MainMenu.fxml");break;
                    case 2: LoadScenes.load("view/NextLevelWindow.fxml");break;
                    case 3: LoadScenes.load("view/FinalLevelWindow.fxml");break;
                    default: LoadScenes.load("view/ErrorPage.fxml");
                }

            }
        }catch(Exception e){
            log.error("add user fail", e);
        }
    }
}
