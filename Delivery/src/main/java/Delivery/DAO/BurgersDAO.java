package Delivery.DAO;

import Delivery.enums.BurgerType;
import Delivery.entity.Burger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by igor on 08.04.17.
 */
@Component
public interface BurgersDAO extends MongoRepository<Burger, String> {
    Burger findById(UUID id);
    Burger findByName(String name);
    List<Burger> findByBurgerType(BurgerType burgerType);
}
