package com.example.demo.Model;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

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


    public PledgeItem() {

        this.pledgingusers= new HashSet<User>();
    }

    public PledgeItem( String itemname, long servesize, String itemtype) {
        this.itemname = itemname;
        this.servesize = servesize;
        this.itemtype = itemtype;
        this.pledgingusers= new HashSet<User>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public long getServesize() {
        return servesize;
    }

    public void setServesize(long servesize) {
        this.servesize = servesize;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public Collection<User> getPledgingusers() {
        return pledgingusers;
    }

    public void setPledgingusers(Collection<User> pledgingusers) {
        this.pledgingusers = pledgingusers;
    }
}
