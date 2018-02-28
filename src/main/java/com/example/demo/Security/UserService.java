package com.example.demo.Security;


import com.example.demo.Model.PledgeItem;
import com.example.demo.Model.User;
import com.example.demo.Repositories.PledgeRepository;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {



    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PledgeRepository pledgeRepository;


    public UserService(UserRepository userRepository){this.userRepository=userRepository;}


    public User findByUsername(String username){return userRepository.findByUserName(username);}

    public void saveUser(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRolename("USER")));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void saveAdmin(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRolename("ADMIN")));
        user.setEnabled(true);
        userRepository.save(user);
    }

//    public User contributor(PledgeItem item){
//        User cont= pledgeRepository.findByPledgingusers(item);
//
//    }

}
