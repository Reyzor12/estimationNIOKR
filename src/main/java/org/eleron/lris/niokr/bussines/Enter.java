package org.eleron.lris.niokr.bussines;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.MainApp;
import org.eleron.lris.niokr.model.Report;
import org.eleron.lris.niokr.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class Enter {

    private final static Logger log = Logger.getLogger(Enter.class);

    private static String computer;

    private static List<User> users;

    private static User cUser;

    private static List<Integer> dateOfReports;

    private static SessionFactory sessionFactory;

    private static Report consideredReport;

    public static Report getConsideredReport() {
        return consideredReport;
    }

    public static void setConsideredReport(Report consideredReport) {
        Enter.consideredReport = consideredReport;
    }

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

    public static User getcUser() {
        return cUser;
    }

    public static void setcUser(User cUser) {
        Enter.cUser = cUser;
    }

    public static List<Integer> getDateOfReports() {
        return dateOfReports;
    }

    public static void setDateOfReports(List<Integer> dateOfReports) {
        Enter.dateOfReports = dateOfReports;
    }

    public static void reloadUsersList(){
        log.info("Reload userlist");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            users = session.createQuery("from User where department = :dept").setParameter("dept",cUser.getDepartment()).list();
            transaction.commit();
            log.info("get userList for session");
        } catch(Exception e){
            log.error("fail get UserList for this session");
            transaction.rollback();
        }finally{
            session.close();
        }
    }

    public static Set<User> welcome(){

        log.info("Начало проверки юзеров на данном компьютере");
        computer = System.getenv("USERNAME");

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try{


            users = (List<User>)session.createQuery("from User where computer = :comp").setParameter("comp",computer).list();

            if(users.isEmpty()){

                LoadScenes.load("view/MainNewUser.fxml");
            } else{
                /*Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Предупреждение");
                alert.setHeaderText(null);
                alert.setContentText("Уже все пользователи созданы, пора выбирать");
                alert.show();*/
                cUser = users.get(0);

                users = session.createQuery("from User where department = :dept").setParameter("dept",cUser.getDepartment()).list();
                LoadScenes.load("view/MainUserList.fxml");

            }
        }catch (Exception e) {

            log.error("не удалось проинициализировать юзеров на данном компьютере",e);
        }finally{
            session.close();
        }

        return null;
    }
}
