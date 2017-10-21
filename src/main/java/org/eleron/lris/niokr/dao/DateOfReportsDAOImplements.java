package org.eleron.lris.niokr.dao;

import org.apache.log4j.Logger;
import org.eleron.lris.niokr.model.DateOfReports;
import org.eleron.lris.niokr.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DateOfReportsDAOImplements implements DateOfReportsDAO {

    private static final Logger log = Logger.getLogger(DateOfReportsDAOImplements.class);

    @Override
    public void addDateOfReports(DateOfReports dateOfReports) {
        log.info("add dateOfReport");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.merge(dateOfReports);
            transaction.commit();
            log.info("add dateOfReport successful");
        }catch(Exception e){
            log.error("fail add dateOfReport",e);
            if(transaction != null) {
                transaction.rollback();
            }
        }finally{
            session.close();
        }
    }

    @Override
    public void updateDateOfReports(DateOfReports dateOfReports) {
        log.info("update dateOfReport");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(dateOfReports);
            transaction.commit();
            log.info("update dateOfReport successful");
        }catch(Exception e){
            log.error("fail update dareOfReport",e);
            if(transaction != null){
                transaction.rollback();
            }
        }finally{
            session.close();
        }
    }

    @Override
    public List<DateOfReports> listDateOfReports() {
        log.info("get list dateOfReport");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<DateOfReports> list = null;
        try{
            transaction = session.beginTransaction();
            list = (List<DateOfReports>) session.createQuery("from DateOfReports").list();
            transaction.commit();
            log.info("get list dateOfReports successful");
        }catch(Exception e){
            log.error("fail get list DateOfReports",e);
            if(transaction != null){
                transaction.rollback();
            }
        }finally{
            session.close();
        }
        return list;
    }

    @Override
    public DateOfReports getDateOfReports(Long id) {

        log.info("get dateOfReport");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        DateOfReports dateOfReports=null;
        try{
            transaction = session.beginTransaction();
            dateOfReports = (DateOfReports) session.load(DateOfReports.class,id);
            transaction.commit();
            log.info("get DateOfReports successful");
        }catch(Exception e){
            log.error("fail get DateOfReports",e);
            if(transaction != null){
                transaction.rollback();
            }
        }finally{
            session.close();
        }
        return dateOfReports;
    }

    @Override
    public void removeDateOfReports(Long id) {
        log.info("remove dateOfReport");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            DateOfReports dateOfReports = (DateOfReports) session.load(DateOfReports.class,id);
            if(dateOfReports != null){
                session.delete(dateOfReports);
            }
            transaction.commit();
            log.info("remove dateOfReports successful");
        }catch(Exception e){
            log.error("fail remove dateOfReports",e);
            if(transaction != null){
                transaction.rollback();
            }
        }finally{
            session.close();
        }
    }

    @Override
    public List<Integer> getDates() {
        log.info("getDates dateOfReport");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Integer> dates = null;
        try{
            transaction = session.beginTransaction();
            dates = (List<Integer>)session.createSQLQuery("select year from date_of_reports").list();
            transaction.commit();
            log.info("getDates dateOfReports successful");
        }catch(Exception e){
            log.error("fail getDates dateOfReports",e);
            if(transaction != null){
                transaction.rollback();
            }
        }finally{
            session.close();
        }
        return dates;
    }
}
