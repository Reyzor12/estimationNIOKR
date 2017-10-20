package org.eleron.lris.niokr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.bussines.Enter;
import org.eleron.lris.niokr.bussines.LoadScenes;
import org.eleron.lris.niokr.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class MainApp extends Application {
    final static Logger log = Logger.getLogger(MainApp.class);

    private static SessionFactory sessionFactory;
    public static void main(String[] args) {

        sessionFactory = HibernateUtil.getSessionFactory();
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        log.info("application start");

        Enter.setSessionFactory(sessionFactory);
        LoadScenes.setPrimaryStage(primaryStage);
        Enter.welcome();
        log.info("MainApp.fxml load");
    }

    public void stop(){
        sessionFactory.close();
    }
}
