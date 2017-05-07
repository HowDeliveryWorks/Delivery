package Delivery.util;

import Delivery.enums.BurgerType;
import Delivery.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.UUID;

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

    public static Integer getCustomBurgersNumbersInSession(HttpServletRequest request) {

        // Get CustomBurgerNumber from Session.
        Integer customBurgerCounter = (Integer) request.getSession().getAttribute("customBurgerNumber");

        // If null, create it, else increment.
        if (customBurgerCounter == null) {
            customBurgerCounter = 1;

            // And store to Session.
            request.getSession().setAttribute("customBurgerNumber", customBurgerCounter);

        } else {
            customBurgerCounter++;
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


    /**
     * Used to convert JSONObject that comes from the constructor
     * into a {@link Burger} object
     *
     * @param jsonObject - income JSON object
     * @return - {@link Burger} object
     */
    public static Burger getBurgerFromJSON(JSONObject jsonObject, Integer number) {

        //Ingredients list
        ArrayList<String> ing = new ArrayList<>();

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
            ing.add(saucesArrayObjectName);
        }

        //Get spice
        boolean spice = jsonObject.getBoolean("spicy");

        //Get overall price
        int price = jsonObject.getInt("price");

        Burger burger = new Burger(UUID.randomUUID(), "Custom Burger #" + number, getMeatType(meatType), getRoasting(roasting), getBreadType(breadName), spice, ing, 350, price, "burger-1.png", BurgerType.Custom);
        return burger;
    }

    /**
     * Converts string to enum
     * @param bread - Income String
     * @return - {@link BreadType} enum
     */
    public static BreadType getBreadType(String bread) {
        BreadType breadType = null;
        switch (bread) {
            case "White Bun": breadType = BreadType.WihiteBread;
                break;
            case "Black Bun": breadType = BreadType.BlackBread;
                break;

            default: breadType = null;
                break;
        }
        return breadType;
    }

    /**
     * Converts string to enum
     * @param meat - Income String
     * @return - {@link MeatType} enum
     */
    public static MeatType getMeatType(String meat) {
        MeatType meatType = null;
        switch (meat) {
            case "Chicken": meatType = MeatType.Chicken;
                break;
            case "Beef": meatType = MeatType.Beef;
                break;
            case "Pork": meatType = MeatType.Pork;
                break;
            case "Veal": meatType = MeatType.Veal;
                break;
            case "Falafel": meatType = MeatType.Falafel;
                break;

            default: meatType = null;
                break;
        }
        return meatType;
    }

    public static Roasting getRoasting(String roasting) {
        Roasting roastingFinal = null;
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

            default: roastingFinal = null;
                break;
        }
        return roastingFinal;
    }
}
