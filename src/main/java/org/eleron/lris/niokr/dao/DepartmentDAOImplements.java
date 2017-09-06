package org.eleron.lris.niokr.dao;

import org.apache.log4j.Logger;
import org.eleron.lris.niokr.model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DepartmentDAOImplements implements DepartmentDAO {

    final static Logger log = Logger.getLogger(DepartmentDAOImplements.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addDepartment(Department department) {

        log.info("add " + department + " begin");
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.persist(department);
            transaction.commit();
            log.info("add " + department + " was successful");
        } catch(Exception e){
            log.error("add " + department + "fail ",e);
            transaction.rollback();
        } finally{
            session.close();
        }

    }

    @Override
    public void updateDepartment(Department department) {

        log.info("update " + department + " begin");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try{

            session.update(department);
            transaction.commit();
            log.info("update " + department + " was successful");
        } catch (Exception e) {
            transaction.rollback();
            log.error("update " + department + " fail ", e);
        } finally{
            session.close();
        }

    }

    @Override
    public List<Department> listDepartment() {

        log.info("list " + this.getClass().getName() + " try receive");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Department> departments = null;
        try{
            departments = session.createQuery("from department").list();
            transaction.commit();
            log.info("list " + this.getClass().getName() + " receive");
        }catch (Exception e) {
            log.error("list " + this.getClass().getName() + " fail ", e);
            transaction.rollback();
        }finally{
            session.close();
        }
        return departments;
    }

    @Override
    public Department getDepartmentById(Long id) {

        log.info("get " + this.getClass().getName() + " id " + id + " start");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Department department_curr = null;
        try{
            department_curr = (Department)session.load(Department.class,id);
            transaction.commit();
            log.info("get " + this.getClass().getName() + " id " + id + " successful");
        }catch(Exception e){
            transaction.rollback();
            log.error("get " + this.getClass().getName() + " id " + id + " fail");
        }finally{
            session.close();
        }
        return department_curr;
    }

    @Override
    public void removeDepartmentById(Long id) {

        log.info("remove " + this.getClass().getName() + " id " + id + " start");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try{
            Department department = (Department) session.load(Department.class,id);
            if (department != null){
                session.delete(department);
            }
        } catch(Exception e) {
            log.error("remove " + this.getClass().getName() + " id " + id + " fail");
            transaction.rollback();
        }finally{
            session.close();
        }
    }
}
