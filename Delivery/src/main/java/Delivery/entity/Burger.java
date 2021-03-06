package Delivery.entity;

import Delivery.enums.BurgerType;
import Delivery.enums.Roasting;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by igor on 08.04.17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Burger {

    @Id
    private UUID id;
    private String name;
    private Meat meat;
    private Roasting roasting;
    private BreadType breadType;
    private Boolean spicy;
    private ArrayList<MiscIngredient> ingredients;

    private int weight;
    private int price;
    private String photo;
    private BurgerType burgerType;

    @Override
    public String toString() {
        return "Burger{" +
                "name='" + name + '\'' +
                '}';
    }

}
