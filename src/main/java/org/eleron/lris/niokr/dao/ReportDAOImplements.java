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
        log.info("add report");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.merge(report);
            transaction.commit();
            log.info("add report successful");
        }catch(Exception e){
            log.error("fail to add report",e);
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public void deleteReport(Long id) {
        log.info("delete report");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Report report = (Report) session.load(Report.class,id);
            if(report != null){
                session.delete(report);
            }
            transaction.commit();
            log.info("delete report complete");
        }catch(Exception e){
            log.error("fail delete user",e);
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public void updateReport(Report report) {
        log.info("update report");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(report);
            transaction.commit();
            log.info("update report successful");
        }catch(Exception e){
            log.error("update report fail");
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public List<Report> listReport() {
        log.info("get list of reports");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Report> reports=null;
        try{
            transaction = session.beginTransaction();
            reports = session.createQuery("from Report").list();
            transaction.commit();
            log.info("have list of reports");
        }catch(Exception e){
            log.error("fail to get list",e);
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return reports;
    }

    @Override
    public Report getReport(Long id) {
        log.info("get report");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Report report = null;
        try{
            transaction = session.beginTransaction();
            report = (Report) session.load(Report.class,id);
            transaction.commit();
            log.info("get report successful");
        }catch(Exception e){
            log.error("fail get report",e);
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return report;
    }

    @Override
    public List<Report> listDepartmentReport(Department department) {
        log.info("get list of department reports ");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Report> reports=null;
        try{
            transaction = session.beginTransaction();
            reports = session.createQuery("from Report where department = :dept")
                    .setParameter("dept",department)
                    .list();
            transaction.commit();
            log.info("have list of department reports");
        }catch(Exception e){
            log.error("fail to get department list",e);
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return reports;
    }
}
