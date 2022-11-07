package com.example.java_venerdi_s5.controllers;

import com.example.java_venerdi_s5.entities.Device;
import com.example.java_venerdi_s5.entities.DeviceStatus;
import com.example.java_venerdi_s5.entities.User;
import com.example.java_venerdi_s5.services.DeviceService;
import com.example.java_venerdi_s5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    DeviceService ds;

    @Autowired
    UserService us;

    @PostMapping("/add-device")
    @PreAuthorize("hasRole('ADMIN')")
    public Device addDevice(
            @RequestParam("name") String name,
            @RequestParam("status") String status
    ) {
        Device newDevice = Device.builder()
                .name( name )
                .deviceStatus( DeviceStatus.valueOf( status ) )
                .build();

        ds.addDevice( newDevice );

        return newDevice;
    }

    @GetMapping("/all-devices")
    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<Device> getAllDevices() {
        return ds.getAllDevices();
    }

    @PutMapping("/modify/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Device putDevice(
            @PathVariable int id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "user_id", required = false) Integer user_id
    ) {
        Optional<Device> d = ds.getDeviceById( id );
        Optional<User> u = us.getUserById( user_id == null ? 0 : user_id );

        if( d.isPresent() ) {
            Device device = d.get();
            if( name != null ) device.setName( name );
            if( status != null ) device.setDeviceStatus( DeviceStatus.valueOf( status ) );
            device.setUser( u.orElse( null ) );
            ds.addDevice( device );
            return device;
        }

        return null;
    }

    @GetMapping ("/device-user/{user_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<Device> getDevicesByUserId(
            @PathVariable int user_id
    ) {
        return ds.getDevicesByUserId( user_id );
    }

}
