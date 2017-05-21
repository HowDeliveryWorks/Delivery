package Delivery.DAO;

import Delivery.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * Created by igor on 21.05.17.
 */
@Component
public interface UserDAO extends MongoRepository<User, String> {
    public User findByUsername(String name);
}
