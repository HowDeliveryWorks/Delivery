package Delivery.controllers;

import Delivery.DAO.*;
import Delivery.DeliveryApplication;
import Delivery.entity.BreadType;
import Delivery.entity.Burger;
import Delivery.entity.Meat;
import Delivery.entity.Sauce;
import Delivery.enums.BurgerType;
import Delivery.enums.Roasting;
import Delivery.model.*;
import Delivery.services.ApplicationMailer;
import Delivery.util.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by igor on 08.04.17.
 */
@Controller
public class BurgersController {

    @Autowired
    private BurgersDAO daoBurgers;

    @Autowired
    private MiscIngredientsDAO daoMiscIngredients;

    @Autowired
    private MeatDAO daoMeat;

    @Autowired
    private SaucesDAO daoSauces;

    @Autowired
    private BreadTypeDAO daoBreadType;

    @GetMapping("/contacts")
    public String contacts(HttpServletRequest request, Model model){
        CartInfo cartInfo = Utils.getCartInSession(request);
        model.addAttribute("feedback", new Feedback());
        return "contacts";
    }

    @PostMapping("/contacts")
    public String contactsForm(@ModelAttribute Feedback feedback, Model model){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DeliveryApplication.class);
        ApplicationMailer am = (ApplicationMailer) ctx.getBean("mailService");
        try
        {
            am.sendMail("howdeliveryworks@gmail.com","Feedback", feedback.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/menu")
    public String getAllBurgers(HttpServletRequest request, Model model){

        model.addAttribute("burgers", daoBurgers.findByBurgerType(BurgerType.PreOrdered));
//        List a = dao.findAll();
        CartInfo cartInfo = Utils.getCartInSession(request);
        return "store";
    }

    @GetMapping("/menuUpdate")
    public String getAllUpdatedBurgers(HttpSession session){
        session.invalidate();
        return "redirect:/menu";
    }

    @GetMapping("/cart")
    public String cart(HttpServletRequest request, Model model){
        CartInfo cartInfo = Utils.getCartInSession(request);
        return "cart";
    }

    @GetMapping("/cart2")
    public String cart2(HttpServletRequest request, Model model){
        CartInfo cartInfo = Utils.getCartInSession(request);
        model.addAttribute("order", new Order());
        return "cart2";
    }

    @PostMapping("/cart2")
    public String ordersForm(HttpServletRequest request, @ModelAttribute Order order, Model model){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DeliveryApplication.class);
        ApplicationMailer am = (ApplicationMailer) ctx.getBean("mailService");
        CartInfo cartInfo = Utils.getCartInSession(request);
        order.setBurgers(cartInfo.getCartLines());
        try
        {
            am.sendMail(order.getEmail(),"Your Burgers Order", am.customerText(order));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            am.sendMail("howdeliveryworks@gmail.com","We got a new order!", am.ownerText(order));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Utils.removeCartInSession(request);
        return "redirect:/menu";
    }


    @RequestMapping({ "/buyBurger" })
    public String listProductHandler(HttpServletRequest request, Model model, //
                                     @RequestParam(value = "id", defaultValue = "") UUID id) {
        Burger burger = null;
        if (id != null) {
            burger = daoBurgers.findById(id);
        }
        if (burger != null) {

            // Cart info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);

            BurgerInfo burgerInfo = new BurgerInfo(burger);

            cartInfo.addBurger(burgerInfo, 1);
        }
        // Redirect to cart page.
        return "forward:/cart";
    }

    @RequestMapping({ "/buyBurgerMenu" })
    public String listProductHandlerMenu(HttpServletRequest request, Model model, //
                                     @RequestParam(value = "id", defaultValue = "") UUID id) {
        Burger burger = null;
        if (id != null) {
            burger = daoBurgers.findById(id);
        }
        if (burger != null) {

            // Cart info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);

            BurgerInfo burgerInfo = new BurgerInfo(burger);

            cartInfo.addBurger(burgerInfo, 1);
        }
        // Redirect to cart page.
        return "forward:/menu";
    }

    @RequestMapping({ "/removeBurger" })
    public String listProductUnHandler(HttpServletRequest request, Model model, //
                                     @RequestParam(value = "id", defaultValue = "") UUID id) {
        Burger burger = null;
        if (id != null) {
            burger = daoBurgers.findById(id);
        }
        if (burger != null) {

            // Cart info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);

            BurgerInfo burgerInfo = new BurgerInfo(burger);

            cartInfo.addBurger(burgerInfo, -1);
        }
        // Redirect to cart page.
        return "forward:/cart";
    }

    @RequestMapping({ "/shoppingCartRemoveProduct" })
    public String removeProductHandler(HttpServletRequest request, Model model, //
                                       @RequestParam(value = "id", defaultValue = "") UUID id) {
        Burger burger = null;
        if (id != null) {
            burger = daoBurgers.findById(id);
            System.out.println(burger.toString());
        }
        if (burger != null) {

            // Cart Info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);

            BurgerInfo burgerInfo = new BurgerInfo(burger);

            cartInfo.removeBurger(burgerInfo);

            //Deletes burger from DB if it is custom
            if (burger.getBurgerType().equals(BurgerType.Custom)) {
                daoBurgers.delete(burger);
            }


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


    @GetMapping("/constructor")
    public String constructor(HttpServletRequest request, Model model){
        model.addAttribute("ingredients", daoMiscIngredients.findAll());
        model.addAttribute("meat", daoMeat.findAll());
        model.addAttribute("breadTypes", daoBreadType.findAll());
        model.addAttribute("sauces", daoSauces.findAll());
        CartInfo cartInfo = Utils.getCartInSession(request);
        return "constructor";
    }

    @RequestMapping(value = "/newCustomBurger", method=RequestMethod.POST)
    public String processForm(HttpServletRequest request,
                              @RequestParam(value="addtocartname") String json) {
        System.out.println(json);
        JSONObject jsonObject = new JSONObject(json);

        Integer customBurgerCounter = Utils.getCustomBurgersNumbersInSession(request);
        List<Meat> meatList = daoMeat.findAll();
        List<BreadType> breadTypeList = daoBreadType.findAll();
        List<Sauce> saucesList = daoSauces.findAll();
        Burger burger = Utils.getBurgerFromJSON(jsonObject, customBurgerCounter, meatList, breadTypeList, saucesList);
        daoBurgers.insert(burger);

        if (burger != null) {
            CartInfo cartInfo = Utils.getCartInSession(request);
            BurgerInfo burgerInfo = new BurgerInfo(burger);

            cartInfo.addBurger(burgerInfo, 1);
        }
        return "forward:/cart";
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

        return "forward:/cart2";
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
//            return "cart2";
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
    public String index(HttpServletRequest request, Model model){
        CartInfo cartInfo = Utils.getCartInSession(request);
        //request.getSession().setAttribute("currentCart", cartInfo);

        return "index";
    }
}
