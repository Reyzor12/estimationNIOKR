package org.eleron.lris.niokr.dao;

import org.eleron.lris.niokr.model.Department;

import java.util.List;

public interface DepartmentDAO {

    public void addDepartment(Department department);
    public void updateDepartment(Department department);
    public List<Department> listDepartment();
    public Department getDepartmentById(Long id);
    public void removeDepartmentById(Long id);
}
