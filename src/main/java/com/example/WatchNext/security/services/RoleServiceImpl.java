package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Role;
import com.example.WatchNext.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void saveDefaultRoles() {
        long count=roleRepository.count();
        if (count==0)
        {
            roleRepository.save(new Role("ROLE_ADMIN",true));
            roleRepository.save(new Role("ROLE_USER",false));
        }
    }


}
