package org.eleron.lris.niokr.bussines;

import org.apache.log4j.Logger;
import org.eleron.lris.niokr.dao.UserDAO;
import org.eleron.lris.niokr.dao.UserDAOImplements;
import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.model.User;
import org.eleron.lris.niokr.util.AlertUtil;
import org.eleron.lris.niokr.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class UserBussines {

    /*
    * Logger
    * */

    final static Logger log = Logger.getLogger(UserBussines.class);

    /*
    * Static fields
    * */

    private static User user;

    /*
    * Getters Setters Methods
    * */

    public static User getUser() {
        return user;
    }
    public static void setUser(User user) {
        UserBussines.user = user;
    }

    public UserBussines(){}

    /*
    * Bussiness function
    * */

    public static void formedUser(String name, String sname, String fname, Department department){

       if( !( name.equals("")||
               sname.equals("")||
               fname.equals("")||
               (department == null)
              )
           ) {
            user = new User(name,sname,fname,department,1);
            if(ValidationUtil.valiationUniqueUser(user)){
                UserDAO userDAO = new UserDAOImplements();
                userDAO.addUser(user);
                user = userDAO.getUsersByParameters(user).get(0);
            }else {
                user=null;
                AlertUtil.getAlert("Такой пользователь уже сушествует!");
            }
        } else {
            user=null;
            AlertUtil.getAlert("Не все поля были заполнены!");
        }
    }

    public static List<Person> toPerson(List<User> users){

        List<Person> persons = new ArrayList<Person>();
        if(users == null)return persons;
        for(User user:users){
            Person person = new Person(user,false);
            persons.add(person);
        }
        return persons;
    }
}
