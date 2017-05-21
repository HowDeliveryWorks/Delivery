package Delivery.DAO;

import Delivery.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * Created by igor on 21.05.17.
 */
@Component
public interface RoleDAO extends MongoRepository<Role, String> {
}
