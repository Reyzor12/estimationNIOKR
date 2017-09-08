package org.eleron.lris.niokr.controller;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.sun.javafx.scene.control.behavior.ChoiceBoxBehavior;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.log4j.Logger;
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
        try{
            log.info("add new user name-" +nameField.getText() + " sname-" + snameField.getText()
                    + " fname-" + fnameField.getText());
            user = new User();

//            int result = (1 > 2) ? 1 : 0;
            boolean check = true;
            if(!nameField.getText().equals("")){
                user.setName(nameField.getText());
            } else{ check=false;}
            if(!snameField.getText().equals("")){
                user.setSname(snameField.getText());
            } else{ check=false;}
            if(!fnameField.getText().equals("")){
                user.setFname(fnameField.getText());
            } else{ check=false;}
            if (departmentField.getValue()!=null) {

                DepartmentDAOImplements departmentDAO = new DepartmentDAOImplements();
                departmentDAO.setSessionFactory(sessionFactory);

//        user.setDepartment(departmentDAO.getDepartmentById(1L));
                user.setDepartment(departmentField.getValue());
            } else{
                check=false;
            }

            if(check) {

                if(ValidationUtil.valiationUniqueUser(user)){

                    UserDAOImplements userDAO = new UserDAOImplements();
                    userDAO.setSessionFactory(sessionFactory);
                    userDAO.addUser(user);
                    log.info("user add successful");
                }else {

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Предупреждение");
                    alert.setHeaderText(null);
                    alert.setContentText("Такой пользователь уже сушествует!");
                    alert.show();
                }

            } else {

                ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
                Validator validator = validatorFactory.getValidator();

                ValidationUtil.validate(user,validator);
            }

        }catch(Exception e){
            log.error("add user fail", e);
            e.printStackTrace();
        }

    }

}
