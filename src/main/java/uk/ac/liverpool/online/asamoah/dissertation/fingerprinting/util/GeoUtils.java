/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.util;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Kofi A. Asamoah <kofi.asamoah@online.liverpool.ac.uk>
 */
public class GeoUtils {

    private static final Logger logger = LoggerFactory.getLogger(GeoUtils.class);
    
    public static String getLocation(String ipAddress) {
        String location = "";

        File file = new File(GeoUtils.class.getClassLoader().getResource("GeoLite2-City.mmdb").getFile());
        
        try {
            DatabaseReader reader = new DatabaseReader.Builder(file).build();
            InetAddress ip = InetAddress.getByName(ipAddress);
            
            CityResponse response = reader.city(ip);
            location += (response.getCity() == null) ? "" : response.getCity().getName() + ", ";
            location += (response.getCountry() == null) ? "" : response.getCountry().getName();
            location += (response.getContinent() == null) ? "" : "(" + response.getContinent().getName() + ")";
            //location = response.toJson();
        } catch (IOException e) {
            logger.error("Error reading Geo database file", e);
        } catch (GeoIp2Exception ex) {
            logger.error("Error reading client location", ex);
        }

        return location;

    }
}
