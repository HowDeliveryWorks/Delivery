package Delivery.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by igor on 08.04.17.
 */
@Data
@NoArgsConstructor
public class Burger {

    @Id
    private UUID id;
    private String name;
    private MeatType meatType;
    private Roasting roasting;
    private BreadType breadType;
    private Boolean spicy;
    private ArrayList<String> ingredients;
    private String photo;

    private int weight;
    private int price;

    @Override
    public String toString() {
        return "Burger{" +
                "name='" + name + '\'' +
                '}';
    }

    public Burger(UUID id, String name, MeatType meatType, Roasting roasting, BreadType breadType, Boolean spicy, ArrayList<String> ingredients, int weight, int price, String photo) {
        this.id = id;
        this.name = name;
        this.meatType = meatType;
        this.roasting = roasting;
        this.breadType = breadType;
        this.spicy = spicy;
        this.ingredients = ingredients;
        this.weight = weight;
        this.price = price;
        this.photo = photo;
    }
}
