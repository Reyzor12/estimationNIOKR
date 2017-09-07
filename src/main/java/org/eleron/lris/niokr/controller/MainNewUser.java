package org.eleron.lris.niokr.controller;

import com.sun.javafx.scene.control.behavior.ChoiceBoxBehavior;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.eleron.lris.niokr.dao.DepartmentDAOImplements;
import org.eleron.lris.niokr.dao.UserDAOImplements;
import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.model.User;
import org.eleron.lris.niokr.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;

public class MainNewUser {

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

        sessionFactory = HibernateUtil.getSessionFactory();
        DepartmentDAOImplements departmentDAO = new DepartmentDAOImplements();
        departmentDAO.setSessionFactory(sessionFactory);
        departments = departmentDAO.listDepartment();
        ObservableList<Department>  departmentChoose = FXCollections.observableArrayList();
        for (Department department: departments) {
            departmentChoose.add(department);
        }
        departmentField.setItems(departmentChoose);
    }

    public MainNewUser(){}

    @FXML
    public void addUser(){

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
    }

}
