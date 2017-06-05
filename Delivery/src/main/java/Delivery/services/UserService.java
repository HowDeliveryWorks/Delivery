package Delivery.services;

import Delivery.entity.User;

/**
 * Created by igor on 22.05.17.
 */
public interface UserService {
    boolean save(User user);

    User findByUsername(String username);
}
