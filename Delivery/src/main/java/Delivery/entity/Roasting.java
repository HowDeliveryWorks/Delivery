package Delivery.entity;

import Delivery.enums.ConstructorCategory;

import java.util.UUID;

/**
 * Created by Max on 18.05.2017.
 */
public class Roasting extends Ingredient {
    private boolean roastingEnabled;
    public Roasting(UUID id, String name, ConstructorCategory constructorCategory, int price, String photo, boolean roastingEnabled) {
        super(id, name, constructorCategory, price, photo);
        this.roastingEnabled = roastingEnabled;
    }
}
