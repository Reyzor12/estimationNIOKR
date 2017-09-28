package org.eleron.lris.niokr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="date_of_reports")
public class DateOfReports extends Model{

    @Column(name="year")
    private Integer year;

    public DateOfReports(){super();}
    public DateOfReports(Long id){super(id);}

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
