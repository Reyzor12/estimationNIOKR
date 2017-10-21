package org.eleron.lris.niokr.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="department")
public class Department extends Model{

    /*
    * Department fields
    * */

    @Column(name="name",unique=true)
    @NotNull(message="Выбирите отдел!")
    private String name;

    @OneToMany(mappedBy = "department",cascade=CascadeType.ALL)
    private Set<User> users = new HashSet<User>();

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    private List<Report> reports;

    /*
    * Getters Setters Methods
    * */

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

    /*
    * Constructors
    * */

    public Department() {
        super();
    }

    public Department(Long id){
        super(id);
    }

    public Department(String name){
        super();
        this.name = name;
    }

    /*
    * Override methods
    * */

    @Override
    public String toString(){
        return name;
    }

    @Override
    public int hashCode(){
        Long id = getId();
        int result = id != null ? id.hashCode() : 0;
        result = result*31 + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Department)) return false;

        Department department = (Department) o;
        Long id = getId();
        Long oId = department.getId();
        if(id != null ? !id.equals(oId) : oId != null)return false;
        return name != null ? name.equals(department.getName()) : department.getName() == null;
    }

    /*
    * Other methods
    * */

    public String info(){
        StringBuffer sb = new StringBuffer("Department(");
        sb.append("id=").append(getId());
        sb.append(",name=").append(name);
        sb.append(")");
        return sb.toString();
    }
}
