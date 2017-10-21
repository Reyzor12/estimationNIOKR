package org.eleron.lris.niokr.dao;

import org.apache.log4j.Logger;
import org.eleron.lris.niokr.model.User;
import org.eleron.lris.niokr.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAOImplements implements UserDAO{

    final static Logger log = Logger.getLogger(UserDAOImplements.class);

    @Override
    public void addUser(User user) {
        log.info("add " + user + " start");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            log.info("add " + user + " successful");
        } catch(Exception e) {
            log.error("add " + user + " fail",e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally{
            session.close();
        }
    }

    @Override
    public void updateUser(User user) {
        log.info("update " + user + " start");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            log.info("update " + user + " successful");
        }catch(Exception e){
            log.error("update " + user + " fail",e);
            if (transaction != null) {
                transaction.rollback();
            }
        }finally{
            session.close();
        }
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("delete " + this.getClass() + " " + id + " start");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            User user = (User)session.load(this.getClass(),id);
            if(user != null){
                session.delete(user);
            }
            transaction.commit();
            log.info("delete " + this.getClass() + " " + id + " successful");
        } catch(Exception e) {
            log.error("delete " + this.getClass() + " " + id + " fail",e);
            if (transaction != null) {
                transaction.rollback();
            }
        }finally{
            session.close();
        }
    }

    @Override
    public List<User> listUser() {
        log.info("list " + this.getClass() + " start");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<User> users = null;
        try{
            transaction = session.beginTransaction();
            users = session.createQuery("from User").list();
            transaction.commit();
            log.info("list " + this.getClass() + " successful");
        }catch(Exception e){
            log.error("list " + this.getClass() + " fail",e);
            if (transaction != null) {
                transaction.rollback();
            }
        }finally{
            session.close();
        }
        return users;
    }

    @Override
    public User getUserById(Long id) {
        log.info("get " + this.getClass() + " id " + id + " start");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        User user = null;
        try{
            transaction = session.beginTransaction();
            user = (User)session.load(this.getClass(),id);
            transaction.commit();
            log.info("get " + this.getClass() + " id " + id + " successful");
        }catch(Exception e){
            log.error("get " + this.getClass() + " id " + id + " fail",e);
            if (transaction != null) {
                transaction.rollback();
            }
        }finally{
            session.close();
        }
        return user;
    }

    @Override
    public List<User> getAnotherUserWithInDepartment(Long id) {
        log.info("get user list of one department except one user id=" + id);
        if(id == null) return null;
        List<User> users = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            User user = (User)session.get(this.getClass(),id);
            users = (List<User>)session.createQuery("From User where department=:dept and id != :id")
                    .setParameter("dept",user.getDepartment())
                    .setParameter("id",id)
                    .list();
            transaction.commit();
            log.info("get user list successful");
        }catch(Exception e){
            log.error("fail get user list of one department",e);
            if (transaction != null) {
                transaction.rollback();
            }
        }finally{
            session.close();
        }
        return users;
    }
}
