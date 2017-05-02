package Delivery.DAO;

import Delivery.model.Burger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by igor on 08.04.17.
 */
@Component
public interface BurgersDAO extends MongoRepository<Burger, String> {
    public Burger findById(UUID id);
    public Burger findByName(String name);
}
