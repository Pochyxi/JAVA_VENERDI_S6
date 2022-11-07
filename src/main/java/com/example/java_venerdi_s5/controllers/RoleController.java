package com.example.java_venerdi_s5.controllers;

import com.example.java_venerdi_s5.entities.Role;
import com.example.java_venerdi_s5.entities.RoleType;
import com.example.java_venerdi_s5.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService rs;

    @GetMapping("/add-roles")
    @PreAuthorize( "hasRole('ADMIN')" )
    public String addRoles() {
        Role roleAdmin = new Role();
        roleAdmin.setRoleType( RoleType.ROLE_ADMIN );
        rs.addRole( roleAdmin );

        Role roleUser = new Role();
        roleUser.setRoleType( RoleType.ROLE_USER );
        rs.addRole( roleUser );

        return "Roles added";
    }

    @GetMapping("/get-roles")
    @PreAuthorize( "hasRole('ADMIN')" )
    public Iterable<Role> getRoles() {
        return rs.getAllRoles();
    }
}
