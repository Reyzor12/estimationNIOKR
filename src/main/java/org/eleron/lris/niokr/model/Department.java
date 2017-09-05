package org.eleron.lris.niokr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="department")
public class Department extends Model{

    @Column(name="name")
    @NotNull(message="Class: Department field: name type: String message: Couldn't be null")
    private String name;

    private List<User> users = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Department() {
        super();
    }

    public Department(Long id){
        super(id);
    }

}
