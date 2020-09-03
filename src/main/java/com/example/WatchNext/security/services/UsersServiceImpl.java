package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Role;
import com.example.WatchNext.model.Users;
import com.example.WatchNext.repositories.RoleRepository;
import com.example.WatchNext.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private @Autowired
    UserRepository userRepository;

    private @Autowired
    RoleRepository roleRepository;


    @Override
    public void saveDefaultUser() {
        long count=userRepository.count();
        if (count==0)
        {
            Role role = roleRepository.findByName("ROLE_ADMIN");
            System.out.println(role.toString());
            Users user = new Users();
            user.setUsername("Admin principal");
            user.setEmail("admin@admin.ro");
            user.setPassword("1234");
            user.setRole(role);
            System.out.println(user);
            userRepository.save(user);

        }
    }
}
