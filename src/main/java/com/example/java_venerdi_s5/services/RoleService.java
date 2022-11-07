package com.example.java_venerdi_s5.services;

import com.example.java_venerdi_s5.entities.Role;
import com.example.java_venerdi_s5.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository rr;

    public void addRole( Role r) {
        rr.save(r);
    }

    public Optional<Role> getById( int id) {
        return rr.findById(id);
    }

    public Iterable<Role> getAllRoles() {
        return rr.findAll();
    }
}
