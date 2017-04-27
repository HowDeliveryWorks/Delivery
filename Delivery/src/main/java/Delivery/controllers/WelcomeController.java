package Delivery.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by igor on 18.04.17.
 */
@Controller
public class WelcomeController {

    @GetMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "/web/hello";
    }

    @GetMapping("/store")
    public String store(Model model) {
        return "/web/store";
    }

}
