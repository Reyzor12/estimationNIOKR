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
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class ReviewMenu {

    private static final Logger log = Logger.getLogger(ReviewMenu.class);

    @FXML
    private Button sendBtn;

    @FXML
    private Button addNewUserBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TextArea message;

    private void initialize(){

    }

    @FXML
    public void backToMainMenu(){

        LoadScenes.load("view/MainMenu.fxml");
    }

    @FXML
    public void newUser(){

        LoadScenes.load("view/NewUser.fxml");
    }

    @FXML
    public void addMessage(){

        log.info("Try add message");
        String msg = message.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        if(msg==""){

            log.info("null message");

            alert.setTitle("Предупреждение");
            alert.setContentText("Сообщение не может быть пустым");
            alert.show();
        } else{
            Message messageTo = new Message();
            messageTo.setMessage(msg);
            messageTo.setUser(Enter.getcUser());

            try{
                messageTo.setDate(TimeManager.currentDate());
                MessageDAOImplements messageDAO = new MessageDAOImplements();
                messageDAO.setSessionFactory(Enter.getSessionFactory());
                messageDAO.addMessage(messageTo);
                message.clear();
                alert.setTitle("Информация");
                alert.setContentText("Сообщение отправлено");
                alert.show();
            }catch(Exception e){
                log.error("Can't add message",e);
            }
        }
    }
}
