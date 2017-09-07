package org.eleron.lris.niokr.dao;

import org.apache.log4j.Logger;
import org.eleron.lris.niokr.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAOImplements implements UserDAO{

    final static Logger log = Logger.getLogger(UserDAOImplements.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {

        log.info("add " + user + " start");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.merge(user);
            transaction.commit();
            log.info("add " + user + " successful");
        } catch(Exception e) {
            transaction.rollback();
            log.error("add " + user + " fail",e);
        } finally{
            session.close();
        }
    }

    @Override
    public void updateUser(User user) {

        log.info("update " + user + " start");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(user);
            transaction.commit();
            log.error("update " + user + " successful");
        }catch(Exception e){
            transaction.rollback();
            log.error("update " + user + " fail");
        }finally{
            session.close();
        }
    }

    @Override
    public void deleteUserById(Long id) {

        log.info("delete " + this.getClass() + " " + id + " start");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try{
            User user = (User)session.load(this.getClass(),id);
            if(user != null){
                session.delete(user);
            }
            transaction.commit();
            log.info("delete " + this.getClass() + " " + id + " successful");
        } catch(Exception e) {
            transaction.rollback();
            log.error("delete " + this.getClass() + " " + id + " fail",e);
        }finally{
            session.close();
        }
    }

    @Override
    public List<User> listUser() {

        log.info("list " + this.getClass() + " start");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<User> users = null;
        try{
            users = session.createQuery("from User").list();
            transaction.commit();
            log.info("list " + this.getClass() + " successful");
        }catch(Exception e){
            log.error("list " + this.getClass() + " fail",e);
            transaction.rollback();
        }finally{
            session.close();
        }
        return users;
    }

    @Override
    public User getUserById(Long id) {

        log.info("get " + this.getClass() + " id " + id + " start");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = null;
        try{
            user = (User)session.load(this.getClass(),id);
            transaction.commit();
            log.info("get " + this.getClass() + " id " + id + " successful");
        }catch(Exception e){
            transaction.rollback();
            log.error("get " + this.getClass() + " id " + id + " fail",e);
        }finally{
            session.close();
        }
        return user;
    }
}
