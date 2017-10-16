package org.eleron.lris.niokr.model;

import org.apache.log4j.Logger;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reports")
public class Report extends Model{

    private static final Logger log = Logger.getLogger(Report.class);

    @NotNull(message="Имя не сожет быть пустым")
    @Column(name="short_name", nullable = false)
    private String nameShort;

    @NotNull(message="Полное имя не может быть пустым")
    @Column(name="full_name", nullable = false)
    private String nameLong;

    @Column(name="text")
    private String text;

    @Column(name="trouble")
    private String trouble;

    @Column(name="year")
    private Integer persentOfYear;

    @Column(name="month")
    private Integer persentOfMonth;


    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinTable(name="report_user", joinColumns={@JoinColumn(name="report_id")},inverseJoinColumns={@JoinColumn(name="user_id")})
    private List<User> users;

    @NotNull(message = "Данное поле не может бвть пустым")
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="department", referencedColumnName = "id")
    private Department department;

    @NotNull(message="Данное поле должно быть заполнено")
    @Column(name="status", nullable = false)
    private Integer status;

    @NotNull(message="Данное поле должно быть заполнено")
    @Column(name="date", nullable = false)
    private Date date;

    @NotNull(message="Данное поле должно быть заполнено")
    @Column(name="years_start", nullable = false)
    private Integer yearsStart;

    @NotNull(message="Данное поле должно быть заполнено")
    @Column(name="years_end", nullable = false)
    private Integer yearsEnd;

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    public String getNameLong() {
        return nameLong;
    }

    public void setNameLong(String nameLong) {
        this.nameLong = nameLong;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTrouble() {
        return trouble;
    }

    public void setTrouble(String trouble) {
        this.trouble = trouble;
    }

    public Integer getPersentOfYear() {
        return persentOfYear;
    }

    public void setPersentOfYear(Integer persentOfYear) {
        this.persentOfYear = persentOfYear;
    }

    public Integer getPersentOfMonth() {
        return persentOfMonth;
    }

    public void setPersentOfMonth(Integer persentOfMonth) {
        this.persentOfMonth = persentOfMonth;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getYearsStart() {
        return yearsStart;
    }

    public void setYearsStart(Integer yearsStart) {
        this.yearsStart = yearsStart;
    }

    public Integer getYearsEnd() {
        return yearsEnd;
    }

    public void setYearsEnd(Integer yearsEnd) {
        this.yearsEnd = yearsEnd;
    }

    public Report(){
        super();
    }

    public Report(Long id){
        super(id);
    }

}
