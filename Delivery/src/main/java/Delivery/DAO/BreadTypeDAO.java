package Delivery.DAO;

import Delivery.entity.BreadType;
import Delivery.enums.ConstructorCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by Max on 15.05.2017.
 */
public interface BreadTypeDAO extends MongoRepository<BreadType, String> {
    public BreadType findById(UUID id);
    public BreadType findByName(String name);
    public List<BreadType> findByConstructorCategory(ConstructorCategory constructorCategory);
}
