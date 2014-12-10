/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.Session;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.repository.SessionRepository;

/**
 *
 * @author Kofi A. Asamoah <kofi.asamoah@online.liverpool.ac.uk>
 */
@Service("sessionService")
public class SessionService {
    @Autowired
    private SessionRepository sessionRepo;
    
    public Session saveSession(Session session){
        return sessionRepo.save(session);
    }
}
