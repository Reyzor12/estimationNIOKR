package org.eleron.lris.niokr.dao;

import org.apache.log4j.Logger;
import org.eleron.lris.niokr.bussines.Enter;
import org.eleron.lris.niokr.model.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class MessageDAOImplements implements MessageDAO{

    private static final Logger log = Logger.getLogger(MessageDAOImplements.class);

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addMessage(Message message) {

        log.info("add message user:" + Enter.getcUser());
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(message);
            transaction.commit();
            log.info("message add");
        }catch(Exception e) {

            transaction.rollback();
            log.error("Add new message user: " + Enter.getcUser() + " fail",e);
        }finally {
            session.close();
        }

    }

    @Override
    public void removeMessage(Long id) {


        log.info("remove message user:" + Enter.getcUser());
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {

            Message message = (Message)session.load(Message.class,id);
            if(message != null) {

                session.delete(message);
            }

            transaction.commit();
            log.info("message delete");
        }catch(Exception e) {

            transaction.rollback();
            log.error("Remove new message user: " + Enter.getcUser() + " fail",e);
        }finally {
            session.close();
        }
    }

    @Override
    public void updateMessage(Message message) {


        log.info("update message user:" + Enter.getcUser());
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {

            session.update(message);
            transaction.commit();
            log.info("message update ");
        }catch(Exception e) {

            transaction.rollback();
            log.error("Update new message user: " + Enter.getcUser() + " fail",e);
        }finally {
            session.close();
        }
    }

    @Override
    public List<Message> listMessage() {

        log.info("list message user:" + Enter.getcUser());
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Message> messages = null;
        try {

            messages=session.createQuery("from Message").list();
            transaction.commit();
            log.info("list of messagw");
        }catch(Exception e) {

            transaction.rollback();
            log.error("List new message user: " + Enter.getcUser() + " fail",e);
        }finally {
            session.close();
        }
        return messages;
    }
}
