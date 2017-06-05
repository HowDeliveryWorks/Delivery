package Delivery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by pc on 05.06.2017.
 */

@Data
@AllArgsConstructor
public class BurgerUserRating {

    private UUID id;
    private Burger burger;
    private int rating;

}
