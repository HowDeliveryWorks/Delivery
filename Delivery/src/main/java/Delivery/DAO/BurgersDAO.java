package Delivery.DAO;

import Delivery.model.Burger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by igor on 08.04.17.
 */
@Component
public interface BurgersDAO extends MongoRepository<Burger, String> {
    public Burger findByName(String name);

}
