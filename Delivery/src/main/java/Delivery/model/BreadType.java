package Delivery.model;

/**
 * Created by igor on 08.04.17.
 */
public enum BreadType {
    WihiteBread ("White Bun"),
    BlackBread ("Black Bun");

    BreadType(String name) {
        this.name = name;
    }

    private final String name;

    @Override
    public String toString() {
        return this.name;
    }
}