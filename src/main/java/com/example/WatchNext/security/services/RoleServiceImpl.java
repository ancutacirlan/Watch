package com.example.WatchNext.security.services;


import com.example.WatchNext.model.Role;
import com.example.WatchNext.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
