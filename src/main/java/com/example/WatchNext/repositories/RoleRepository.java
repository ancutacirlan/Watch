package com.example.WatchNext.repositories;

import com.example.WatchNext.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {

    Role findByName(String name);

}
