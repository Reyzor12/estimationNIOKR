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

    private static final Logger log = Logger.getLogger(NewUser.class);

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

    @FXML
    private void initialize(){

        ValidationUtil.validUserField(nameField);
        ValidationUtil.validUserField(snameField);
        ValidationUtil.validUserField(fnameField);
    }

    @FXML
    public void goBack(){

        LoadScenes.load("view/MainMenu.fxml");
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

//            int result = (1 > 2) ? 1 : 0;
            UserBussines.formedUser(name,sname,fname,department);
            if(UserBussines.getUser()!= null){

                Enter.setcUser(UserBussines.getUser());
                LoadScenes.load("view/MainMenu.fxml");
            }

        }catch(Exception e){
            log.error("add user fail", e);
            e.printStackTrace();
        }
    }
}
