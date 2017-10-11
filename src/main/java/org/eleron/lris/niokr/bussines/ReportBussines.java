package org.eleron.lris.niokr.bussines;

import org.eleron.lris.niokr.dao.ReportDAO;
import org.eleron.lris.niokr.dao.ReportDAOImplements;
import org.eleron.lris.niokr.model.DateOfReports;
import org.eleron.lris.niokr.model.Report;
import org.eleron.lris.niokr.model.User;

import java.util.List;

public class ReportBussines {

    public static void saveNewReportDB(String name, String fullName, DateOfReports start, DateOfReports end, List<User> users){
        Report report = new Report();
        report.setNameShort(name);
        report.setNameLong(fullName);
        report.setYearsStart(start.getYear());
        report.setYearsEnd(end.getYear());
        report.setUsers(users);
        ReportDAO reportDAO = new ReportDAOImplements();
        reportDAO.addReport(report);
    }

    public static boolean check(String name, String fullName, DateOfReports start, DateOfReports end, List<User> users){
        if(name==""&&
           name==null&&
           fullName==""&&
           fullName==null&&
           start.getYear()==null&&
           start.getYear()==0&&
           end.getYear()==null&&
           end.getYear()==0&&
           users.isEmpty()) return false;
        return true;
    }
}
