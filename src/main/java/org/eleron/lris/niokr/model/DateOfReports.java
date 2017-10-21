package org.eleron.lris.niokr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="date_of_reports")
public class DateOfReports extends Model{

    /*
    * DateOfReports fields
    * */

    @NotNull(message = "Не задана дата для объекта")
    @Column(name = "year", nullable = false)
    private Integer year;

    /*
    * Constructors
    * */

    public DateOfReports(){super();}
    public DateOfReports(Long id){super(id);}
    public DateOfReports(Integer year){
        super();
        this.year = year;
    }

    /*
    * Getters Setters Methods
    * */

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    /*
    * Override Methods
    * */

    @Override
    public String toString(){
        return year.toString();
    }

    @Override
    public int hashCode(){
        Long id = getId();
        int result = id != null ? id.hashCode() : 0;
        result = result*31 + (year != null ? year.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o){
        if( this == o) return true;
        if(!(o instanceof DateOfReports)) return false;

        DateOfReports dateOfReports = (DateOfReports) o;
        Long id = getId();
        Long oId = dateOfReports.getId();
        if(id != null ? !id.equals(oId) : oId != null) return false;
        return year != null ? year.equals(dateOfReports.getYear()) : dateOfReports.getYear() == null;
    }

    /*
    * Other methods
    * */

    public String info(){
        StringBuffer sb = new StringBuffer("DateOfReports(");
        sb.append("id=").append(getId());
        sb.append(",year=").append(year);
        sb.append(")");
        return sb.toString();
    }
}
