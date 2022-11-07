package com.example.java_venerdi_s5.repositories;

import com.example.java_venerdi_s5.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    @Query(
            "SELECT u FROM User u WHERE u.username LIKE concat('%', :u, '%' )"
    )
    Optional<User> findByUsername( @Param("u") String u);
}
