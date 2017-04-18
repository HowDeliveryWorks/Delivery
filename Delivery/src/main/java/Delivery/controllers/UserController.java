package Delivery.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by igor on 16.04.17.
 */
@RestController
public class UserController {
    @GetMapping("/register")
    public ModelAndView registration(){
        ModelAndView model = new ModelAndView();
        model.setViewName("welcomePage");
        return model;
    }

}
