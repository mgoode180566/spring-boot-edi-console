package com.sml.mgoode.controller;

import com.sml.mgoode.entity.Lookup;
import com.sml.mgoode.entity.LookupCondition;
import com.sml.mgoode.entity.ProductEntry;
import com.sml.mgoode.entity.Program;
import com.sml.mgoode.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelgoode on 17/01/2018.
 */
@Controller
public class ConsoleController {

    @Autowired
    ProgramService programService;

    @Autowired
    LookupConditionService lookupConditionService;

    @Autowired
    LookupService lookupService;

    @Autowired
    ProductService productService;

    @Autowired
    FavouriteService favouriteService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("user", principal.getName());
        modelAndView.addObject( "list", favouriteService.getFavourites());
        return modelAndView;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "test-css";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/config")
    public ModelAndView programConfig( @ModelAttribute("id") int id ) {
        ArrayList<LookupCondition> lookupConditions = lookupConditionService.getLookupConditions(id);
        ArrayList<Lookup> lookups = lookupService.getLookup(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("config");
        modelAndView.addObject("list", lookupConditions);
        modelAndView.addObject("id", id);
        modelAndView.addObject("lookups", lookups);
        return modelAndView;
    }

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public String printUser(ModelMap model, Principal principal) {
        String name = principal.getName(); //get logged in username
        model.addAttribute("username", name);
        return "hello";
    }

    @RequestMapping(value = "/locked", method= RequestMethod.GET)
    public ModelAndView statusLocked() {
        System.out.println("ConsoleController:process");
        List<Program> list = programService.getAllPrograms(true);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("status");
        return modelAndView;
    }

    @RequestMapping(value = "/all", method= RequestMethod.GET)
    public ModelAndView statusAll() {
        System.out.println("ConsoleController:process");
        List<Program> list = programService.getAllPrograms();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("status");
        return modelAndView;
    }

    @RequestMapping(value = "/unlocked", method= RequestMethod.GET)
    public ModelAndView statusUnLocked() {
        System.out.println("ConsoleController:process");
        List<Program> list = programService.getAllPrograms(false);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("status");
        return modelAndView;
    }

    @RequestMapping(value = "/favourites", method= RequestMethod.GET)
    public ModelAndView favourites(Principal principal) {
        System.out.println("ConsoleController:process");
        List<Program> list = programService.getFavourites();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.addObject("user", principal.getName());
        modelAndView.setViewName("status");
        return modelAndView;
    }

    @RequestMapping(value = "/products", method= RequestMethod.GET)
    public ModelAndView products(Principal principal, @ModelAttribute("id") int id) {
        System.out.println("ConsoleController:Products");
        List<ProductEntry> list = productService.getProducts(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.addObject("user", principal.getName());
        modelAndView.setViewName("products");
        return modelAndView;
    }

    @RequestMapping(value = "/saveproduct", method= RequestMethod.POST)
    public String saveProduct(Principal principal, ProductEntry productEntry) {
        System.out.println("ConsoleController:Products");
        productService.saveProduct(productEntry);
        return "redirect:/products?id=" + productEntry.getLookupId();
    }

    @RequestMapping(value = "/addproduct", method= RequestMethod.POST)
    public ModelAndView addProduct(Principal principal, @ModelAttribute("id") int id) {
        System.out.println("ConsoleController:addProduct");
        ProductEntry productEntry = new ProductEntry();
        productEntry.setLookupId(id);
        return new ModelAndView("addproduct","command", productEntry);
    }

    @RequestMapping(value = "/deleteproduct", method= RequestMethod.GET)
    public String deleteProduct(Principal principal, @ModelAttribute("programid") int programId, @ModelAttribute("id") long id) {
        System.out.println("ConsoleController:deleteProduct");
        productService.deleteProduct( programId, id );
        // go to the products for current program
        return "redirect:/products?id=" + programId;
    }

    @RequestMapping(value = "/programs", method = RequestMethod.GET)
    public ModelAndView showPrograms() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("programs");
        modelAndView.addObject("list", favouriteService.getFavourites());
        return modelAndView;
    }
}
