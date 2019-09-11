package com.example.byobproject.controllers;

import com.example.byobproject.models.User;
import com.example.byobproject.models.data.BeerDao;
import com.example.byobproject.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private BeerDao beerDao;

    @Autowired
    private UserDao userDao;
    @RequestMapping("")
    public String displayUserHome(Model model, @ModelAttribute User user) {
        model.addAttribute("beers", beerDao.findAll());
        model.addAttribute("user", user);
        model.addAttribute("username", user.getUsername());

        return "user/index";

    }
    @RequestMapping("add")
    public String displayAddForm(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddForm(Model model, @ModelAttribute @Valid User newUser, Errors errors, String verify) {

        if (errors.hasErrors()) {
            model.addAttribute(newUser);
            return "user/add";
        }

        if(newUser.getPassword().equals(verify)) {
            model.addAttribute("user", newUser);
            userDao.save(newUser);
            return "redirect:";

        }
        model.addAttribute("errormessage","Passwords do not match");
        return "user/add";

    }
}