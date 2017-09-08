package org.eleron.lris.niokr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class MainApp extends Application {
    final static Logger log = Logger.getLogger(MainApp.class);

    private static SessionFactory sessionFactory;
    public static void main(String[] args) {

        sessionFactory = HibernateUtil.getSessionFactory();
        /*
        DepartmentDAOImplements dao_department = new DepartmentDAOImplements();
        dao_department.setSessionFactory(sessionFactory);
        Department department = new Department();
        department.setName("Name1");
        dao_department.addDepartment(department);
        */
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        log.info("application start");
        Parent root = FXMLLoader.load(MainApp.class.getClassLoader().getResource("view/MainNewUser.fxml"));
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();
        log.info("MainApp.fxml load");
    }

    public void stop(){
        sessionFactory.close();
    }
}
