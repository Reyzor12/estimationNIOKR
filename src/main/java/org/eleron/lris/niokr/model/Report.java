package org.eleron.lris.niokr.model;

import org.apache.log4j.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @NotNull(message="Данное поле должно быть заполнено")
    @Column(name="text", nullable = false)
    private String text;

    @NotNull(message="Данное поле должно быть заполнено")
    @Column(name="trouble",nullable=false)
    private String trouble;

    @Column(name="year")
    private Integer persentOfYear;

    @Column(name="month")
    private Integer persentOfMonth;


    private List<User> users;

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

    public Report(){
        super();
    }

    public Report(Long id){
        super(id);
    }
}
