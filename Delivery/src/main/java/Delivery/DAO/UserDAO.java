package Delivery.DAO;

import Delivery.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * Created by Max on 01.06.2017.
 */
@Component
public interface UserDAO extends MongoRepository<User, String> {

    User findById(UUID id);
    User findByName(String name);
    User findByEmail(String email);
    User findByPhone(String phone);
    User findByAddress(String address);
}
