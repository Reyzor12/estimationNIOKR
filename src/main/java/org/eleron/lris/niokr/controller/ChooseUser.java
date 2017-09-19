package org.eleron.lris.niokr.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.bussines.Enter;
import org.eleron.lris.niokr.bussines.LoadScenes;
import org.eleron.lris.niokr.model.User;

public class ChooseUser {

    private final static Logger log = Logger.getLogger(ChooseUser.class);

    @FXML
    private ListView<User> users;

    @FXML
    private Button chooseBtn;

    @FXML
    public void initialize(){

        log.info("Инициализируем окно");
        ObservableList<User> observationUserList = FXCollections.observableArrayList();
        for (User user: Enter.getUsers()) {
            observationUserList.add(user);
        }
        users.setItems(observationUserList);
        users.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void enter(){

        log.info("Заходим под пользователя " + users.getSelectionModel().getSelectedItem());
        Enter.setcUser(users.getSelectionModel().getSelectedItem());
        LoadScenes.load("view/MainMenu.fxml");
    }
}
