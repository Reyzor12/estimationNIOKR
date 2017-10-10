package org.eleron.lris.niokr.dao;

import org.eleron.lris.niokr.model.DateOfReports;

import java.util.List;

public interface DateOfReportsDAO {

    public void addDateOfReports(DateOfReports dateOfReports);
    public void updateDateOfReports(DateOfReports dateOfReports);
    public List<DateOfReports> listDateOfReports();
    public DateOfReports getDateOfReports(Long id);
    public void removeDateOfReports(Long id);
    List<Integer> getDates();
}
