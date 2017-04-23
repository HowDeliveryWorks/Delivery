package Delivery.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CartInfo {

    private int orderNum;

    private CustomerInfo customerInfo;

    private final List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();

    public CartInfo() {

    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public List<CartLineInfo> getCartLines() {
        return this.cartLines;
    }

    private CartLineInfo findLineByName(String name) {
        for (CartLineInfo line : this.cartLines) {
            if (line.getBurgerInfo().getName().equals(name)) {
                return line;
            }
        }
        return null;
    }

    public void addBurger(BurgerInfo burgerInfo, int quantity) {
        CartLineInfo line = this.findLineByName(burgerInfo.getName());

        if (line == null) {
            line = new CartLineInfo();
            line.setQuantity(0);
            line.setBurgerInfo(burgerInfo);
            this.cartLines.add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartLines.remove(line);
        } else {
            line.setQuantity(newQuantity);
        }
    }

    public void validate() {

    }

    public void updateBurger(String name, int quantity) {
        CartLineInfo line = this.findLineByName(name);

        if (line != null) {
            if (quantity <= 0) {
                this.cartLines.remove(line);
            } else {
                line.setQuantity(quantity);
            }
        }
    }

    public void removeBurger(BurgerInfo burgerInfo) {
        CartLineInfo line = this.findLineByName(burgerInfo.getName());
        if (line != null) {
            this.cartLines.remove(line);
        }
    }

    public boolean isEmpty() {
        return this.cartLines.isEmpty();
    }

    public boolean isValidCustomer() {
        return this.customerInfo != null && this.customerInfo.isValid();
    }

    public int getQuantityTotal() {
        int quantity = 0;
        for (CartLineInfo line : this.cartLines) {
            quantity += line.getQuantity();
        }
        return quantity;
    }

    public double getAmountTotal() {
        double total = 0;
        for (CartLineInfo line : this.cartLines) {
            total += line.getAmount();
        }
        return total;
    }

    public void updateQuantity(CartInfo cartForm) {
        if (cartForm != null) {
            List<CartLineInfo> lines = cartForm.getCartLines();
            for (CartLineInfo line : lines) {
                this.updateBurger(line.getBurgerInfo().getName(), line.getQuantity());
            }
        }

    }

}
