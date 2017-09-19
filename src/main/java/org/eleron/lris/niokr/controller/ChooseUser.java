package org.eleron.lris.niokr.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.eleron.lris.niokr.bussines.Enter;
import org.eleron.lris.niokr.model.User;

public class ChooseUser {

    @FXML
    private ListView<User> users;

    @FXML
    private Button chooseBtn;

    @FXML
    public void initialize(){

        ObservableList<User> observationUserList = FXCollections.observableArrayList();
        for (User user: Enter.getUsers()) {
            observationUserList.add(user);
        }
        users.setItems(observationUserList);
    }

    @FXML
    public void enter(){

        System.out.println("1");
    }
}
