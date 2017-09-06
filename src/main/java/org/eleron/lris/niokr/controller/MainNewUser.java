package org.eleron.lris.niokr.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.eleron.lris.niokr.dao.UserDAOImplements;
import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.model.User;
import org.eleron.lris.niokr.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class MainNewUser {

    private Label nameLabel;
    private Label snameLabel;
    private Label fnameLabel;
    private Label departmentLabel;

    private User user;

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
    private void initialize() {}

    public MainNewUser(){}

    @FXML
    public void addUser(){
        user = new User();
        user.setName(nameField.getText());
        user.setSname(snameField.getText());
        user.setFname(fnameField.getText());
        user.setDepartment(departmentField.getValue());

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        UserDAOImplements userDAO = new UserDAOImplements();
        userDAO.setSessionFactory(sessionFactory);
        userDAO.addUser(user);
        sessionFactory.close();
    }

}
