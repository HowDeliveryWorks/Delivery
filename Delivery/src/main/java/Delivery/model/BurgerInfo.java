package Delivery.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.ArrayList;
import java.util.UUID;

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

    public BurgerInfo() {
    }

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

    public BurgerInfo(UUID id, String name, MeatType meatType, Roasting roasting, BreadType breadType, Boolean spicy, ArrayList<String> ingredients, int weight, int price) {
        this.id = id;
        this.name = name;
        this.meatType = meatType;
        this.roasting = roasting;
        this.breadType = breadType;
        this.spicy = spicy;
        this.ingredients = ingredients;
        this.weight = weight;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MeatType getMeatType() {
        return meatType;
    }

    public void setMeatType(MeatType meatType) {
        this.meatType = meatType;
    }

    public Roasting getRoasting() {
        return roasting;
    }

    public void setRoasting(Roasting roasting) {
        this.roasting = roasting;
    }

    public BreadType getBreadType() {
        return breadType;
    }

    public void setBreadType(BreadType breadType) {
        this.breadType = breadType;
    }

    public Boolean getSpicy() {
        return spicy;
    }

    public void setSpicy(Boolean spicy) {
        this.spicy = spicy;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
