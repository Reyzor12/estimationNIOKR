package org.eleron.lris.niokr.util;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import org.eleron.lris.niokr.dao.UserDAO;
import org.eleron.lris.niokr.dao.UserDAOImplements;
import org.eleron.lris.niokr.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ValidationUtil {

    final static Logger log = Logger.getLogger(ValidationUtil.class);

    public static void validate(Object object, Validator validator){

        log.info("validator work for class " + object.getClass().getName());
        Set<ConstraintViolation<Object>> constraintViolation = validator.validate(object);
        StringBuilder errorMessage = new StringBuilder(
                "Уважаемый пользователь!\n" +
                "При заполнении были дорущены следующие ошибки:\n"
        );

        for(ConstraintViolation<Object> cv: constraintViolation){
            errorMessage.append(
                    cv.getMessage() + "\n"
            );
        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка ввода данных");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage.toString());
        alert.showAndWait();
    }

    public static Boolean valiationUniqueUser(User user){

        log.info("user uniq validation start");
        UserDAO userDao = new UserDAOImplements();
        List<User> users =  userDao.getUsersByParameters(user);
            if(!users.isEmpty())
            {
                return false;
            }
        return true;
    }

    public static void validUserField(TextField textField){

        String pattern_name = "[А-Я']{1}([а-я']){0,30}";
        Pattern validValue = Pattern.compile(pattern_name);
        textField.textProperty().addListener(
                ((observable, oldValue, newValue) ->
                {
                    if(validValue.matcher(newValue).matches()||newValue.isEmpty()){
                        ((StringProperty)observable).setValue(newValue);
                    } else {
                        ((StringProperty)observable).setValue(oldValue);
                    }
                }
                )
        );
    }
}
