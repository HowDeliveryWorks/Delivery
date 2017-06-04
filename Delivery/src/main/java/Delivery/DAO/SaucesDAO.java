package Delivery.DAO;

import Delivery.entity.Sauce;
import Delivery.enums.ConstructorCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by Max on 15.05.2017.
 */
public interface SaucesDAO extends MongoRepository<Sauce, String> {
    Sauce findById(UUID id);
    Sauce findByName(String name);
    List<Sauce> findByConstructorCategory(ConstructorCategory constructorCategory);
}
