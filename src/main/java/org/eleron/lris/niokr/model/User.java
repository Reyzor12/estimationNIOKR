package org.eleron.lris.niokr.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")
public class User extends Model {

    @NotNull(message="Class: User field: name message: couldn't be null")
    @Size(max=100, message="Class: User field: name message: max size is 100 digits")
    @Column(name="name")
    private String name;

    @NotNull(message="Class: User field: sname message: couldn't be null")
    @Size(max=100, message="Class: User field: sname message: max size is 100 digits")
    @Column(name="sname")
    private String sname;

    @NotNull(message="Class: User field: fname message: couldn't be null")
    @Size(max=100, message="Class: User field: fname message: max size is 100 digits")
    @Column(name="fname")
    private String fname;

    @NotNull(message="Class: User field: department message: couldn't be null")
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="department_id",referencedColumnName="id")
    private Department department;

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User(){
        super();
    }

    public User(Long id){
        super(id);
    }
}
