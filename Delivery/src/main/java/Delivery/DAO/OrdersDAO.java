package Delivery.DAO;

import Delivery.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * Created by LevelNone on 14.05.2017.
 */
@Component
public interface OrdersDAO extends MongoRepository<Order, String> {
    public Order findById(long id);
}

