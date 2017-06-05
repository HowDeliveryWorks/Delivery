package Delivery.DAO;

import Delivery.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

/**
 * Created by Max on 01.06.2017.
 */
public interface UserDAO extends MongoRepository<User, String> {

    User findById(UUID id);
    User findByName(String name);
    User findByEmail(String email);
    User findByPhone(String phone);
    User findByAddress(String address);
}
