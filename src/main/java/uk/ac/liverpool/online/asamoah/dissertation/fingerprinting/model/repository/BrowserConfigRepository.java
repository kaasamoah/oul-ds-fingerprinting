/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.BrowserConfig;

/**
 *
 * @author Kofi A. Asamoah <kofi.asamoah@online.liverpool.ac.uk>
 */
@Repository("browserConfigRepository")
public interface BrowserConfigRepository extends JpaRepository<BrowserConfig, Integer>{
    
    public BrowserConfig findByNameAndHostDeviceId(@Param("name") String name, @Param("deviceId") int deviceId);
    
    public BrowserConfig findByNameCodeNamePlatformAndHostDeviceId(@Param("name") String name, @Param("codeName") String codeName, @Param("platform") String platform, @Param("deviceId") int deviceId);
}
