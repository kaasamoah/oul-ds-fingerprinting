/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.Device;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.DeviceConfig;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.User;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.service.DeviceConfigService;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.service.DeviceService;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.service.SessionService;

/**
 *
 * @author Kofi A. Asamoah <kofi.asamoah@online.liverpool.ac.uk>
 */
@Controller
public class VisitorController {
    
    @Autowired
    DeviceService deviceSvc;
    @Autowired
    SessionService sessionSvc;
    @Autowired
    DeviceConfigService configSvc;
    
    @RequestMapping(value="/visitor/devices", method = RequestMethod.POST)
    public @ResponseBody Object getUserDevices(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null)
            return null;
        
        return deviceSvc.getUserDevices(user.getId());
    }
    
    @RequestMapping(value="/visitor/sessions", method = RequestMethod.POST)
    public @ResponseBody Object getUserSessions(@RequestParam("page") int page, @RequestParam("size") int size, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null)
            return null;
        
        return sessionSvc.getUserSessions(user.getId(), page, size);
    }
    
    @RequestMapping(value="/visitor/device/register", method = RequestMethod.POST)
    public @ResponseBody Object saveDevice(@RequestParam(value="deviceId", required=false) Integer deviceId, @RequestParam(value="deviceName", required=false) String deviceName, @RequestParam(value="configId") Integer configId, HttpServletRequest request){       
        User user = (User)request.getSession().getAttribute("user");
        DeviceConfig config = configSvc.getById(configId);
        if(config == null)
            return "Device configuration unavailable";
            
        Device device;
        if(deviceId != null){
            device = deviceSvc.getById(deviceId);
            if(device == null)
                return "Device not found";
        }else if(deviceName != null && !deviceName.isEmpty()){
            device = new Device();
            device.setName(deviceName);
            device.setDateAdded(new Date());
            device.setOwner(user);
            
            device = deviceSvc.saveDevice(device);
        }else{
            return "Device details unavailable";
        }
        config.setDevice(device);
        configSvc.saveConfig(config);
        
        request.getSession().removeAttribute("device");
        
        return "SUCCESS";
    }
}
