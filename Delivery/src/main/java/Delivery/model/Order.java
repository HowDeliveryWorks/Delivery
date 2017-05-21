package Delivery.model;

import Delivery.enums.OrderPaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by LevelNone on 20.04.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private OrderPaymentMethod paymentMethod;
    private String comment;
    private List<CartLineInfo> burgers;
}
