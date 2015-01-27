/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.Session;

/**
 *
 * @author Kofi A. Asamoah <kofi.asamoah@online.liverpool.ac.uk>
 */
@Repository("sessionRepository")
public interface SessionRepository extends JpaRepository<Session, Integer>{
    Page<Session> findByUserId(@Param("userId") Integer userId, Pageable request);
}
