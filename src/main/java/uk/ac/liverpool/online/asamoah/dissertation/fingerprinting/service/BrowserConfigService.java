/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.BrowserConfig;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.repository.BrowserConfigRepository;

/**
 *
 * @author Kofi A. Asamoah <kofi.asamoah@online.liverpool.ac.uk>
 */
@Service("browserConfigService")
public class BrowserConfigService {
    @Autowired
    private BrowserConfigRepository browserRepo;
    
    public BrowserConfig saveBrowser(BrowserConfig browser){
        return browserRepo.save(browser);
    }
    
    public BrowserConfig getByNameAndHostDevice(String name, int deviceId){
        return browserRepo.findByNameAndHostDeviceId(name, deviceId);
    }
    
    public BrowserConfig getByDetails(String name, String codeName, String platform, int deviceId){
        return browserRepo.findByNameCodeNamePlatformAndHostDeviceId(name, codeName, platform, deviceId);
    }
}
