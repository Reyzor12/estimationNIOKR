package org.eleron.lris.niokr.bussines;

import org.apache.log4j.Logger;
import org.eleron.lris.niokr.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;

public class TimeManager {

    /*
    * Logger
    * */

    private static final Logger log = Logger.getLogger(TimeManager.class);

    /*
    * Static fields
    * */

    private static Date date;

    /*
    * Getters Setters methods
    * */

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        date = date;
    }

    /*
    * Bussiness Functions
    * */

    public static Date currentDate(){

        log.info("get date");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            date = (Date)session.createSQLQuery("SELECT GETDATE()").list().get(0);
            transaction.commit();
        } catch(Exception e){
            log.error("fail get date ", e);
            transaction.rollback();
        }finally{
            if(session.isOpen()) session.close();
        }
        return date;
    }
}
