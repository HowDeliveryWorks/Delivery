package Delivery.controllers;

import Delivery.DAO.BurgersDAO;
import Delivery.model.Burger;
import Delivery.model.BurgerInfo;
import Delivery.model.CartInfo;
import Delivery.model.CustomerInfo;
import Delivery.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * Created by igor on 08.04.17.
 */
@Controller
public class BurgersController {

    @Autowired
    private BurgersDAO dao;

    @GetMapping("/menu")
    public String getAllBurgers(HttpServletRequest request, Model model){
        model.addAttribute("burgers", dao.findAll());
        List a = dao.findAll();

        CartInfo cartInfo = Utils.getCartInSession(request);

        return "/web/store";
    }

    @GetMapping("/cart")
    public String cart(Model model){
        return "/web/cart";
    }

    @GetMapping("/cart2")
    public String cart2(Model model){
        return "/web/cart2";
    }

    @RequestMapping({ "/buyBurger" })
    public String listProductHandler(HttpServletRequest request, Model model, //
                                     @RequestParam(value = "id", defaultValue = "") UUID id) {
        Burger burger = null;
        if (id != null) {
            burger = dao.findById(id);
        }
        if (burger != null) {

            // Cart info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);

            BurgerInfo burgerInfo = new BurgerInfo(burger);

            cartInfo.addBurger(burgerInfo, 1);
            //model.addAttribute("currentCart", cartInfo);
        }
        // Redirect to cart page.
        return "forward:/cart";
    }

    @RequestMapping({ "/shoppingCartRemoveProduct" })
    public String removeProductHandler(HttpServletRequest request, Model model, //
                                       @RequestParam(value = "id", defaultValue = "") UUID id) {
        Burger burger = null;
        if (id != null) {
            burger = dao.findById(id);
            System.out.println(burger.toString());
        }
        if (burger != null) {

            // Cart Info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);

            BurgerInfo burgerInfo = new BurgerInfo(burger);

            cartInfo.removeBurger(burgerInfo);
            //model.addAttribute("currentCart", cartInfo);

        }
        // Redirect to cart page.
        return "forward:/cart";
    }

    // POST: Update quantity of products in cart.
    @RequestMapping(value = { "/cart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, //
                                        Model model, //
                                        @ModelAttribute("cartForm") CartInfo cartForm) {

        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);

        // Redirect to shoppingCart page.
        return "redirect:/cart";
    }

    // GET: Enter customer information.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {

        CartInfo cartInfo = Utils.getCartInSession(request);
        //model.addAttribute("currentCart", cartInfo);

        // Cart is empty.
        if (cartInfo.isEmpty()) {

            // Redirect to cart page.
            return "redirect:/cart";
        }

        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        if (customerInfo == null) {
            customerInfo = new CustomerInfo();
        }

        model.addAttribute("customerForm", customerInfo);

        return "/web/cart2";
    }

//    // POST: Save customer information.
//    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
//    public String shoppingCartCustomerSave(HttpServletRequest request, //
//                                           Model model, //
//                                           @ModelAttribute("customerForm") @Validated CustomerInfo customerForm, //
//                                           BindingResult result, //
//                                           final RedirectAttributes redirectAttributes) {
//
//        // If has Errors.
//        if (result.hasErrors()) {
//            customerForm.setValid(false);
//            // Forward to reenter customer info.
//            return "/web/cart2";
//        }
//
//        customerForm.setValid(true);
//        CartInfo cartInfo = Utils.getCartInSession(request);
//
//        cartInfo.setCustomerInfo(customerForm);
//
//        // Redirect to Confirmation page.
//        return "redirect:/shoppingCartConfirmation";
//    }
//
//    // GET: Review Cart to confirm.
//    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
//    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
//        CartInfo cartInfo = Utils.getCartInSession(request);
//
//        // Cart have no products.
//        if (cartInfo.isEmpty()) {
//            // Redirect to shoppingCart page.
//            return "redirect:/shoppingCart";
//        } else if (!cartInfo.isValidCustomer()) {
//            // Enter customer info.
//            return "redirect:/shoppingCartCustomer";
//        }
//
//        return "shoppingCartConfirmation";
//    }

//    // POST: Send Cart (Save).
//    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
//    // Avoid UnexpectedRollbackException (See more explanations)
//    @Transactional(propagation = Propagation.NEVER)
//    public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
//        CartInfo cartInfo = Utils.getCartInSession(request);
//
//        // Cart have no products.
//        if (cartInfo.isEmpty()) {
//            // Redirect to shoppingCart page.
//            return "redirect:/shoppingCart";
//        } else if (!cartInfo.isValidCustomer()) {
//            // Enter customer info.
//            return "redirect:/shoppingCartCustomer";
//        }
//        try {
//            dao.saveOrder(cartInfo);
//        } catch (Exception e) {
//            // Need: Propagation.NEVER?
//            return "shoppingCartConfirmation";
//        }
//        // Remove Cart In Session.
//        Utils.removeCartInSession(request);
//
//        // Store Last ordered cart to Session.
//        Utils.storeLastOrderedCartInSession(request, cartInfo);
//
//        // Redirect to successful page.
//        return "redirect:/shoppingCartFinalize";
//    }

//    @RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
//    public String shoppingCartFinalize(HttpServletRequest request, Model model) {
//
//        CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
//
//        if (lastOrderedCart == null) {
//            return "redirect:/shoppingCart";
//        }
//
//        return "shoppingCartFinalize";
//    }

    @GetMapping("/")
    public String main(HttpServletRequest request, Model model){

        CartInfo cartInfo = Utils.getCartInSession(request);
        //request.getSession().setAttribute("currentCart", cartInfo);

        return "/web/index";
    }
}
