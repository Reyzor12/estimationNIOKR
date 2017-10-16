package org.eleron.lris.niokr.bussines;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.eleron.lris.niokr.model.User;

public class Person {

    private User user;
    private BooleanProperty check = new SimpleBooleanProperty(false);

    private String name;
    private String sname;
    private String fname;

    Person(){}
    Person(User user,Boolean check){

        this.user=user;
        this.check.setValue(check);
        this.name=user.getName();
        this.sname=user.getSname();
        this.fname=user.getFname();

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isCheck() {
        return check.get();
    }

    public BooleanProperty checkProperty() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check.set(check);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public boolean equals(Object object){

        if(object instanceof Person){
            Person per = (Person) object;
            if(per.getUser().equals(getUser())){
                return true;
            } else {
                return false;
            }
        } else {
            if(object instanceof User){
                User user = (User) object;
                if(user.equals(getUser())){
                    return true;
                }
            }
        }
        return false;
    }
}
