/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.Browser;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.repository.BrowserRepository;

/**
 *
 * @author Kofi A. Asamoah <kofi.asamoah@online.liverpool.ac.uk>
 */
@Service("browserService")
public class BrowserService {
    @Autowired
    private BrowserRepository browserRepo;
    
    public Browser saveBrowser(Browser browser){
        return browserRepo.save(browser);
    }
}
