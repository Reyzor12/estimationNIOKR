package org.eleron.lris.niokr.bussines;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;

public class TimeManager {

    private static final Logger log = Logger.getLogger(TimeManager.class);

    private static SessionFactory sessionFactory;

    private static Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        date = date;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        sessionFactory = sessionFactory;
    }


    public static Date currentDate(){

        log.info("get date");
        sessionFactory = Enter.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            date = (Date)session.createSQLQuery("SELECT GETDATE()").list().get(0);
            transaction.commit();
        } catch(Exception e){
            log.error("fail get date ", e);
            transaction.rollback();
        }finally{
            session.close();
        }
        return date;
    }
}
