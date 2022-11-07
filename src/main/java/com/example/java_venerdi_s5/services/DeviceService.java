package com.example.java_venerdi_s5.services;

import com.example.java_venerdi_s5.entities.Device;
import com.example.java_venerdi_s5.entities.User;
import com.example.java_venerdi_s5.repositories.DeviceRepository;
import com.example.java_venerdi_s5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    DeviceRepository dr;

    //SALVATAGGIO
    public void addDevice( Device u) {
        dr.save(u);
    }

    //ELIMINAZIONE
    public void deleteDeviceById(int id) {
        dr.deleteById(id);
    }

    //ESTRAZIONE SINGOLO DEVICE PER ID
    public Optional<Device> getDeviceById( int id) {
        return dr.findById(id);
    }

    //TUTTI I DEVICES
    public Iterable<Device> getAllDevices(){
        return dr.findAll();
    }

    //PAGINAZIONE DI TUTTI I DEVICES
    public Iterable<Device> getAllAndPaginate( Pageable p){
        return dr.findAll(p);
    }

    // CERCA DEVICE PER NOME
    public Optional<Device> getDeviceByName( String name) {
        return dr.findByName( name );
    }

    public Iterable<Device> getDevicesByUserId(int userId) {
        return dr.getDevicesByUserId(userId);
    }
}
