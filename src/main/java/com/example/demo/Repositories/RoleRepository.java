package com.example.demo.Repositories;

import com.example.demo.Security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByRolename(String rolename);

}
