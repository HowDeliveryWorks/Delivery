package Delivery.services;

import Delivery.DAO.RoleDAO;
import Delivery.DAO.UserDAO;
import Delivery.entity.BurgerUserRating;
import Delivery.entity.Order;
import Delivery.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

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
        user.setId(UUID.randomUUID());
        user.setPhone("+380901231212");
        user.setAddress("MockAddress");
        user.setOrders(new ArrayList<Order>());
        user.setOrderedBurgers(new ArrayList<BurgerUserRating>());
        userDAO.save(user);
        return true;
    }

    @Override
    public User findByName(String name) {
        return userDAO.findByEmail(name);
    }
}
