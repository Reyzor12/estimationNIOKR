package org.eleron.lris.niokr.bussines;

import javafx.scene.control.Alert;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.dao.UserDAOImplements;
import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.model.User;
import org.eleron.lris.niokr.util.HibernateUtil;
import org.eleron.lris.niokr.util.ValidationUtil;
import org.hibernate.SessionFactory;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class UserBussines {

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserBussines.user = user;
    }

    private static User user;

    final static Logger log = Logger.getLogger(UserBussines.class);

    private static SessionFactory sessionFactory;

    public UserBussines(){

    }

    public static void formedUser(String name, String sname, String fname, Department department){

        log.info("try add user to db");

        user = new User();

        user.setName(name);
        user.setSname(sname);
        user.setFname(fname);
        user.setDepartment(department);
        user.setRole(1);

        sessionFactory = HibernateUtil.getSessionFactory();

        boolean check = true;
        if(name.equals("")||sname.equals("")||fname.equals("")||(department ==null)){
            check=false;
        }

        if(check) {

            if(ValidationUtil.valiationUniqueUser(user)){

                UserDAOImplements userDAO = new UserDAOImplements();
                userDAO.setSessionFactory(sessionFactory);
                userDAO.addUser(user);
                log.info("user add successful");
            }else {

                user=null;
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Предупреждение");
                alert.setHeaderText(null);
                alert.setContentText("Такой пользователь уже сушествует!");
                alert.show();
            }

        } else {

            user=null;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setHeaderText(null);
            alert.setContentText("Не все поля были заполнены!");
            alert.show();
        }

    }
}
