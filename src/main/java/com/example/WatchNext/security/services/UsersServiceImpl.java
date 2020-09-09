package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Role;
import com.example.WatchNext.model.Users;
import com.example.WatchNext.repositories.RoleRepository;
import com.example.WatchNext.repositories.UserRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private JavaMailSender javaMailSender;

    public UsersServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

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
