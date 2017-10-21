package org.eleron.lris.niokr.dao;

import org.apache.log4j.Logger;
import org.eleron.lris.niokr.bussines.Enter;
import org.eleron.lris.niokr.model.Message;
import org.eleron.lris.niokr.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MessageDAOImplements implements MessageDAO{

    private static final Logger log = Logger.getLogger(MessageDAOImplements.class);

    @Override
    public void addMessage(Message message) {
        log.info("add message user:" + Enter.getcUser());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(message);
            transaction.commit();
            log.info("message add");
        }catch(Exception e) {
            log.error("Add new message user: " + Enter.getcUser() + " fail",e);
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public void removeMessage(Long id) {
        log.info("remove message user:" + Enter.getcUser());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Message message = (Message)session.load(Message.class,id);
            if(message != null) {
                session.delete(message);
            }
            transaction.commit();
            log.info("message delete");
        }catch(Exception e) {
            log.error("Remove new message user: " + Enter.getcUser() + " fail",e);
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public void updateMessage(Message message) {
        log.info("update message user:" + Enter.getcUser());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(message);
            transaction.commit();
            log.info("message update ");
        }catch(Exception e) {
            log.error("Update new message user: " + Enter.getcUser() + " fail",e);
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public List<Message> listMessage() {
        log.info("list message user:" + Enter.getcUser());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Message> messages = null;
        try {
            transaction = session.beginTransaction();
            messages=session.createQuery("from Message").list();
            transaction.commit();
            log.info("list of messagw");
        }catch(Exception e) {
            log.error("List new message user: " + Enter.getcUser() + " fail",e);
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return messages;
    }
}
