package Delivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by LevelNone on 20.04.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private Integer number = 0;
    private String name;
    private String email;
    private String phone;
    private String address;
    private OrderPaymentMethod paymentMethod;
    private String comment;
    private List<CartLineInfo> burgers;
}
