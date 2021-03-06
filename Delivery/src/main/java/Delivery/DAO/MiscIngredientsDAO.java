package Delivery.DAO;

import Delivery.entity.MiscIngredient;
import Delivery.enums.ConstructorCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by Max on 02.05.2017.
 */
public interface MiscIngredientsDAO extends MongoRepository<MiscIngredient, String> {
    MiscIngredient findById(UUID id);
    MiscIngredient findByName(String name);
    List<MiscIngredient> findByConstructorCategory(ConstructorCategory constructorCategory);
}
