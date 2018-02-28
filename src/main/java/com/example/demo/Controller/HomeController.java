package com.example.demo.Controller;


import com.example.demo.Model.PledgeItem;
import com.example.demo.Repositories.PledgeRepository;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;

@Controller
public class HomeController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PledgeRepository pledgeRepository;


    @RequestMapping("/")
    public String index() {

        return "listPage";
    }


    @RequestMapping("/login")
    public String login() {

        return "myLoginPage";
    }


    @RequestMapping("/access-denied")
    public String accessDenied() {

        return "accessDenied";
    }

    @GetMapping("/register")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "Registration";
    }


    @PostMapping("/register")
    public String processUser(@Valid @ModelAttribute("user") User user, @RequestParam("selectedRole") String selectedRole, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Registration";
        }
        System.out.println(selectedRole);

        switch (selectedRole) {
            case "ADMIN":
                user.addRole(roleRepository.findByRolename("ADMIN"));

            case "USER":
                user.addRole(roleRepository.findByRolename("USER"));


        }


        userRepository.save(user);
        //  userService.saveUser(user);
        model.addAttribute("message", "User account Successfully Created");
        return "redirect:/login";
    }


    @GetMapping("/pledgeitem")
    public String pedgeItem(Model model) {

        model.addAttribute("item", new PledgeItem());

        return "itemform";
    }


    @PostMapping("/pledgeitem")
    public String processPledge(@Valid @ModelAttribute("item") PledgeItem item,
                                BindingResult result, Model model, Authentication auth) {

        if (result.hasErrors())
            return "itemform";
        pledgeRepository.save(item);
        User currentuser= userRepository.findByUserName(auth.getName());
        currentuser.addItem(item);
        userRepository.save(currentuser);
        model.addAttribute("item", pledgeRepository);
        return "redirect:/";
    }


    @RequestMapping("/displaypotluckmy")
    public String applicantResume(Model model, Authentication auth) {

        Collection<PledgeItem> myitems=pledgeRepository.findByPledgingusers(userRepository.findByUserName(auth.getName()));

        model.addAttribute("items", myitems);

        return "listPage";
    }

    @RequestMapping("/displaypotluckall")
    public String applicantResume(Model model) {

        Iterable<PledgeItem> allitems=pledgeRepository.findAll();
        model.addAttribute("items",allitems);

        return "listPage";
    }
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id){
        PledgeItem myitem=pledgeRepository.findOne(id);

        pledgeRepository.delete(myitem);

        return "redirect:/";
    }


}