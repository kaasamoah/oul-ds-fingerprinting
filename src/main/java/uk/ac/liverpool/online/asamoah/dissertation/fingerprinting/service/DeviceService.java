/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.service;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.Device;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.repository.DeviceRepository;

/**
 *
 * @author Kofi A. Asamoah <kofi.asamoah@online.liverpool.ac.uk>
 */
@Service("deviceService")
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepo;
    
    public Device getById(int deviceId){
        return deviceRepo.findOne(deviceId);
    }
    
    public Device saveDevice(Device device){
        return deviceRepo.save(device);
    }
    
    public Set<Device> getUserDevices(int userId){
        return deviceRepo.findByOwnerId(userId);
    }
}
