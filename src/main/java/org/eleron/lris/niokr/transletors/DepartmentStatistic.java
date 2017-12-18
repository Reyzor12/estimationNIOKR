package org.eleron.lris.niokr.transletors;


import org.eleron.lris.niokr.model.Department;

public class DepartmentStatistic{

    private Department department;
    private Integer completeReports;
    private Integer allReports;

    public DepartmentStatistic(Department department, Integer completeReports, Integer allReports){
        this.department = department;
        this.completeReports = completeReports;
        this.allReports = allReports;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getCompleteReports() {
        return completeReports;
    }

    public void setCompleteReports(Integer completeReports) {
        this.completeReports = completeReports;
    }

    public Integer getAllReports() {
        return allReports;
    }

    public void setAllReports(Integer allReports) {
        this.allReports = allReports;
    }
}
