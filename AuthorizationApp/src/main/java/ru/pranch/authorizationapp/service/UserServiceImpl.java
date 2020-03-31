package ru.pranch.authorizationapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pranch.authorizationapp.dao.RoleDAO;
import ru.pranch.authorizationapp.dao.UserDAO;
import ru.pranch.authorizationapp.model.Role;
import ru.pranch.authorizationapp.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of {@link UserService} interface
 *
 * @author Anatoly
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.getOne(1L));
        user.setRoles(roles);
        userDAO.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public User findById(Long id) {
        return userDAO.getOne(id);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public User saveUser(User user) {
        return userDAO.save(user);
    }

    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }

    public List<Role> findRolesAll() {
        return roleDAO.findAll();
    }

    public  Role findRoleById(Long id){
        return roleDAO.getOne(id);
    }
}
