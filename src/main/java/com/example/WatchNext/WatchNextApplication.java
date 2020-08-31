package com.example.WatchNext;

import com.example.WatchNext.model.Role;
import com.example.WatchNext.model.Users;
import com.example.WatchNext.repositories.RoleRepository;
import com.example.WatchNext.services.RoleService;
import com.example.WatchNext.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatchNextApplication implements CommandLineRunner{

	private @Autowired
	RoleService roleService;
	private @Autowired
	UsersService usersService;


	public static void main(String[] args) {
		SpringApplication.run(WatchNextApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {
		roleService.saveDefaultRoles();
		usersService.saveDefaultUser();
	}
}