package Delivery.controllers;

import Delivery.DAO.*;
import Delivery.DeliveryApplication;
import Delivery.entity.*;
import Delivery.enums.BurgerType;
import Delivery.model.*;
import Delivery.DAO.SequenceDAO;
import Delivery.mail.ApplicationMailer;
import Delivery.util.Utils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by igor on 08.04.17.
 */
@Controller
public class BurgersController {

    @Autowired
    private BurgersDAO daoBurgers;

    @Autowired
    private OrdersDAO ordersDAO;

    @Autowired
    private MiscIngredientsDAO daoMiscIngredients;

    @Autowired
    private MeatDAO daoMeat;

    @Autowired
    private SaucesDAO daoSauces;

    @Autowired
    private BreadTypeDAO daoBreadType;

    @Autowired
    private SequenceDAO sequenceDAO;

    @Autowired
    private UserDAO daoUsers;

    private static final String ORDER_SEQ_KEY = "order";

    @GetMapping("/contacts")
    public String contacts(HttpServletRequest request, Model model){
        CartInfo cartInfo = Utils.getCartInSession(request);
        model.addAttribute("feedback", new Feedback());
        model.addAttribute("user", new User());
        return "contacts";
    }

    @PostMapping("/contacts")
    public String contactsForm(@ModelAttribute Feedback feedback, Model model){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DeliveryApplication.class);
        ApplicationMailer am = (ApplicationMailer) ctx.getBean("mailService");
        ExecutorService exec = Executors.newFixedThreadPool(1);
        exec.submit(() -> am.sendMail("howdeliveryworks@gmail.com","Feedback", feedback.toString()));
        exec.shutdown();
        return "index";
    }

    @GetMapping("/menu")
    public String getAllBurgers(HttpServletRequest request, Model model){
        model.addAttribute("user", new User());
        model.addAttribute("burgers", daoBurgers.findByBurgerType(BurgerType.PreOrdered));
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
        model.addAttribute("user", new User());
        CartInfo cartInfo = Utils.getCartInSession(request);
        return "cart";
    }

    @GetMapping("/sorry")
    public String sorry(HttpServletRequest request, Model model){
        model.addAttribute("user", new User());

        CartInfo cartInfo = Utils.getCartInSession(request);
        return "sorry";
    }

    /**
     * Profile mapping
     * Get current user in session. If doesn't exist - create a mock.
     * When normal logic will implements - we sould check for a user,
     * if there are no - SUGGEST TO LOG IN OR SIGN IN.
     */
    @GetMapping("/profile")
    public String profile(HttpServletRequest request, Model model){
        CartInfo cartInfo = Utils.getCartInSession(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getName() != "anonymousUser"){

            //Get current user
            String eMail = auth.getName();
            User currentUser = daoUsers.findByEmail(eMail);
            List<BurgerUserRating> orderedBurgers = currentUser.getOrderedBurgers();
            model.addAttribute("orderedBurgers", orderedBurgers);
        } else {
            return "sorry";
        }

//        User currentUser = daoUsers.findByName("Max");
//        List<BurgerUserRating> orderedBurgers = currentUser.getOrderedBurgers();
//        model.addAttribute("orderedBurgers", orderedBurgers);

        return "profile";
    }

    @RequestMapping({ "/rateBurger" })
    public String rateBurgerHandler(HttpServletRequest request, Model model, //
                                    @RequestParam(value = "id", defaultValue = "") UUID id,
                                    @RequestParam(value = "rating", defaultValue = "") int rating) {
        model.addAttribute("user", new User());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String eMail = auth.getName();
        User currentUser = daoUsers.findByEmail(eMail);

        List<BurgerUserRating> burgerUserRatingList = currentUser.getOrderedBurgers();

        BurgerUserRating burMain = null;
        for (BurgerUserRating bur : burgerUserRatingList) {
            if (bur.getId().equals(id)) {
                burMain = bur;
            }
        }

        burgerUserRatingList.remove(burMain);
        burMain.setRating(rating);
        burgerUserRatingList.add(burMain);

        currentUser.setOrderedBurgers(burgerUserRatingList);
        daoUsers.save(currentUser);

        return "forward:/profile";
    }

    @GetMapping("/cart2")
    public String cart2(HttpServletRequest request, Model model){
        model.addAttribute("user", new User());

        CartInfo cartInfo = Utils.getCartInSession(request);
        model.addAttribute("order", new Order());
        return "cart2";
    }

    @PostMapping("/cart2")
    public String ordersForm(HttpSession session, HttpServletRequest request, @ModelAttribute Order order, Model model){
        model.addAttribute("user", new User());

        ApplicationContext ctx = new AnnotationConfigApplicationContext(DeliveryApplication.class);
        ApplicationMailer am = (ApplicationMailer) ctx.getBean("mailService");
        CartInfo cartInfo = Utils.getCartInSession(request);
        order.setBurgers(cartInfo.getCartLines());
        order.setId(sequenceDAO.getNextSequenceId(ORDER_SEQ_KEY));
        ordersDAO.insert(order);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName() != "anonymousUser") {
            String eMail = auth.getName();
            User currentUser = daoUsers.findByEmail(eMail);
            List<Order> userOrderList = currentUser.getOrders();
            userOrderList.add(order);
            currentUser.setOrders(userOrderList);
            daoUsers.save(currentUser);
        }

        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.submit(() -> am.sendMail(order.getEmail(),"Your Burgers Order", am.customerText(order)));
        exec.submit(() -> am.sendMail("howdeliveryworks@gmail.com","We got a new order!", am.ownerText(order)));
        exec.shutdown();
        Utils.removeCartInSession(request);
        session.invalidate();
        return "redirect:/menu";
    }


    @RequestMapping({ "/buyBurger" })
    public String listProductHandler(HttpServletRequest request, Model model, //
                                     @RequestParam(value = "id", defaultValue = "") UUID id) {
        model.addAttribute("user", new User());

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
        model.addAttribute("user", new User());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

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

        // Check if there logged in user, to write down his burger in db
        if (auth.getName() != "anonymousUser"){
            String eMail = auth.getName();
            User currentUser = daoUsers.findByEmail(eMail);
//            User currentUser = daoUsers.findByName("Max");
            List<BurgerUserRating> burgerUserRatingList = currentUser.getOrderedBurgers();

            if (burgerUserRatingList != null) {
                // Check all burgers on equality; For each User's ordered burger
                boolean equal = false;
                for (BurgerUserRating bur : burgerUserRatingList) {
                    Burger userBurger = bur.getBurger();
                    // Compare 2 burgers on equality
                    boolean eq = Utils.compareBurgersByIngredients(userBurger, burger);
                    if (eq) {
                        equal = eq;
                    }
                }

                boolean addToDb = false;
                // If there found no equal - add to db
                if (equal == false) {
                    addToDb = true;
                }
                if (addToDb) {
                    // Create new bur
                    BurgerUserRating customBur = new BurgerUserRating(UUID.randomUUID(), burger, 0);
                    // Add it to existing User's list
                    burgerUserRatingList.add(customBur);
                    // Rewrite list
                    currentUser.setOrderedBurgers(burgerUserRatingList);
                    // Update user
                    daoUsers.save(currentUser);
                }
            } else {
                BurgerUserRating customBur = new BurgerUserRating(UUID.randomUUID(), burger, 0);
                burgerUserRatingList.add(customBur);
                currentUser.setOrderedBurgers(burgerUserRatingList);
                daoUsers.save(currentUser);
            }
        }
        // Redirect to menu page.
        return "forward:/menu";
    }

    @RequestMapping({ "/removeBurger" })
    public String listProductUnHandler(HttpServletRequest request, Model model, //
                                     @RequestParam(value = "id", defaultValue = "") UUID id) {
        model.addAttribute("user", new User());

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
        model.addAttribute("user", new User());

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

    // GET: Enter customer information.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
        model.addAttribute("user", new User());


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

    @GetMapping("/constructor")
    public String constructor(HttpServletRequest request, Model model){
        model.addAttribute("user", new User());

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

        Integer customBurgerCounter = Utils.getCustomBurgersNumberInSession(request);
        List<Meat> meatList = daoMeat.findAll();
        List<BreadType> breadTypeList = daoBreadType.findAll();
        List<Sauce> saucesList = daoSauces.findAll();
        List<MiscIngredient> miscIngredientsList = daoMiscIngredients.findAll();
        Burger burger = Utils.getBurgerFromJSON(jsonObject, customBurgerCounter, meatList, breadTypeList, saucesList, miscIngredientsList);
        daoBurgers.insert(burger);

        if (burger != null) {
            CartInfo cartInfo = Utils.getCartInSession(request);
            BurgerInfo burgerInfo = new BurgerInfo(burger);

            cartInfo.addBurger(burgerInfo, 1);

            // Check if there logged in user, to write down his burger in db
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth.getName() != "anonymousUser"){
                String eMail = auth.getName();
                User currentUser = daoUsers.findByEmail(eMail);
//                User currentUser = daoUsers.findByName("Max");
                List<BurgerUserRating> burgerUserRatingList = currentUser.getOrderedBurgers();

                if (burgerUserRatingList != null) {
                    // Check all burgers on equality; For each User's ordered burger
                    boolean equal = false;
                    for (BurgerUserRating bur : burgerUserRatingList) {
                        Burger userBurger = bur.getBurger();
                        // Compare 2 burgers on equality
                        boolean eq = Utils.compareBurgersByIngredients(userBurger, burger);
                        if (eq) {
                            equal = eq;
                        }
                    }

                    boolean addToDb = false;
                    // If there found no equal - add to db
                    if (equal == false) {
                        addToDb = true;
                    }
                    if (addToDb) {
                        /** Create new bur **/

                        // Check how much custom burgers
                        int customCounter = 1;
                        for (BurgerUserRating cbur : burgerUserRatingList){
                            if (cbur.getBurger().getBurgerType().equals(BurgerType.Custom)){
                                customCounter++;
                            }
                        }
                        // Name of custom burger
                        burger.setName("Custom Burger #" + customCounter);
                        BurgerUserRating customBur = new BurgerUserRating(UUID.randomUUID(), burger, 0);
                        // Add it to existing User's list
                        burgerUserRatingList.add(customBur);
                        // Rewrite list
                        currentUser.setOrderedBurgers(burgerUserRatingList);
                        // Update user
                        daoUsers.save(currentUser);
                    }
                } else {
                    burger.setName("Custom Burger #1");
                    BurgerUserRating customBur = new BurgerUserRating(UUID.randomUUID(), burger, 0);
                    burgerUserRatingList.add(customBur);
                    currentUser.setOrderedBurgers(burgerUserRatingList);
                    daoUsers.save(currentUser);
                }
            }
        }
        return "forward:/cart";
    }
  
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model){
        CartInfo cartInfo = Utils.getCartInSession(request);

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getName());

        model.addAttribute("user", new User());
        return "index";
    }
}
