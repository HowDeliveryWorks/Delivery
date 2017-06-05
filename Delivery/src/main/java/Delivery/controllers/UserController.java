package Delivery.controllers;

import Delivery.entity.User;
import Delivery.services.SecurityService;
import Delivery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by igor on 22.05.17.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/login")
    public String contactsForm(Model model, HttpServletRequest request){
        System.out.print("Success login \n");
        return "redirect:" + request.getHeader("referer");
    }

    // Login form with error
    @RequestMapping(value = "/login-error", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void loginError(Model model, HttpServletRequest request) {
        System.out.print("wrong login \n");
    }

    @RequestMapping("/login-success")
    public String loginSuccess(Model model, HttpServletRequest request) {
        System.out.print("success login");
        return "sorry";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public String registration(@ModelAttribute User user, Model model) {
        if(userService.save(user) == false){
            return "error";
        }
        System.out.println("user added: " + user.getUsername());

        securityService.autologin(user.getEmail(), user.getPasswordConfirm());
        return "success";
    }

    @GetMapping(value = "/registration")
    public String regG(){
        return "sorry";
    }
}
