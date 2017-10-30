package org.eleron.lris.niokr.bussines;

import org.apache.log4j.Logger;
import org.eleron.lris.niokr.dao.UserDAO;
import org.eleron.lris.niokr.dao.UserDAOImplements;
import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.model.Report;
import org.eleron.lris.niokr.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class Enter {

    /*
    * Logger
    * */

    private final static Logger log = Logger.getLogger(Enter.class);

    /*
    * Static fields
    * */

    private static String computer;

    private static List<User> users;

    private static User cUser;

    private static List<Integer> dateOfReports;

    private static SessionFactory sessionFactory;

    private static Report consideredReport;

    /*
    * Getterd Setters methods
    * */

    public static Report getConsideredReport() {
        return consideredReport;
    }
    public static void setConsideredReport(Report consideredReport) {
        Enter.consideredReport = consideredReport;
    }

    public static String getComputer() { return computer; }
    public static void setComputer(String computer) { Enter.computer = computer; }

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

    /*
    * Bussiness methods
    * */

    public static void reloadUsersList(){

        log.info("Reload userlist");
        UserDAO userDao = new UserDAOImplements();
        if(cUser != null) users = userDao.getUserByDepartment(cUser.getDepartment());
    }

    public static List<User> welcome(){

        log.info("Начало проверки юзеров на данном компьютере");
        computer = System.getenv("USERNAME");
        UserDAO userDao = new UserDAOImplements();
        users = userDao.getUserByComputer(computer);
        if(users.isEmpty()){
            LoadScenes.load("view/MainNewUser.fxml");
        } else{
            cUser = users.get(0);
            users = userDao.getUserByDepartment(cUser.getDepartment());
            LoadScenes.load("view/MainUserList.fxml");
            }
        return users;
    }

    public static List<User> getAnotherUsers(){

        UserDAO userDao = new UserDAOImplements();
        User user = getcUser();
        List<User> users =  userDao.getAnotherUsers(user.getDepartment(),user.getId());
        return users;
    }

    public static List<User> getAnotherUsers(User user){

        UserDAO userDao = new UserDAOImplements();
        List<User> users = userDao.getAnotherUsers(user.getDepartment(),user.getId());
        return users;
    }
}
