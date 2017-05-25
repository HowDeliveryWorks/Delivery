package Delivery.services;

import Delivery.DAO.RoleDAO;
import Delivery.DAO.UserDAO;
import Delivery.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Created by igor on 22.05.17.
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
    public boolean save(User user) {
        if(userDAO.findByEmail(user.getEmail()) != null) return false;

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleDAO.findAll()));
        userDAO.save(user);
        return true;
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByEmail(username);
    }
}
