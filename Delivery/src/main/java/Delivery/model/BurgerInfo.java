package Delivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.ArrayList;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BurgerInfo {

    private UUID id;
    private String name;
    private MeatType meatType;
    private Roasting roasting;
    private BreadType breadType;
    private Boolean spicy;
    private ArrayList<String> ingredients;
    private int weight;
    private int price;

    private boolean newProduct=false;

    // Upload file.
    private CommonsMultipartFile fileData;

    public BurgerInfo(Burger burger) {
        this.id = burger.getId();
        this.name = burger.getName();
        this.meatType = burger.getMeatType();
        this.roasting = burger.getRoasting();
        this.breadType = burger.getBreadType();
        this.spicy = burger.getSpicy();
        this.ingredients = burger.getIngredients();
        this.weight = burger.getWeight();
        this.price = burger.getPrice();
    }

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }

    public boolean isNewProduct() {
        return newProduct;
    }

    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }
}
