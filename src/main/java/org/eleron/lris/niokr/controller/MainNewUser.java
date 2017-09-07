package org.eleron.lris.niokr.controller;

import com.sun.javafx.scene.control.behavior.ChoiceBoxBehavior;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.dao.DepartmentDAOImplements;
import org.eleron.lris.niokr.dao.UserDAOImplements;
import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.model.User;
import org.eleron.lris.niokr.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.regex.Pattern;

public class MainNewUser {

    final static Logger log = Logger.getLogger(MainNewUser.class);

    private Label nameLabel;
    private Label snameLabel;
    private Label fnameLabel;
    private Label departmentLabel;

    private String pattern_name;

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

    private void validField(TextField textField){

        pattern_name = "[А-Я']{1}([а-я']){0,30}";
        Pattern validValue = Pattern.compile(pattern_name);
        textField.textProperty().addListener(
                ((observable, oldValue, newValue) ->
                {
                    if(validValue.matcher(newValue).matches()||newValue.isEmpty()){
                        ((StringProperty)observable).setValue(newValue);
                    } else {
                        ((StringProperty)observable).setValue(oldValue);
                    }
                }
                )
        );
    }
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

    }

    public MainNewUser(){}

    @FXML
    public void addUser(){
        try{
            log.info("add new user name-" +nameField.getText() + " sname-" + snameField.getText()
                    + " fname-" + fnameField.getText());
            user = new User();
            user.setName(nameField.getText());
            user.setSname(snameField.getText());
            user.setFname(fnameField.getText());

            DepartmentDAOImplements departmentDAO = new DepartmentDAOImplements();
            departmentDAO.setSessionFactory(sessionFactory);

//        user.setDepartment(departmentDAO.getDepartmentById(1L));
            user.setDepartment(departmentField.getValue());

            UserDAOImplements userDAO = new UserDAOImplements();
            userDAO.setSessionFactory(sessionFactory);

            System.out.println("id user = " + user.getId());
            userDAO.addUser(user);
            log.info("user add successful");
        }catch(Exception e){
            log.error("add user fail", e);
            e.printStackTrace();
        }

    }

}
