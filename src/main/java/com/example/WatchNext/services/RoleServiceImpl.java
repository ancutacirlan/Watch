package com.example.WatchNext.services;

import com.example.WatchNext.model.Role;
import com.example.WatchNext.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void saveDefaultRoles() {
        long count=roleRepository.count();
        System.out.println(count);
        if (count==0)
        {
            roleRepository.save(new Role("Admin",true));
            roleRepository.save(new Role("User",false));
        }
    }


}
