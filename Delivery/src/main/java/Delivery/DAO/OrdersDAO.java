package Delivery.DAO;

import Delivery.model.Order;
import Delivery.enums.OrderPaymentMethod;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * Created by LevelNone on 16.05.2017.
 */
@Component
public interface OrdersDAO extends MongoRepository<Order, String> {
    public Order findById(long id);
    public Order findByName(String name);
    public Order findByEmail(String email);
    public Order findByPhone(String phone);
    public Order findByAddress(String address);
    public Order findByPaymentMethod(OrderPaymentMethod orderPaymentMethod);
    public Order findByComment(String comment);
}
