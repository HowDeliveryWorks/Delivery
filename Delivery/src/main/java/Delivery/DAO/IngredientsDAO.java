package Delivery.DAO;

import Delivery.model.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

/**
 * Created by Max on 02.05.2017.
 */
public interface IngredientsDAO extends MongoRepository<Ingredient, String> {
    public Ingredient findById(UUID id);
    public Ingredient findByName(String name);
}
