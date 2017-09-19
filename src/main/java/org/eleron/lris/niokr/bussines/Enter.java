package org.eleron.lris.niokr.bussines;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.MainApp;
import org.eleron.lris.niokr.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class Enter {

    private final static Logger log = Logger.getLogger(Enter.class);

    private static Stage primaryStage;

    private static String computer;

    private static List<User> users;

    private static SessionFactory sessionFactory;

    public static String getComputer() {
        return computer;
    }

    public static void setComputer(String computer) {
        Enter.computer = computer;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        Enter.sessionFactory = sessionFactory;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        Enter.users = users;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public  static void setPrimaryStage(Stage primaryStage) {
        Enter.primaryStage = primaryStage;
    }

    public static Set<User> welcome(){

        log.info("Начало проверки юзеров на данном компьютере");
        computer = System.getenv("USERNAME");

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try{

            users = session.createQuery("from User where computer = :comp").setParameter("comp",computer).list();
            if(users.isEmpty()){
                Parent root = FXMLLoader.load(MainApp.class.getClassLoader().getResource("view/MainNewUser.fxml"));
                primaryStage.setScene(new Scene(root,800,600));
                primaryStage.show();

            } else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Предупреждение");
                alert.setHeaderText(null);
                alert.setContentText("Уже все пользователи созданы, пора выбирать");
                alert.show();

            }
        }catch (Exception e) {

            log.error("не удалось проинициализировать юзеров на данном компьютере",e);
        }finally{
            session.close();
        }

        return null;
    }
}
