/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.DeviceConfig;

/**
 *
 * @author Kofi A. Asamoah <kofi.asamoah@online.liverpool.ac.uk>
 */
@Repository("deviceConfigRepository")
public interface DeviceConfigRepository extends JpaRepository<DeviceConfig, Integer>{
    
    Set<DeviceConfig> findByDeviceId(@Param("devId") Integer deviceId);
    
    int countByPlatformTimezoneAndDeviceId(@Param("platform") String platform, @Param("tz") String timezone, @Param("devId") Integer userId);
}
