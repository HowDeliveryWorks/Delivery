package Delivery.entity;


import Delivery.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.util.Set;
import java.util.List;
import java.util.UUID;

/**
 * Created by Max on 01.06.2017.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private UUID id;
    private String name;
    private String password;
    private String passwordConfirm;
    private Set<Role> roles;
    private String email;
    private String phone;
    private String address;
    private List<Order> orders;
    private List<BurgerUserRating> orderedBurgers;
}
