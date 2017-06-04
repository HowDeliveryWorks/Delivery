package Delivery.DAO;

import Delivery.entity.Order;
import Delivery.enums.OrderPaymentMethod;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * Created by LevelNone on 16.05.2017.
 */
@Component
public interface OrdersDAO extends MongoRepository<Order, String> {
    Order findById(long id);
    Order findByName(String name);
    Order findByEmail(String email);
    Order findByPhone(String phone);
    Order findByAddress(String address);
    Order findByPaymentMethod(OrderPaymentMethod orderPaymentMethod);
    Order findByComment(String comment);
}
