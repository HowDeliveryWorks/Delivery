package Delivery.entity;

import Delivery.enums.ConstructorCategory;
import lombok.*;

import java.util.UUID;

/**
 * Created by Max on 14.05.2017.
 */

@Data
@NoArgsConstructor
public class Meat extends Ingredient {

    private boolean roastingEnabled;

    public Meat(UUID id, String name, ConstructorCategory constructorCategory, int price, String photo, boolean roastingEnabled){
        super(id, name, constructorCategory, price, photo);
        this.roastingEnabled = roastingEnabled;
    }

}
