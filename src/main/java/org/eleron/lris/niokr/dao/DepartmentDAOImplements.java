package org.eleron.lris.niokr.dao;

import org.apache.log4j.Logger;
import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DepartmentDAOImplements implements DepartmentDAO {

    final static Logger log = Logger.getLogger(DepartmentDAOImplements.class);

    @Override
    public void addDepartment(Department department) {
        log.info("add " + department + " begin");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.merge(department);
            transaction.commit();
            log.info("add " + department + " was successful");
        } catch(Exception e){
            log.error("add " + department + "fail ",e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally{
            session.close();
        }
    }

    @Override
    public void updateDepartment(Department department) {
        log.info("update " + department + " begin");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(department);
            transaction.commit();
            log.info("update " + department + " was successful");
        } catch (Exception e) {
            log.error("update " + department + " fail ", e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally{
            session.close();
        }
    }

    @Override
    public List<Department> listDepartment() {
        log.info("list " + this.getClass().getName() + " try receive");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Department> departments = null;
        try{
            transaction = session.beginTransaction();
            departments = session.createQuery("from Department").list();
            transaction.commit();
            log.info("list " + this.getClass().getName() + " receive");
        }catch (Exception e) {
            log.error("list " + this.getClass().getName() + " fail ", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }finally{
            session.close();
        }
        return departments;
    }

    @Override
    public Department getDepartmentById(Long id) {
        log.info("get " + this.getClass().getName() + " id " + id + " start");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Department department_curr = null;
        try{
            transaction = session.beginTransaction();
            department_curr = (Department)session.load(Department.class,id);
            transaction.commit();
            log.info("get " + this.getClass().getName() + " id " + id + " successful");
        }catch(Exception e){
            log.error("get " + this.getClass().getName() + " id " + id + " fail");
            if (transaction != null) {
                transaction.rollback();
            }
        }finally{
            session.close();
        }
        return department_curr;
    }

    @Override
    public void removeDepartmentById(Long id) {
        log.info("remove " + this.getClass().getName() + " id " + id + " start");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Department department = (Department) session.load(Department.class,id);
            if (department != null){
                session.delete(department);
            }
            transaction.commit();
            log.info("remove " + this.getClass().getName() + " id " + id + " successful");
        } catch(Exception e) {
            log.error("remove " + this.getClass().getName() + " id " + id + " fail");
            if (transaction != null) {
                transaction.rollback();
            }
        }finally{
            session.close();
        }
    }
}
