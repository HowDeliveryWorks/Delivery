package Delivery.controllers;

import Delivery.DAO.BurgersDAO;
import Delivery.model.Burger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by igor on 08.04.17.
 */
@Controller
public class BurgersController {

    @Autowired
    private BurgersDAO dao;

    @GetMapping("/menu")
    public String getAllBurgers(Model model){
        model.addAttribute("burgers", dao.findAll());
        List a = dao.findAll();
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
}
