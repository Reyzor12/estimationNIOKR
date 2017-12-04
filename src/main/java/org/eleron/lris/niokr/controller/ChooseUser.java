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
import org.eleron.lris.niokr.util.AlertUtil;

public class ChooseUser {

    /*
    * Logger
    * */

    private final static Logger log = Logger.getLogger(ChooseUser.class);

    /*
    * Fields FXML
    * */

    @FXML
    private ListView<User> users;

    @FXML
    private Button chooseBtn;

    /*
    * FXML Methods
    * */

    @FXML
    public void initialize(){

        log.info("Инициализируем окно");
        ObservableList<User> observationUserList = FXCollections.observableArrayList(Enter.getUsers());
        users.setItems(observationUserList);
        users.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        users.getSelectionModel().selectFirst();
    }

    @FXML
    public void enter(){
        if(users.getSelectionModel().getSelectedItem()!=null){
            log.info("Заходим под пользователя " + users.getSelectionModel().getSelectedItem());
            Enter.setcUser(users.getSelectionModel().getSelectedItem());
            switch(Enter.getcUser().getRole()) {
                case 1:LoadScenes.load("view/MainMenu.fxml");break;
                case 2:LoadScenes.load("view/NextLevelWindow.fxml");break;
                case 3:LoadScenes.load("view/FinalLevelWindow.fxml");break;
                default: AlertUtil.getAlert("Для данного пользователя задана неправомерная роль");
            }
        } else {
            AlertUtil.getInformation("Пользователь не выбран");
        }
    }
}
