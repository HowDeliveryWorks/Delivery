package Delivery.DAO;

import Delivery.entity.Meat;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Max on 14.05.2017.
 */

public interface MeatDAO extends MongoRepository<Meat, String>  {
    public Meat findByName(String name);
    public Meat findByRoastingEnabled(boolean roastingEnabled);
}
