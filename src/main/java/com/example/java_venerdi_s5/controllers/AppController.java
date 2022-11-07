package com.example.java_venerdi_s5.controllers;

import com.example.java_venerdi_s5.entities.Device;
import com.example.java_venerdi_s5.entities.DeviceStatus;
import com.example.java_venerdi_s5.entities.User;
import com.example.java_venerdi_s5.services.DeviceService;
import com.example.java_venerdi_s5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    DeviceService ds;

    @Autowired
    UserService us;

    @PutMapping("/give-device/{device_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Device giveDevice(
            @PathVariable("device_id") int device_id,
            @RequestParam("user_id") int user_id
    ) {
        Optional<Device> d = ds.getDeviceById( device_id );
        Optional<User> u = us.getUserById( user_id );

        if( d.isPresent() && u.isPresent() ) {

            User user = u.get();
            Device device = d.get();
            //Se ha qualsiasi stato diverso da disponibile manda l'errore ed esce dalla funzione
            if( !Objects.equals( device.getDeviceStatus().toString(), "AVAILABLE" ) ) {

                System.out.println( device.getDeviceStatus() );
                System.out.println( "----------------------------" );
                Device dError = new Device();
                dError.setId( 404 );
                dError.setName( "Device già assegnato!" );
                return dError;

            } else {

                device.setUser( user );
                device.setDeviceStatus( DeviceStatus.ASSIGNED );
                ds.addDevice( device );
                return device;

            }
        }
        Device dError = new Device();
        dError.setId( 404 );
        dError.setName( "Errore durante l'assegnamento" );
        return dError;

    }
}
