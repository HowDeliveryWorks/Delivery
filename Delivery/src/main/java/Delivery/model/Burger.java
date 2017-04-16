package Delivery.model;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by igor on 08.04.17.
 */
@Data
@Builder
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

    private int weight;
    private float prize;

    @Override
    public String toString() {
        return "Burger{" +
                "name='" + name + '\'' +
                '}';
    }

    public Burger(UUID id, String name, MeatType meatType, Roasting roasting, BreadType breadType, Boolean spicy, ArrayList<String> ingredients, int weight, float prize) {
        this.id = id;
        this.name = name;
        this.meatType = meatType;
        this.roasting = roasting;
        this.breadType = breadType;
        this.spicy = spicy;
        this.ingredients = ingredients;
        this.weight = weight;
        this.prize = prize;
    }
}
