package com.example.demo.Controller;


import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @RequestMapping("/")
    public String index(){

        return "navbar";
    }



    @RequestMapping("/login")
    public String login(){

        return "myLoginPage";
    }




    @RequestMapping("/access-denied")
    public String accessDenied(){

        return "accessDenied";
    }

    @GetMapping("/register")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "Registration";
    }


    @PostMapping("/register")
    public String processUser(@Valid @ModelAttribute("user") User user, @RequestParam("selectedRole") String selectedRole, BindingResult result, Model model){
        if(result.hasErrors()){
            return "Registration";
        }
        System.out.println(selectedRole);

        switch (selectedRole)
        {
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



}
