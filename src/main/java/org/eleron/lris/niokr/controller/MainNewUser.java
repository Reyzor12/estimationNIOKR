package org.eleron.lris.niokr.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.bussines.Enter;
import org.eleron.lris.niokr.bussines.LoadScenes;
import org.eleron.lris.niokr.bussines.UserBussines;
import org.eleron.lris.niokr.dao.DepartmentDAO;
import org.eleron.lris.niokr.dao.DepartmentDAOImplements;
import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.model.User;
import org.eleron.lris.niokr.util.HibernateUtil;
import org.eleron.lris.niokr.util.ValidationUtil;
import org.hibernate.SessionFactory;

import java.util.List;

public class MainNewUser {

    /*
    * Logging
    * */

    final static Logger log = Logger.getLogger(MainNewUser.class);

    /*
    * FXML Fields
    * */

    @FXML
    private Label infoLabel;

    @FXML
    private TextField nameField;
    @FXML
    private TextField snameField;
    @FXML
    private TextField fnameField;
    @FXML
    private ChoiceBox<Department> departmentField;
    @FXML
    private Button saveButton;

    /*
    * Init method
    * */

    @FXML
    private void initialize() {
        try{
            log.info("start initialize " + this.getClass().getName());
            departmentField.setItems(
                    FXCollections.observableArrayList(
                            (new DepartmentDAOImplements())
                                    .listDepartment()
                    )
            );
        } catch (Exception e) {
            log.error("initialize " + this.getClass().getName() + " fail",e);
        }
        ValidationUtil.validUserField(nameField);
        ValidationUtil.validUserField(snameField);
        ValidationUtil.validUserField(fnameField);
    }

    /*
    * FXML Methods
    * */

    @FXML
    public void addUser(){

        String name = nameField.getText();
        String sname = snameField.getText();
        String fname = fnameField.getText();
        Department department = departmentField.getValue();

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
