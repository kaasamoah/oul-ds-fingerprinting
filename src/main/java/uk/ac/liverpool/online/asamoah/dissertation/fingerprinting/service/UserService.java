/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.User;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.repository.UserRepository;

/**
 *
 * @author Kofi A. Asamoah <kofi.asamoah@online.liverpool.ac.uk>
 */
@Service("userService")
public class UserService {
    @Autowired
    private UserRepository userRepo;
    
    public User saveUser(User user){
        return userRepo.save(user);
    }
    
    public User getByUsername(String username){
        return userRepo.findByUsername(username);
    }
}
