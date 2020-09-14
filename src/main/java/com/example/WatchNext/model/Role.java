package com.example.WatchNext.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "is_admin")
    private Boolean isAdmin = false;

    public Role(String name, Boolean isAdmin) {
        this.name = name;
        this.isAdmin = isAdmin;
    }
}
