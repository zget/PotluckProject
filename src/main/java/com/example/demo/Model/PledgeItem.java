package com.example.demo.Model;


import com.example.demo.Security.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class PledgeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String itemname;

    private long servesize;

    private String itemtype;


    @ManyToMany(mappedBy = "pledgeitems")
    private Collection<User> pledgingusers;


}
