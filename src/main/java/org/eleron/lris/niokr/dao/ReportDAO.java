package org.eleron.lris.niokr.dao;

import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.model.Report;

import java.util.List;

public interface ReportDAO {

    public void addReport(Report report);
    public void deleteReport(Long id);
    public void updateReport(Report report);
    public List<Report> listReport();
    public Report getReport(Long id);
    public List<Report> listDepartmentReport(Department department);
}
