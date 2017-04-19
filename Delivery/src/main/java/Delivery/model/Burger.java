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
    public String name;
    public MeatType meatType;
    public Roasting roasting;
    public BreadType breadType;
    public Boolean spicy;
    public ArrayList<String> ingredients;
    public String photo;

    public int weight;
    public int price;

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
