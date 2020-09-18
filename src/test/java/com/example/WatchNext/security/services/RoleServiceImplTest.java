package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Category;
import com.example.WatchNext.model.Role;
import com.example.WatchNext.repositories.RoleRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoleServiceImplTest {

    RoleServiceImpl roleService;

    @Mock
    RoleRepository roleRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        roleService = new RoleServiceImpl(roleRepository);
    }

    @Test
    void findRoleByName() throws Exception{
        Role role = mock(Role.class);
        when(roleRepository.findByName(role.getName())).thenReturn(role);
        assertEquals(role.getName(),roleService.findRoleByName(role.getName()).getName());
    }

}