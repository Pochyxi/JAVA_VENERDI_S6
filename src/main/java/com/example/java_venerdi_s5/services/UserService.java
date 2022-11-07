package com.example.java_venerdi_s5.services;

import com.example.java_venerdi_s5.entities.User;
import com.example.java_venerdi_s5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository ur;

    //SALVATAGGIO
    public void addUser(User u) {
        ur.save(u);
    }

    //ELIMINAZIONE
    public void deleteUserById(int id) {
        ur.deleteById(id);
    }

    //ESTRAZIONE SINGOLO USER PER ID
    public Optional<User> getUserById( int id) {
        return ur.findById(id);
    }

    //TUTTI GLI USERS
    public Iterable<User> getAllUsers(){
        return ur.findAll();
    }

    //PAGINAZIONE DI TUTTI GLI USERS
    public Iterable<User> getAllAndPaginate( Pageable p){
        return ur.findAll(p);
    }

    // CERCA USER PER USERNAME
    public Optional<User> getUserByUsername( String username) {
        return ur.findByUsername( username );
    }
}
