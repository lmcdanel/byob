package com.example.byobproject.controllers;

import com.example.byobproject.models.AddBeerForm;
import com.example.byobproject.models.Beer;
import com.example.byobproject.models.User;
import com.example.byobproject.models.data.BeerDao;
import com.example.byobproject.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private BeerDao beerDao;

    @Autowired
    private UserDao userDao;
    @RequestMapping("")
    public String displayUsers(Model model, @ModelAttribute User user) {
       model.addAttribute("users", userDao.findAll());

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
            return "redirect:view/" + newUser.getId();

        }
        model.addAttribute("errormessage","Passwords do not match");
        return "user/add";

    }

    @RequestMapping(value = "view/{userId}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int userId) {

        User user = userDao.findById(userId).orElse(null);
        model.addAttribute("title", user.getUsername());
        model.addAttribute("beers", user.getBeers());
        model.addAttribute("userId", user.getId());

        return "user/view";
    }


    @RequestMapping(value = "add-beer/{userId}", method = RequestMethod.GET)
    public String addBeer(Model model, @PathVariable int userId) {

        User user = userDao.findById(userId).orElse(null);

        AddBeerForm form = new AddBeerForm(beerDao.findAll(),user);

        model.addAttribute("title", "Add Beer to " + user.getUsername() + "'s collection");
        model.addAttribute("form", form);

        return "user/add-item";
    }

    @RequestMapping(value= "add-beer/{userId}", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddBeerForm form, Errors errors, @PathVariable int userId) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "user/add-item";
        }

        Beer theBeer = beerDao.findById(form.getBeerId()).orElse(null);
        User theUser = userDao.findById(form.getUserId()).orElse(null);

        theUser.addItem(theBeer);
        userDao.save(theUser);

        return "redirect:/user/view/" + theUser.getId();

    }

}