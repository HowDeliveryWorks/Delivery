package Delivery;

import Delivery.DAO.BurgersDAO;
import Delivery.DAO.IngredientsDAO;
import Delivery.enums.BurgerType;
import Delivery.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeliveryApplicationTests {

    @Autowired
    private BurgersDAO daoBurgers;

    @Autowired
    private IngredientsDAO daoIngredients;

	@Test
	public void contextLoads() {

        /****************************
         * {@link BurgersDAO} usage *
         ****************************/

        daoBurgers.deleteAll();
	    ArrayList<Burger> burgers = new ArrayList<>();

	    ArrayList<String> ing = new ArrayList<>();
	    Collections.addAll(ing,"Mayo", "Iceberg Lettuce", "Red Onion", "Chess", "Tomato", "Pickles", "BBQ sauce");
        Burger burger = new Burger(UUID.randomUUID(), "New York", MeatType.Beef, Roasting.Medium, BreadType.WihiteBread, false, ing, 400, 210, "burger-1.png", BurgerType.PreOrdered);
        burgers.add(burger);

        ArrayList<String> ing1 = new ArrayList<>();
        Collections.addAll(ing1,"Mayo", "Iceberg Lettuce", "Tomato", "BBQ sauce", "Chess", "Bacon", "Pickles", "Onion Rings");
        Burger burger1 = new Burger(UUID.randomUUID(), "The Burger", MeatType.Chicken, Roasting.WellDone, BreadType.WihiteBread, false, ing1, 390, 290, "burger-2.png", BurgerType.PreOrdered);
        burgers.add(burger1);

        ArrayList<String> ing2 = new ArrayList<>();
        Collections.addAll(ing2,"Iceberg Lettuce", "Sauce Salsa", "Secret Sauce", "Chess Viola");
        Burger burger2 = new Burger(UUID.randomUUID(), "Boston", MeatType.Veal, Roasting.WellDone, BreadType.WihiteBread, true, ing2, 360, 265, "burger-5.png", BurgerType.PreOrdered);
        burgers.add(burger2);

        ArrayList<String> ing3 = new ArrayList<>();
        Collections.addAll(ing3,"Berry Sauce", "Iceberg Lettuce", "Chess Dar Blue", "Caramelized Apples");
        Burger burger3 = new Burger(UUID.randomUUID(), "Carolina", MeatType.Veal, Roasting.WellDone, BreadType.WihiteBread, false, ing3, 320, 225, "burger-4.png", BurgerType.PreOrdered);
        burgers.add(burger3);

        ArrayList<String> ing4 = new ArrayList<>();
        Collections.addAll(ing4,"Onion", "Mozzarella", "Bulgarian Pepper", "BBQ sauce", "Iceberg Lettuce", "Tomato", "Eggplant", "Zucchini");
        Burger burger4 = new Burger(UUID.randomUUID(), "Florida", MeatType.Beef, Roasting.Medium, BreadType.BlackBread, false, ing4, 360, 165, "burger-3.png", BurgerType.PreOrdered);
        burgers.add(burger4);

        ArrayList<String> ing5 = new ArrayList<>();
        Collections.addAll(ing5,"Iceberg Lettuce", "Tomato", "Mustard", "Red Onion", "Mayo", "Chess Gauda", "Catchup");
        Burger burger5 = new Burger(UUID.randomUUID(), "Williams", MeatType.Beef, Roasting.MediumWell, BreadType.WihiteBread, true, ing5, 310, 160, "burger-6.png", BurgerType.PreOrdered);
        burgers.add(burger5);

        ArrayList<String> ing6 = new ArrayList<>();
        Collections.addAll(ing6,"Tomato", "Mozzarella", "Iceberg Lettuce", "Sauce Pesto");
        Burger burger6 = new Burger(UUID.randomUUID(), "Kentucky", MeatType.Chicken, Roasting.WellDone, BreadType.WihiteBread, false, ing6, 340, 170, "burger-7.png", BurgerType.PreOrdered);
        burgers.add(burger6);
        daoBurgers.insert(burgers);

        List<Burger> res = daoBurgers.findAll();
        System.out.print(res);


        /********************************
         * {@link IngredientsDAO} usage *
         ********************************/
        daoIngredients.deleteAll();
        ArrayList<Ingredient> ingredients = new ArrayList<>();

        Ingredient ingredient = new Ingredient(UUID.randomUUID(), "White Bun", ConstructorCategory.BreadType, 10, "whitebun.png");
        ingredients.add(ingredient);

        Ingredient ingredient2 = new Ingredient(UUID.randomUUID(), "Black Bun", ConstructorCategory.BreadType, 20, "blackbun.png");
        ingredients.add(ingredient2);

        Ingredient ingredient3 = new Ingredient(UUID.randomUUID(), "Chicken", ConstructorCategory.MeatType, 50, "chicken-breast.png");
        ingredients.add(ingredient3);

        Ingredient ingredient4 = new Ingredient(UUID.randomUUID(), "Beef", ConstructorCategory.MeatType, 60, "Beef.png");
        ingredients.add(ingredient4);

        Ingredient ingredient5 = new Ingredient(UUID.randomUUID(), "Pork", ConstructorCategory.MeatType, 60, "pork.png");
        ingredients.add(ingredient5);

        Ingredient ingredient6 = new Ingredient(UUID.randomUUID(), "Veal", ConstructorCategory.MeatType, 60, "vealmain.png");
        ingredients.add(ingredient6);

        Ingredient ingredient7 = new Ingredient(UUID.randomUUID(), "Falafel", ConstructorCategory.MeatType, 40, "falafel.jpg");
        ingredients.add(ingredient7);

        Ingredient ingredient8 = new Ingredient(UUID.randomUUID(), "Rare", ConstructorCategory.Roasting, 0, "rare.png");
        ingredients.add(ingredient8);

        Ingredient ingredient9 = new Ingredient(UUID.randomUUID(), "Medium Rare", ConstructorCategory.Roasting, 0, "mediumrare.png");
        ingredients.add(ingredient9);

        Ingredient ingredient10 = new Ingredient(UUID.randomUUID(), "Medium", ConstructorCategory.Roasting, 0, "medium.png");
        ingredients.add(ingredient10);

        Ingredient ingredient11 = new Ingredient(UUID.randomUUID(), "Medium Well", ConstructorCategory.Roasting, 0, "mediumwell.png");
        ingredients.add(ingredient11);

        Ingredient ingredient12 = new Ingredient(UUID.randomUUID(), "Well Done", ConstructorCategory.Roasting, 0, "welldone.png");
        ingredients.add(ingredient12);

        Ingredient ingredient13 = new Ingredient(UUID.randomUUID(), "Yellow Mustard", ConstructorCategory.Sauces, 5, "yellow.png");
        ingredients.add(ingredient13);

        Ingredient ingredient14 = new Ingredient(UUID.randomUUID(), "Aioli", ConstructorCategory.Sauces, 5, "aioli.png");
        ingredients.add(ingredient14);

        Ingredient ingredient15 = new Ingredient(UUID.randomUUID(), "BBQ Sauce", ConstructorCategory.Sauces, 5, "bbq.png");
        ingredients.add(ingredient15);

        Ingredient ingredient16 = new Ingredient(UUID.randomUUID(), "Mayo", ConstructorCategory.Sauces, 5, "mayo.png");
        ingredients.add(ingredient16);

        Ingredient ingredient17 = new Ingredient(UUID.randomUUID(), "Ketchup", ConstructorCategory.Sauces, 5, "ketchup.png");
        ingredients.add(ingredient17);

        daoIngredients.insert(ingredients);

        List<Ingredient> res1 = daoIngredients.findAll();
        System.out.print(res1);

	}

}
