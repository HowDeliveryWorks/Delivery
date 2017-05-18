package Delivery.entity;

import Delivery.enums.ConstructorCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by Max on 15.05.2017.
 */
@Data
@NoArgsConstructor
public class MiscIngredient extends Ingredient {
    public MiscIngredient(UUID id, String name, ConstructorCategory constructorCategory, int price, String photo){
        super(id, name, constructorCategory, price, photo);
    }
}
