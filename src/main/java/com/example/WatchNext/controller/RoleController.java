package com.example.WatchNext.controller;

import com.example.WatchNext.model.Role;
import com.example.WatchNext.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;


}
