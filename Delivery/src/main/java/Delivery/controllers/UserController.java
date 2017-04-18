package Delivery.controllers;

import Delivery.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by igor on 15.04.17.
 */
@RestController
public class UserController {

    @GetMapping("/register")
    public String viewRegistration(Map<String, Object> model){
        User userForm = new User();
        model.put("userForm", userForm);

        List<String> professionList = new ArrayList<>();
        professionList.add("Developer");
        professionList.add("Designer");
        professionList.add("IT Manager");
        model.put("professionList", professionList);

        return "Registration";
    }
}
