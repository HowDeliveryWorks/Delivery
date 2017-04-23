package Delivery.util;

import Delivery.model.CartInfo;

import javax.servlet.http.HttpServletRequest;

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

    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("currentCart");
    }

    public static void storeLastOrderedCartInSession(HttpServletRequest request, CartInfo cartInfo) {
        request.getSession().setAttribute("lastOrderedCart", cartInfo);
    }

    public static CartInfo getLastOrderedCartInSession(HttpServletRequest request) {
        return (CartInfo) request.getSession().getAttribute("lastOrderedCart");
    }
}