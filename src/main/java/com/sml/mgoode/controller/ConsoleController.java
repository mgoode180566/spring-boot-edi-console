package com.sml.mgoode.controller;

import com.sml.mgoode.entity.LookupCondition;
import com.sml.mgoode.entity.Program;
import com.sml.mgoode.service.LookupConditionService;
import com.sml.mgoode.service.ProgramService;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("user", principal.getName());
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
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("config");
        modelAndView.addObject("list", lookupConditions);
        modelAndView.addObject("id", id);
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

}
