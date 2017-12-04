package org.eleron.lris.niokr.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.bussines.Enter;
import org.eleron.lris.niokr.bussines.LoadScenes;
import org.eleron.lris.niokr.bussines.TimeManager;
import org.eleron.lris.niokr.dao.MessageDAOImplements;
import org.eleron.lris.niokr.model.Message;
import org.eleron.lris.niokr.util.AlertUtil;

public class ReviewMenu {

    /*
    * Logger
    * */

    private static final Logger log = Logger.getLogger(ReviewMenu.class);

    /*
    * FXML Fields
    * */

    @FXML
    private Button sendBtn;

    @FXML
    private Button addNewUserBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TextArea message;

    /*
    * FXML Methods
    * */

    @FXML
    private void initialize(){

    }

    @FXML
    public void backToMainMenu(){
        switch(Enter.getcUser().getRole()){
            case 1: LoadScenes.load("view/MainMenu.fxml");break;
            case 3: LoadScenes.load("view/FinalLevelWindow.fxml");break;
            default: AlertUtil.getAlert("Сбились пользовательские настройки! Обратитесь к разработчику программы!");break;
        }

    }

    @FXML
    public void newUser(){
        LoadScenes.load("view/NewUser.fxml");
    }

    @FXML
    public void addMessage(){

        log.info("Try add message");
        String msg = message.getText();
        if(msg==""){
            log.info("null message");
            AlertUtil.getInformation("Сообщение не может быть пустым");
        } else{
            MessageDAOImplements messageDAO = new MessageDAOImplements();
            messageDAO.addMessage(new Message(Enter.getcUser(),msg,TimeManager.currentDate()));
            message.clear();
            AlertUtil.getInformation("Сообщение отправлено");
        }
    }
}
