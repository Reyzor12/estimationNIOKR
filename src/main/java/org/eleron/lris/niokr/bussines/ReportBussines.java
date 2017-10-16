package org.eleron.lris.niokr.bussines;

import org.eleron.lris.niokr.dao.ReportDAO;
import org.eleron.lris.niokr.dao.ReportDAOImplements;
import org.eleron.lris.niokr.model.DateOfReports;
import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.model.Report;
import org.eleron.lris.niokr.model.User;

import java.util.Date;
import java.util.List;

public class ReportBussines {

    public static void saveNewReportDB(String name, String fullName, Integer start, Integer end, List<User> users){
        Report report = null;
        ReportDAO reportDAO = new ReportDAOImplements();
        if(Enter.getConsideredReport()==null){
            report = new Report();
            report.setNameShort(name);
            report.setNameLong(fullName);
            report.setYearsStart(start);
            report.setYearsEnd(end);
            report.setUsers(users);
            report.setDate(new Date());
            report.setStatus(0);
            report.setDepartment(Enter.getcUser().getDepartment());
            reportDAO.addReport(report);
        } else {
            report = Enter.getConsideredReport();
            report.setNameShort(name);
            report.setNameLong(fullName);
            report.setYearsStart(start);
            report.setYearsEnd(end);
            report.setUsers(users);
            report.setDate(new Date());
            report.setDepartment(Enter.getcUser().getDepartment());
            reportDAO.updateReport(report);
            Enter.setConsideredReport(null);
        }
    }

    public static boolean check(String name, String fullName, Integer start, Integer end, List<User> users){
        if(name==""||
           name==null||
           fullName==""||
           fullName==null||
           start==null||
           start==0||
           end==null||
           end==0||
           users.isEmpty()) return false;
        return true;
    }

    public static List<Report> loadReportMain(){
        return new ReportDAOImplements().listDepartmentReport(Enter.getcUser().getDepartment());
    }
}
