package org.eleron.lris.niokr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.eleron.lris.niokr.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class MainApp extends Application {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(MainApp.class.getClassLoader().getResource("view/Main.fxml"));
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();

    }
}
