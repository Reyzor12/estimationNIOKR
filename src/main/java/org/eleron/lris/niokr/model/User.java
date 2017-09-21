package org.eleron.lris.niokr.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="users", uniqueConstraints = {
        @UniqueConstraint(columnNames={"name","sname","fname","department"})})
public class User extends Model {

    @NotNull(message="Не задано имя пользователя")
    @Size(max=100, message="Имя прользователя не может привышать 100 знаков")
    @Column(name="name" , nullable=false)
    private String name;

    @NotNull(message="Не задана фамилия пользователя")
    @Size(max=100, message="Фамилия прользователя не может привышать 100 знаков")
    @Column(name="sname", nullable=false)
    private String sname;

    @NotNull(message="Не задано отчество пользователя")
    @Size(max=100, message="Отчество прользователя не может привышать 100 знаков")
    @Column(name="fname",nullable=false)
    private String fname;

    @NotNull(message="Не задано подрзделение пользователя")
    @ManyToOne(fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="department",referencedColumnName="id")
    private Department department;

    @NotNull(message="Имя компьютера не может быть пустым")
    @Column(name="computer",nullable = false)
    private String computer;

    @OneToMany(mappedBy="user")
    private List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getComputer() {
        return computer;
    }

    public void setComputer(String computer) {
        this.computer = computer;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User(){
        super();
        this.computer=System.getenv("USERNAME");
    }

    public User(Long id){

        super(id);
        this.computer=System.getenv("USERNAME");
    }

    public String toString(){
        return this.getName() + " " + this.getSname() + " " + this.getFname();
    }
}
