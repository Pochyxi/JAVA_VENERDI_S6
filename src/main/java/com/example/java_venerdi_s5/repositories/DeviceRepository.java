package com.example.java_venerdi_s5.repositories;

import com.example.java_venerdi_s5.entities.Device;
import com.example.java_venerdi_s5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

    @Query(
            "SELECT d FROM Device d WHERE d.name LIKE concat('%', :u, '%' )"
    )
    Optional<Device> findByName( @Param("u") String u);

    @Query(
            value = "select d from Device d where d.user.id = :userId"
    )
    Iterable<Device> getDevicesByUserId(@Param( "userId" ) int userId);
}
