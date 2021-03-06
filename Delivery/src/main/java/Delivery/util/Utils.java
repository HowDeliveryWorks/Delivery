package Delivery.util;

import Delivery.entity.*;
import Delivery.enums.BurgerType;
import Delivery.enums.Roasting;
import Delivery.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class Utils {

    // Products in Cart, stored in Session.
    public static CartInfo getCartInSession(HttpServletRequest request) {

        // Get Cart from Session.
        CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("currentCart");

        // If null, create it.
        if (cartInfo == null) {
            cartInfo = new CartInfo();

            // And store to Session.
            request.getSession().setAttribute("currentCart", cartInfo);
        }
        //System.out.println(cartInfo.toString());
        return cartInfo;
    }

    // User stored in Session.
    public static User getUserInSession(HttpServletRequest request) {

        // Get User from Session.
        User currentUser = (User) request.getSession().getAttribute("currentUser");

        if (currentUser != null) {
            System.out.println("Got current user");
        }

        //TODO: User add #2. If no use suggest to LogIn or SignUp
        // If null, create it.
//        if (currentUser == null) {
//            currentUser = new User();
//
//            // And store to Session.
//            request.getSession().setAttribute("currentUser", currentUser);
//        }
        return currentUser;
    }

    public static Integer getCustomBurgersNumberInSession(HttpServletRequest request) {

        // Get CustomBurgerNumber from Session.
        Integer customBurgerCounter = (Integer) request.getSession().getAttribute("customBurgerNumber");

        // If null, create it, else increment.
        if (customBurgerCounter == null) {
            customBurgerCounter = 1;

            // And store to Session.
            request.getSession().setAttribute("customBurgerNumber", customBurgerCounter);

        } else {
            customBurgerCounter++;
            request.getSession().setAttribute("customBurgerNumber", customBurgerCounter);
        }
        return customBurgerCounter;
    }

    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("currentCart");
    }

    public static void storeLastOrderedCartInSession(HttpServletRequest request, CartInfo cartInfo) {
        request.getSession().setAttribute("lastOrderedCart", cartInfo);
    }

    public static CartInfo getLastOrderedCartInSession(HttpServletRequest request) {
        return (CartInfo) request.getSession().getAttribute("lastOrderedCart");
    }

    public static boolean compareBurgersByName(Burger userBurger, Burger dbBurger) {

        boolean equal = false;
        if (userBurger.getName().equals(dbBurger.getName())) {
            equal = true;
        }
//        System.out.println(userBurger.getName() + " " + dbBurger.getName());
//        System.out.println(userBurger.getName().equals(dbBurger.getName()));
        return equal;
    }

    public static boolean compareBurgersByIngredients(Burger userBurger, Burger dbBurger) {

        boolean equal = false;

        if (userBurger.getMeat().equals(dbBurger.getMeat())
                && userBurger.getRoasting().equals(dbBurger.getRoasting())
                && userBurger.getBreadType().equals(dbBurger.getBreadType())
                && userBurger.getSpicy().equals(dbBurger.getSpicy())
                && userBurger.getIngredients().equals(dbBurger.getIngredients())
                && userBurger.getBurgerType().equals(dbBurger.getBurgerType())) {
            equal = true;
        }
//        System.out.println(userBurger.getName() + " " + dbBurger.getName());
//        System.out.println("Meat: " + userBurger.getMeat().equals(dbBurger.getMeat()));
//        System.out.println("Roasting: " + userBurger.getRoasting().equals(dbBurger.getRoasting()));
//        System.out.println("BreadType: " + userBurger.getBreadType().equals(dbBurger.getBreadType()));
//        System.out.println("Spicy: " + userBurger.getSpicy().equals(dbBurger.getSpicy()));
//        System.out.println("Ingredients: " + userBurger.getIngredients().equals(dbBurger.getIngredients()));
//        System.out.println("BurgerType: " + userBurger.getBurgerType().equals(dbBurger.getBurgerType()));

        return equal;
    }


    /**
     * Used to convert JSONObject that comes from the constructor
     * into a {@link Burger} object
     *
     * @param jsonObject - income JSON object
     * @return - {@link Burger} object
     */
    public static Burger getBurgerFromJSON(JSONObject jsonObject, Integer number, List<Meat> meatList,
                                           List<BreadType> breadTypeList, List<Sauce> saucesList, List<MiscIngredient> miscIngredientList) {

        //Ingredients list
        ArrayList<String> stringIngredientsList = new ArrayList<>();

        //Sauces list
        ArrayList<String> stringSaucesList = new ArrayList<>();

        //Get roasting
        String roasting = jsonObject.getString("roasting");

        //Get bread
        JSONObject breadObject = jsonObject.getJSONObject("bread");
        String breadName = breadObject.getString("name");
        //int breadPrice = breadObject.getInt("price");

        //Get meat
        JSONObject meatObject = jsonObject.getJSONObject("meat");
        String meatType = meatObject.getString("name");
        //int meatPrice = meatObject.getInt("price");

        //Get sauces
        JSONArray saucesObject = jsonObject.getJSONArray("sauces");
        for (int i = 0; i < saucesObject.length(); i++) {
            JSONObject saucesArrayObject = saucesObject.getJSONObject(i);
            String saucesArrayObjectName = saucesArrayObject.getString("name");
            stringSaucesList.add(saucesArrayObjectName);
        }

        //Get misc
        JSONArray miscObject = jsonObject.getJSONArray("misk");
        for (int i = 0; i < miscObject.length(); i++) {
            JSONObject miscArrayObject = miscObject.getJSONObject(i);
            String miscArrayObjectName = miscArrayObject.getString("name");
            stringIngredientsList.add(miscArrayObjectName);
        }

        //Get spice
        boolean spice = jsonObject.getBoolean("spicy");

        //Get overall price
        int price = jsonObject.getInt("price");

        //PrepareMeat
        Meat currentMeat = null;
        for (Meat meat : meatList){
            if (meatType.equals(meat.getName())) {
                currentMeat = meat;
                break;
            }
        }

        //PrepareBreadType
        BreadType currentBreadType = null;
        for (BreadType breadType : breadTypeList){
            if (breadName.equals(breadType.getName())) {
                currentBreadType = breadType;
                break;
            }
        }
        //Prepare Misc Ingredients
        ArrayList<MiscIngredient> miscIngredientArrayList = new ArrayList<>();

        //Sauces first
        ArrayList<MiscIngredient> sauceArrayList = new ArrayList<>();
        for (String miscString : stringSaucesList) {
            Sauce currentSauce = null;
            for (Sauce sauceOne : saucesList){
                if (miscString.equals(sauceOne.getName())) {
                    currentSauce = sauceOne;
                    break;
                }
            }
            sauceArrayList.add(currentSauce);
        }

        //Then other Ingredients
        ArrayList<MiscIngredient> miscArrayList = new ArrayList<>();
        for (String miscString : stringIngredientsList) {
            MiscIngredient currentMiscIngredient = null;
            for (MiscIngredient miscIngredientOne : miscIngredientList){
                if (miscString.equals(miscIngredientOne.getName())) {
                    currentMiscIngredient = miscIngredientOne;
                    break;
                }
            }
            miscArrayList.add(currentMiscIngredient);
        }

        //Add them all to main ingredient list
        miscIngredientArrayList.addAll(sauceArrayList);
        miscIngredientArrayList.addAll(miscArrayList);

        return new Burger(UUID.randomUUID(), "Your Burger #" + number, currentMeat, getRoasting(roasting), currentBreadType, spice, miscIngredientArrayList, 350, price, "burger-1.png", BurgerType.Custom);
    }

    /**
     * Converts string to enum
     * @param roasting - Income String
     * @return - {@link Roasting} enum
     */
    public static Roasting getRoasting(String roasting) {
        Roasting roastingFinal;
        switch (roasting) {
            case "Rare": roastingFinal = Roasting.Rare;
                break;
            case "Medium Rare": roastingFinal = Roasting.MediumRare;
                break;
            case "Medium": roastingFinal = Roasting.Medium;
                break;
            case "Medium Well": roastingFinal = Roasting.MediumWell;
                break;
            case "Well Done": roastingFinal = Roasting.WellDone;
                break;
            case "None": roastingFinal = Roasting.None;
                break;

            default: roastingFinal = null;
                break;
        }
        return roastingFinal;
    }
}
