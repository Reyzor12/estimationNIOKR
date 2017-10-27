package org.eleron.lris.niokr.dao;

import org.eleron.lris.niokr.model.Department;
import org.eleron.lris.niokr.model.User;

import java.util.List;

public interface UserDAO {

    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUserById(Long id);
    public List<User> listUser();
    public User getUserById(Long id);
    public List<User> getAnotherUserWithInDepartment(Long id);
    public List<User> getUsersByParameters(User user);
    public List<User> getUserByDepartment(Department department);
    public List<User> getUserByComputer(String computer);
    public List<User> getAnotherUsers(Department department, Long id);
}
