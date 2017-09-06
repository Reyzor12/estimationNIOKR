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
        session.persist(department);
        transaction.commit();
        session.close();
        log.info("add " + department + " was successful");
    }

    @Override
    public void updateDepartment(Department department) {

        log.info("update " + department + " begin");
        Session session = this.sessionFactory;
    }

    @Override
    public List<Department> listDepartment() {
        return null;
    }

    @Override
    public Department getDepartmentById(Long id) {
        return null;
    }

    @Override
    public void removeDepartmentById(Long id) {

    }
}
