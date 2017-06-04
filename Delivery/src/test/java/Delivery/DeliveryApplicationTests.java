package Delivery;

import Delivery.DAO.*;
import Delivery.entity.*;
import Delivery.enums.BurgerType;
import Delivery.enums.ConstructorCategory;
import Delivery.enums.Roasting;
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
    private BreadTypeDAO daoBreadType;

    @Autowired
    private SaucesDAO daoSauces;

    @Autowired
    private MiscIngredientsDAO daoMiscIngredients;

    @Autowired
    private MeatDAO daoMeat;

	@Test
	public void contextLoads() {

        /******************************
         * {@link BreadTypeDAO} usage *
         ******************************/

        daoBreadType.deleteAll();
        ArrayList<BreadType> breadTypes = new ArrayList<>();

        BreadType breadWhiteBun = new BreadType(UUID.randomUUID(), "White Bun", ConstructorCategory.BreadType, 40, "whitebun.png");
        breadTypes.add(breadWhiteBun);

        BreadType breadBlackBun = new BreadType(UUID.randomUUID(), "Black Bun", ConstructorCategory.BreadType, 50, "blackbun.png");
        breadTypes.add(breadBlackBun);

        daoBreadType.insert(breadTypes);

        List<BreadType> breadList = daoBreadType.findAll();
        System.out.print(breadList);

        /*************************
         * {@link MeatDAO} usage *
         *************************/

        daoMeat.deleteAll();
        ArrayList<Meat> meat = new ArrayList<>();

        Meat meatChicken = new Meat(UUID.randomUUID(), "Chicken", ConstructorCategory.Meat, 50, "chicken-breast.png", true);
        meat.add(meatChicken);

        Meat meatBeef = new Meat(UUID.randomUUID(), "Beef", ConstructorCategory.Meat, 60, "Beef.png", false);
        meat.add(meatBeef);

        Meat meatPork = new Meat(UUID.randomUUID(), "Pork", ConstructorCategory.Meat, 60, "pork.png", false);
        meat.add(meatPork);

        Meat meatVeal = new Meat(UUID.randomUUID(), "Veal", ConstructorCategory.Meat, 60, "vealmain.png", false);
        meat.add(meatVeal);

        Meat meatFalafel = new Meat(UUID.randomUUID(), "Falafel", ConstructorCategory.Meat, 40, "falafel.jpg", true);
        meat.add(meatFalafel);

        daoMeat.insert(meat);

        List<Meat> meatList = daoMeat.findAll();
        System.out.print(meatList);

        /******************************
         * {@link SaucesDAO} usage *
         ******************************/

        daoSauces.deleteAll();
        ArrayList<Sauce> sauces = new ArrayList<>();

        Sauce sauceYellowMustard = new Sauce(UUID.randomUUID(), "Yellow Mustard", ConstructorCategory.Sauce, 10, "yellow.png");
        sauces.add(sauceYellowMustard);

        Sauce sauceAioli = new Sauce(UUID.randomUUID(), "Aioli", ConstructorCategory.Sauce, 10, "aioli.png");
        sauces.add(sauceAioli);

        Sauce sauceBBQ = new Sauce(UUID.randomUUID(), "BBQ Sauce", ConstructorCategory.Sauce, 10, "bbq.png");
        sauces.add(sauceBBQ);

        Sauce sauceMayo = new Sauce(UUID.randomUUID(), "Mayo", ConstructorCategory.Sauce, 10, "mayo.png");
        sauces.add(sauceMayo);

        Sauce sauceKetchup = new Sauce(UUID.randomUUID(), "Ketchup", ConstructorCategory.Sauce, 10, "ketchup.png");
        sauces.add(sauceKetchup);

        daoSauces.insert(sauces);

        List<Sauce> saucesList = daoSauces.findAll();
        System.out.print(saucesList);

        /************************************
         * {@link MiscIngredientsDAO} usage *
         ************************************/
        daoMiscIngredients.deleteAll();
        ArrayList<MiscIngredient> miscIngredients = new ArrayList<>();


        MiscIngredient miscPickles = new MiscIngredient(UUID.randomUUID(), "Pickles", ConstructorCategory.Misc, 10, "pickles.png");
        miscIngredients.add(miscPickles);

        MiscIngredient miscTomatos = new MiscIngredient(UUID.randomUUID(), "Tomatos", ConstructorCategory.Misc, 10, "tomato.png");
        miscIngredients.add(miscTomatos);

        MiscIngredient miscGarlic = new MiscIngredient(UUID.randomUUID(), "Garlic", ConstructorCategory.Misc, 5, "garlic.png");
        miscIngredients.add(miscGarlic);

        MiscIngredient miscOnion = new MiscIngredient(UUID.randomUUID(), "Onion", ConstructorCategory.Misc, 10, "onion.png");
        miscIngredients.add(miscOnion);

        MiscIngredient miscBlueCheese = new MiscIngredient(UUID.randomUUID(), "Blue Cheese", ConstructorCategory.Misc, 20, "bluelarge.png");
        miscIngredients.add(miscBlueCheese);

        MiscIngredient miscMozzarella = new MiscIngredient(UUID.randomUUID(), "Mozzarella", ConstructorCategory.Misc, 20, "mozzarella.png");
        miscIngredients.add(miscMozzarella);

        MiscIngredient miscParmesan = new MiscIngredient(UUID.randomUUID(), "Parmesan", ConstructorCategory.Misc, 20, "parmesan.png");
        miscIngredients.add(miscParmesan);

        MiscIngredient miscCheddar = new MiscIngredient(UUID.randomUUID(), "Cheddar", ConstructorCategory.Misc, 20, "cheddar.png");
        miscIngredients.add(miscCheddar);

        MiscIngredient miscBasil = new MiscIngredient(UUID.randomUUID(), "Basil", ConstructorCategory.Misc, 15, "basil.png");
        miscIngredients.add(miscBasil);

        MiscIngredient miscSalad = new MiscIngredient(UUID.randomUUID(), "Salad", ConstructorCategory.Misc, 10, "salad.png");
        miscIngredients.add(miscSalad);

        /*********** Roasting ***********/

        MiscIngredient roastingRare = new MiscIngredient(UUID.randomUUID(), "Rare", ConstructorCategory.Roasting, 0, "rare.png");
        miscIngredients.add(roastingRare);

        MiscIngredient roastingMediumRare = new MiscIngredient(UUID.randomUUID(), "Medium Rare", ConstructorCategory.Roasting, 0, "mediumrare.png");
        miscIngredients.add(roastingMediumRare);

        MiscIngredient roastingMedium = new MiscIngredient(UUID.randomUUID(), "Medium", ConstructorCategory.Roasting, 0, "medium.png");
        miscIngredients.add(roastingMedium);

        MiscIngredient roastingMediumWell = new MiscIngredient(UUID.randomUUID(), "Medium Well", ConstructorCategory.Roasting, 0, "mediumwell.png");
        miscIngredients.add(roastingMediumWell);

        MiscIngredient roastingWellDone = new MiscIngredient(UUID.randomUUID(), "Well Done", ConstructorCategory.Roasting, 0, "welldone.png");
        miscIngredients.add(roastingWellDone);

        daoMiscIngredients.insert(miscIngredients);

        List<MiscIngredient> ingredientList = daoMiscIngredients.findAll();
        System.out.print(ingredientList);

        /****************************
         * {@link BurgersDAO} usage *
         ****************************/

        daoBurgers.deleteAll();
        ArrayList<Burger> burgers = new ArrayList<>();

        ArrayList<MiscIngredient> ing = new ArrayList<>();
        Collections.addAll(ing, sauceBBQ, sauceMayo);
        Burger burger = new Burger(UUID.randomUUID(), "New York", meatChicken, Roasting.None, breadWhiteBun, false, ing, 400, 210, "burger-1.png", BurgerType.PreOrdered);
        burgers.add(burger);

        ArrayList<MiscIngredient> ing1 = new ArrayList<>();
        Collections.addAll(ing1, sauceKetchup, sauceYellowMustard, sauceAioli);
        Burger burger1 = new Burger(UUID.randomUUID(), "The Burger", meatChicken, Roasting.WellDone, breadWhiteBun, false, ing1, 390, 290, "burger-2.png", BurgerType.PreOrdered);
        burgers.add(burger1);

        ArrayList<MiscIngredient> ing2 = new ArrayList<>();
        Collections.addAll(ing2, sauceKetchup, sauceMayo);
        Burger burger2 = new Burger(UUID.randomUUID(), "Boston",meatVeal, Roasting.WellDone, breadWhiteBun, true, ing2, 360, 265, "burger-5.png", BurgerType.PreOrdered);
        burgers.add(burger2);

        ArrayList<MiscIngredient> ing3 = new ArrayList<>();
        Collections.addAll(ing3, sauceBBQ);
        Burger burger3 = new Burger(UUID.randomUUID(), "Carolina", meatVeal, Roasting.WellDone, breadWhiteBun, false, ing3, 320, 225, "burger-4.png", BurgerType.PreOrdered);
        burgers.add(burger3);

        ArrayList<MiscIngredient> ing4 = new ArrayList<>();
        Collections.addAll(ing4, sauceYellowMustard, sauceMayo);
        Burger burger4 = new Burger(UUID.randomUUID(), "Florida", meatBeef, Roasting.Medium, breadBlackBun, true, ing4, 360, 165, "burger-3.png", BurgerType.PreOrdered);
        burgers.add(burger4);

        ArrayList<MiscIngredient> ing5 = new ArrayList<>();
        Collections.addAll(ing5, sauceAioli);
        Burger burger5 = new Burger(UUID.randomUUID(), "Williams", meatBeef, Roasting.MediumWell, breadBlackBun, true, ing5, 310, 160, "burger-6.png", BurgerType.PreOrdered);
        burgers.add(burger5);

        ArrayList<MiscIngredient> ing6 = new ArrayList<>();
        Collections.addAll(ing6, sauceBBQ, sauceMayo);
        Burger burger6 = new Burger(UUID.randomUUID(), "Kentucky", meatChicken, Roasting.None, breadWhiteBun, false, ing6, 340, 170, "burger-7.png", BurgerType.PreOrdered);
        burgers.add(burger6);
        daoBurgers.insert(burgers);

        List<Burger> burgerList = daoBurgers.findAll();
        System.out.print(burgerList);

	}

}
