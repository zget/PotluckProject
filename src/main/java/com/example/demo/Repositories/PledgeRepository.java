package com.example.demo.Repositories;

import com.example.demo.Model.PledgeItem;
import com.example.demo.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PledgeRepository extends CrudRepository<PledgeItem, Long>{

    PledgeItem findByItemname(String itemname);
    Collection<PledgeItem> findByPledgingusers(User user);

}
