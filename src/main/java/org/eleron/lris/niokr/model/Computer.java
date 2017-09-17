package org.eleron.lris.niokr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="computer")
public class Computer extends Model {

    @NotNull
    @Column(name="name")
    private String name;

    private Set<User> users = new HashSet<User>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Computer() {

        super();
    }

    public Computer(Long id){

        super(id);
    }
}
