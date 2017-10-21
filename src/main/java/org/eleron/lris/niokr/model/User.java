package org.eleron.lris.niokr.model;

import javafx.beans.property.BooleanProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="users", uniqueConstraints = {
        @UniqueConstraint(columnNames={"name","sname","fname","department"})})
public class User extends Model {

    /*
    * User Fields
    * */

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

    @NotNull(message="Не задано подразделение пользователя")
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="department",referencedColumnName="id")
    private Department department;

    @NotNull(message="Имя компьютера не может быть пустым")
    @Column(name="computer",nullable = false)
    private String computer;

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL,orphanRemoval=true)
    private List<Message> messages;

    @NotNull(message="Не задана роль пользователя")
    @Column(name="role", nullable = false)
    private Integer role;

    /*
    * Getters Setters methods
    * */

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

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

    /*
    * Constructors
    * */

    public User(){
        super();
        this.computer=System.getenv("USERNAME");
    }

    public User(Long id){

        super(id);
        this.computer=System.getenv("USERNAME");
    }

    public User(String name, String sname, String fname, Department department, Integer role){
        super();
        this.computer = System.getenv("USERNAME");
        this.name = name;
        this.sname = sname;
        this.fname = fname;
        this.department = department;
        this.role = role;
    }

    /*
    * Override methods
    * */

    @Override
    public String toString(){
        return this.getName() + " " + this.getSname() + " " + this.getFname();
    }

    @Override
    public int hashCode(){
        Long id = getId();
        int result = (id != null)? id.hashCode():0;
        result = 31*result + (name != null? name.hashCode():0);
        return result;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof User)) return false;

        User user = (User) o;
        Long id = getId();
        Long oId = user.getId();
        return id != null ? !id.equals(user.getId()): oId != null;
    }

    /*
    * Other methods
    * */

    public String info(){
        final StringBuffer sb = new StringBuffer("User(");
        sb.append("id=").append(getId());
        sb.append(",name=").append(name);
        sb.append(",sname=").append(sname);
        sb.append(",fname=").append(fname);
        sb.append(",computer=").append(computer);
        sb.append(",department=").append(department);
        sb.append(",role=").append(role);
        sb.append(")");
        return sb.toString();
    }
}
