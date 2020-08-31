package com.example.WatchNext.services;

import com.example.WatchNext.model.Role;
import com.example.WatchNext.model.Users;
import com.example.WatchNext.repositories.RoleRepository;
import com.example.WatchNext.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private @Autowired
    UsersRepository usersRepository;

    private @Autowired
    RoleRepository roleRepository;


    @Override
    public void saveDefaultUser() {
        long count=usersRepository.count();
        if (count==0)
        {
            Role role = roleRepository.findByName("Admin");
            System.out.println(role.toString());
            Users user = new Users();
            user.setName("Admin principal");
            user.setEmail("admin@admin.ro");
            user.setPassword_hash("1234");

            user.setRole(role);

            System.out.println(user);
            usersRepository.save(user);

        }
    }
}
