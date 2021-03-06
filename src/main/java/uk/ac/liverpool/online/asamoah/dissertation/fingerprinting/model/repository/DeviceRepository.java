/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.repository;

import java.io.Serializable;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.Device;

/**
 *
 * @author Kofi A. Asamoah <kofi.asamoah@online.liverpool.ac.uk>
 */
@Repository("deviceRepository")
public interface DeviceRepository extends JpaRepository<Device, Serializable>{
    Set<Device> findByOwnerId(@Param("ownerId") Integer userId);
}
