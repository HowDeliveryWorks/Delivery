package Delivery.model;

public class CartLineInfo {

    private BurgerInfo burgerInfo;
    private int quantity;

    public CartLineInfo() {
        this.quantity = 0;
    }

    public BurgerInfo getBurgerInfo() {
        return burgerInfo;
    }

    public void setBurgerInfo(BurgerInfo burgerInfo) {
        this.burgerInfo = burgerInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return this.burgerInfo.getPrice() * this.quantity;
    }
}
