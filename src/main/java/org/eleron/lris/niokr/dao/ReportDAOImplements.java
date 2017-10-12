package org.eleron.lris.niokr.dao;

import org.apache.log4j.Logger;
import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.model.Report;
import org.eleron.lris.niokr.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReportDAOImplements implements ReportDAO{

    Logger log = Logger.getLogger(ReportDAOImplements.class);

    @Override
    public void addReport(Report report) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            log.info("add report");
            session.merge(report);
            transaction.commit();
            log.info("add report successful");

        }catch(Exception e){
            transaction.rollback();
            log.error("fail to add report",e);

        }finally {
            session.close();
        }

    }

    @Override
    public void deleteReport(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            log.info("delete report");
            Report report = (Report) session.load(Report.class,id);
            if(report!=null){
                session.delete(report);
            }
            transaction.commit();
            log.info("delete report complete");

        }catch(Exception e){
            transaction.rollback();
            log.error("fail delete user",e);

        }finally {
            session.close();
        }


    }

    @Override
    public void updateReport(Report report) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            log.info("update report");
            session.update(report);
            transaction.commit();
            log.info("update report successful");
        }catch(Exception e){
            transaction.rollback();
            log.error("update report fail");

        }finally {
            session.close();
        }
    }

    @Override
    public List<Report> listReport() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Report> reports=null;
        try{
            log.info("get list of reports");
            reports = session.createQuery("from Report").list();
            transaction.commit();
            log.info("have list of reports");
        }catch(Exception e){
            transaction.rollback();
            log.error("fail to get list",e);
        }finally {
            session.close();
        }
        return reports;
    }

    @Override
    public Report getReport(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Report report = null;
        try{
            log.info("get report");
            report = (Report) session.load(Report.class,id);
            transaction.commit();
            log.info("get report successful");
        }catch(Exception e){
            transaction.rollback();
            log.error("fail get report",e);
        }finally {
            session.close();
        }
        return report;
    }

    @Override
    public List<Report> listDepartmentReport(Department department) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Report> reports=null;
        try{
            log.info("get list of department reports ");
            reports = session.createQuery("from Report where department = :dept")
                    .setParameter("dept",department)
                    .list();
            transaction.commit();
            log.info("have list of department reports");
        }catch(Exception e){
            transaction.rollback();
            log.error("fail to get department list",e);
        }finally {
            session.close();
        }
        return reports;
    }
}
