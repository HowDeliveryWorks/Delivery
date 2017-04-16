package Delivery.controllers;

import Delivery.DAO.BurgersDAO;
import Delivery.model.Burger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by igor on 08.04.17.
 */
@RestController
public class BurgersController {

    @Autowired
    private BurgersDAO dao;

    @GetMapping("/menu")
    public String getAllBurgers(){
        return dao.findAll().toString();
    }
}
