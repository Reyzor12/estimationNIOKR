package org.eleron.lris.niokr.controller;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.sun.javafx.scene.control.behavior.ChoiceBoxBehavior;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.bussines.Enter;
import org.eleron.lris.niokr.bussines.LoadScenes;
import org.eleron.lris.niokr.bussines.UserBussines;
import org.eleron.lris.niokr.dao.DepartmentDAOImplements;
import org.eleron.lris.niokr.dao.UserDAOImplements;
import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.model.User;
import org.eleron.lris.niokr.util.HibernateUtil;
import org.eleron.lris.niokr.util.ValidationUtil;
import org.hibernate.SessionFactory;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.regex.Pattern;

public class MainNewUser {

    final static Logger log = Logger.getLogger(MainNewUser.class);

    private Label nameLabel;
    private Label snameLabel;
    private Label fnameLabel;
    private Label departmentLabel;

    private User user;

    private List<Department> departments;

    private SessionFactory sessionFactory;

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

    @FXML
    private void initialize() {
        try{
            log.info("start initialize " + this.getClass().getName());
            sessionFactory = HibernateUtil.getSessionFactory();
            DepartmentDAOImplements departmentDAO = new DepartmentDAOImplements();
            departmentDAO.setSessionFactory(sessionFactory);
            departments = departmentDAO.listDepartment();
            ObservableList<Department>  departmentChoose = FXCollections.observableArrayList();
            for (Department department: departments) {
                departmentChoose.add(department);
            }
            departmentField.setItems(departmentChoose);
        } catch (Exception e) {
            log.error("initialize " + this.getClass().getName() + " fail",e);
            e.printStackTrace();
        }

        ValidationUtil.validUserField(nameField);
        ValidationUtil.validUserField(snameField);
        ValidationUtil.validUserField(fnameField);

    }

    public MainNewUser(){}

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
            if(!UserBussines.getUser().equals(null)){

                Enter.setcUser(UserBussines.getUser());
                LoadScenes.load("view/MainMenu.fxml");
            }

        }catch(Exception e){
            log.error("add user fail", e);
            e.printStackTrace();
        }

    }

}
