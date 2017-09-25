package org.eleron.lris.niokr.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="department")
public class Department extends Model{

    @Column(name="name",unique=true)
    @NotNull(message="Выбирите отдел!")
    private String name;

    @OneToMany(mappedBy = "department")
    private Set<User> users = new HashSet<User>();

    @OneToMany(mappedBy = "department")
    private List<Report> reports;

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

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

    public Department() {
        super();
    }

    public Department(Long id){
        super(id);
    }

    public String toString(){
        return this.name;
    }

}
