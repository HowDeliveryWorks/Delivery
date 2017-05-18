package Delivery.entity;

import Delivery.enums.ConstructorCategory;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * Created by Max on 02.05.2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Ingredient {

    @Id
    private UUID id;
    private String name;
    private ConstructorCategory constructorCategory;

    private int price;
    private String photo;

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                '}';
    }

}
