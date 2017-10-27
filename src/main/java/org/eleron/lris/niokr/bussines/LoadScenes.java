package org.eleron.lris.niokr.bussines;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.MainApp;

import java.io.IOException;

public class LoadScenes {

    /*
    * Logger
    * */

    private static final Logger log = Logger.getLogger(LoadScenes.class);

    /*
    * Static fields
    * */

    private static Stage primaryStage;

    /*
    * Getters Setters methods
    * */

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        LoadScenes.primaryStage = primaryStage;
    }

    /*
    * Bussiness methods
    * */

    public static void load(String path){

        Parent root = null;
        try {
            root = FXMLLoader.load(MainApp.class.getClassLoader().getResource(path));
        } catch (IOException e) {
            log.error("Сцена " + path + " не была загружена",e);
        }
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();
        log.info("Сцена " + path + " загружена");
    }
}
