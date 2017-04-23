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
@AllArgsConstructor
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
    private int price;
    private String photo;

    @Override
    public String toString() {
        return "Burger{" +
                "name='" + name + '\'' +
                '}';
    }

}
