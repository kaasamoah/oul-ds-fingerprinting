/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.service;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.DeviceConfig;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.repository.DeviceConfigRepository;

/**
 *
 * @author Kofi A. Asamoah <kofi.asamoah@online.liverpool.ac.uk>
 */
@Service("deviceConfigService")
public class DeviceConfigService {
    @Autowired
    private DeviceConfigRepository deviceCfgRepo;
    
    public DeviceConfig getById(int configId){
        return deviceCfgRepo.findOne(configId);
    }
    
    public DeviceConfig saveConfig(DeviceConfig config){
        return deviceCfgRepo.save(config);
    }
    
    public Set<DeviceConfig> getForDevice(int configId){
        return deviceCfgRepo.findByDeviceId(configId);
    }
    
    public int countWithDetails(String platform, String timezone, int deviceId){
        return deviceCfgRepo.countByPlatformTimezoneAndDeviceId(platform, timezone, deviceId);
    }
}
