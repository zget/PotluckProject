package com.example.demo.Security;


import com.example.demo.Model.PledgeItem;
import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repositories.PledgeRepository;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PledgeRepository pledgeRepository;




    @Override
    public void run(String... strings) throws Exception{
        System.out.println("Loading data....");

        roleRepository.save(new Role("USER")) ;
        roleRepository.save(new Role("ADMIN")) ;



        User user1= new User("d@d.com", "password", "gech",
                "dere",true, "admin");
        Role myRole= roleRepository.findByRolename("ADMIN");
        user1.addRole(myRole);
        userRepository.save(user1);

        User user2= new User("d@d.com", "password", "RODAS",
                "ASFAW",true, "user");
        myRole= roleRepository.findByRolename("USER");
        user1.addRole(myRole);
        userRepository.save(user2);

        PledgeItem myitem=new PledgeItem("pizza", 6,"food");
        pledgeRepository.save(myitem);
        user1.addItem(myitem);
        userRepository.save(user1);






    }
}
