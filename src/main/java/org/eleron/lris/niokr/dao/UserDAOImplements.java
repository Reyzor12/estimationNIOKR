package org.eleron.lris.niokr.dao;

import org.apache.log4j.Logger;
import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.model.User;
import org.eleron.lris.niokr.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAOImplements implements UserDAO {

    final static Logger log = Logger.getLogger(UserDAOImplements.class);

    @Override
    public void addUser(User user) {
        log.info("add " + user + " start");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            log.info("add " + user + " successful");
        } catch (Exception e) {
            log.error("add " + user + " fail", e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void updateUser(User user) {
        log.info("update " + user + " start");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            log.info("update " + user + " successful");
        } catch (Exception e) {
            log.error("update " + user + " fail", e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("delete " + this.getClass() + " " + id + " start");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = (User) session.get(this.getClass(), id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
            log.info("delete " + this.getClass() + " " + id + " successful");
        } catch (Exception e) {
            log.error("delete " + this.getClass() + " " + id + " fail", e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> listUser() {
        log.info("list " + this.getClass() + " start");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<User> users = null;
        try {
            transaction = session.beginTransaction();
            users = session.createQuery("from User").list();
            transaction.commit();
            log.info("list " + this.getClass() + " successful");
        } catch (Exception e) {
            log.error("list " + this.getClass() + " fail", e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
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
        try {
            transaction = session.beginTransaction();
            user = (User) session.load(this.getClass(), id);
            transaction.commit();
            log.info("get " + this.getClass() + " id " + id + " successful");
        } catch (Exception e) {
            log.error("get " + this.getClass() + " id " + id + " fail", e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public List<User> getAnotherUserWithInDepartment(Long id) {
        log.info("get user list of one department except one user id=" + id);
        if (id == null) return null;
        List<User> users = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = (User) session.get(this.getClass(), id);
            users = (List<User>) session.createQuery("From User where department=:dept and id != :id")
                    .setParameter("dept", user.getDepartment())
                    .setParameter("id", id)
                    .list();
            transaction.commit();
            log.info("get user list successful");
        } catch (Exception e) {
            log.error("fail get user list of one department", e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public List<User> getUsersByParameters(User user) {
        log.info("get list of users like user = " + user.toString());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<User> users = null;
        try{
            transaction = session.beginTransaction();
            users = (List<User>)session
                    .createQuery("from User where name = :name and sname = :sname and fname = :fname and department = :department")
                    .setParameter("name",user.getName())
                    .setParameter("sname",user.getSname())
                    .setParameter("fname",user.getFname())
                    .setParameter("department",user.getDepartment())
                    .list();
            transaction.commit();
            log.info("list of user like " + user.toString() + " received");
        }catch(Exception e){
            log.error("fail get list of user like " + user.toString(),e);
            transaction.rollback();
        }finally{
            if(session.isOpen()){
                session.close();
            }
        }
        return users;
    }

    @Override
    public List<User> getUserByDepartment(Department department) {

        log.info("get Users by department =" + department + " start");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<User> users = null;
        try{
            transaction = session.beginTransaction();
            users = (List<User>) session.createQuery("from User where department = :dept").setParameter("dept",department).list();
            transaction.commit();
            log.info("get Users by department = " + department + " complite");
        }catch(Exception e){
            log.error("get Users by department = " + department + " fail",e);
            transaction.rollback();
        }finally{
            session.close();
        }
        return users;
    }

    @Override
    public List<User> getUserByComputer(String computer) {

        log.info("get Users by computer name " + computer);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<User> users = null;
        try{
            transaction = session.beginTransaction();
            users = (List<User>)session.createQuery("from User where computer = :comp").setParameter("comp",computer).list();
            log.info("get Users by computer name succesfully");
            transaction.commit();
        } catch(Exception e){
            log.error("get Users by computer name fail",e);
            transaction.rollback();
        }finally{
            if(session.isOpen()){
                session.close();
            }
        }
        return users;
    }

    @Override
    public List<User> getAnotherUsers(Department department, Long id) {

        log.info("get another user");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<User> users = null;
        try{
            transaction = session.beginTransaction();
            users = (List<User>) session.createQuery("from User where department=:dept and id != :ide")
                    .setParameter("dept", department)
                    .setParameter("ide", id)
                    .list();
            transaction.commit();
            log.info("get another users succesully");
        }catch(Exception e){
            log.error("get another users fail");
            transaction.rollback();
        }finally{
            if(session.isOpen()) session.close();
        }
        return users;
    }
}
