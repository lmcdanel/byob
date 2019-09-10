package com.example.byobproject.controllers;


import com.example.byobproject.models.Beer;
import com.example.byobproject.models.data.BeerDao;
import com.example.byobproject.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("byob")
public class BeerController {

    @Autowired
    private BeerDao beerDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("beers", beerDao.findAll());
        model.addAttribute("title", "My Beers");
        model.addAttribute("title", "BYOB");
        return "beer/index";
    }

    @RequestMapping(value = "suggest", method = RequestMethod.GET)
    public String suggest(Model model) {

        model.addAttribute("title", "BYOB");
        return "beer/suggest";
    }

    @RequestMapping(value = "random", method = RequestMethod.GET)
    public String random(Model model) {

        model.addAttribute("title", "BYOB");
        return "beer/random";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddBeerForm(Model model) {
        model.addAttribute("title", "Add Beer");
        model.addAttribute(new Beer());
        return "beer/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddBeerForm(@ModelAttribute @Valid Beer newBeer,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Beer");
        }
        return "beer/add";
    }



    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveBeerForm(Model model) {
        model.addAttribute("beers", beerDao.findAll());
        model.addAttribute("title", "Remove Beer");
        return "beer/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveBeerForm(@RequestParam(value = "ids") int[] ids) {

        for (int id : ids) {
            beerDao.deleteById(id);
        }

        return "redirect:";

    }
}