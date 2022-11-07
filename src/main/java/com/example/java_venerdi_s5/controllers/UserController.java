package com.example.java_venerdi_s5.controllers;

import com.example.java_venerdi_s5.entities.Role;
import com.example.java_venerdi_s5.entities.User;
import com.example.java_venerdi_s5.services.RoleService;
import com.example.java_venerdi_s5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService us;

    @Autowired
    RoleService rs;

    @GetMapping("/all-users")
    @PreAuthorize( "hasRole('ADMIN')" )
    public Iterable<User> allUsers() {
        return us.getAllUsers();
    }

    @PostMapping("/add-user")
    @PreAuthorize( "hasRole('ADMIN')" )
    public User addUser(
            @RequestParam("username") String username,
            @RequestParam("full_name") String full_name,
            @RequestParam("password") String password,
			@RequestParam("email") String email,
            @RequestParam(value = "role", required = false) String role
    ) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setFullName(full_name);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setActive( true );

        Set<Role> roles = new HashSet<>();

        Role admin = rs.getById( 1 ).isPresent() ? rs.getById( 1 ).get() : null;
        Role user = rs.getById( 2 ).isPresent() ? rs.getById( 2 ).get() : null;

        if ( Objects.equals( role, "admin" ) ){
            roles.add( admin );
        }

        roles.add(user);
        newUser.setRoles( roles );
        us.addUser( newUser );
        return newUser;
    }

    @DeleteMapping("/del-user/{userId}")
    public User deleteUserById(@PathVariable("userId") int id) {
        User user = us.getUserById( id ).isPresent() ? us.getUserById( id ).get() : null;
        if ( user!= null ) {
            us.deleteUserById( user.getId() );
        }
        return user;
    }
}
