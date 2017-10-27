package org.eleron.lris.niokr.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reports")
public class Report extends Model{

    /*
    *Report Fields
    * */

    @NotNull(message="Сокращенное название НИОКР'а не сожет быть пустым")
    @Column(name="short_name", nullable = false)
    private String nameShort;

    @NotNull(message="Полное название НИОКР'а не может быть пустым")
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

    @NotNull(message="Не задан ответственный за НИОКР")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="owner",referencedColumnName="id")
    private User owner;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="report_user", joinColumns={@JoinColumn(name="report_id")},inverseJoinColumns={@JoinColumn(name="user_id")})
    private List<User> users;

    @NotNull(message = "НИОКР не отнесен ни к одному подразделению")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="department", referencedColumnName = "id")
    private Department department;

    @NotNull(message="Необходимо задать статус НИОКР'а")
    @Column(name="status", nullable = false)
    private Integer status;

    @NotNull(message="Не определена дата создания НИОКР'а")
    @Column(name="date", nullable = false)
    private Date date;

    @NotNull(message="Не определен год начала НИОКР'а")
    @Column(name="years_start", nullable = false)
    private Integer yearsStart;

    @NotNull(message="Не определен год окончания НИОКР'а")
    @Column(name="years_end", nullable = false)
    private Integer yearsEnd;

    /*
    * Getters and Setters methods
    * */

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

    public Integer getYearsStart() { return yearsStart; }

    public void setYearsStart(Integer yearsStart) {
        this.yearsStart = yearsStart;
    }

    public Integer getYearsEnd() {
        return yearsEnd;
    }

    public void setYearsEnd(Integer yearsEnd) {
        this.yearsEnd = yearsEnd;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    /*
    * Report constructors
    * */

    public Report(){
        super();
    }

    public Report(Long id){
        super(id);
    }

    public Report(String nameShort, String nameLong, User owner, Department department, Integer status, Integer yearsStart, Integer yearsEnd, List<User> users){
        super();
        this.date = new Date();
        this.nameShort = nameShort;
        this.nameLong = nameLong;
        this.owner = owner;
        this.department = department;
        this.status = status;
        this.yearsStart = yearsStart;
        this.yearsEnd = yearsEnd;
        this.users = users;
        this.persentOfYear = 0;
    }

    /*
    * Override methods
    * */

    @Override
    public String toString(){
        return this.nameLong;
    }

    @Override
    public int hashCode(){
        Long id = getId();
        int result = (id != null)? id.hashCode():0;
         return 31*result + (this.nameLong != null?nameLong.hashCode():0);
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Report)) return false;

        Report report = (Report) o;
        Long id = this.getId();
        Long oId = report.getId();

        if(id != null? !id.equals(report.getId()): oId != null) return false;
        return nameLong != null? nameLong.equals(report.getNameLong()): report.getNameLong() == null;
    }

    /*
    * Other methods
    * */

    public String info(){
        StringBuffer sb = new StringBuffer("Report(");
        sb.append("id=").append(this.getId());
        sb.append(",shortName=").append(nameShort);
        sb.append(",longName=").append(nameLong);
        sb.append(",date=").append(date);
        sb.append(",owner=").append(owner);
        sb.append(",department=").append(department);
        sb.append(",status=").append(status);
        sb.append(",yearStart=").append(yearsStart);
        sb.append(",yearEnd=").append(yearsEnd);
        sb.append(",persentOfYear=").append(persentOfYear);
        sb.append(",persentOfMonth=").append(persentOfMonth);
        sb.append(",text=").append(text);
        sb.append(",trouble").append(trouble);
        sb.append(")");
        return sb.toString();
    }
}
