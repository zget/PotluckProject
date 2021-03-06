package com.example.demo.Repositories;

import com.example.demo.Model.PledgeItem;
import com.example.demo.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String username);
    Collection<User> findByPledgeitemsIn(Iterable<PledgeItem> item);

}
