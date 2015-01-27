package uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.BrowserConfig;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.DeviceConfig;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.Session;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.User;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.service.BrowserConfigService;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.service.DeviceConfigService;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.service.SessionService;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.service.UserService;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.util.EncryptionUtils;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.util.GeoUtils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    @Autowired
    UserService userSvc;
    @Autowired
    DeviceConfigService deviceCfgSvc;
    @Autowired
    BrowserConfigService browserCfgSvc;
    @Autowired
    SessionService sessionSvc;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    /**
     * Simply selects the home view to render by returning its name.
     *
     * @param locale the visitor's locale
     * @param model the MVC model
     * @return
     */
    @RequestMapping(value = {"/", "/home", "/index"}, method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate);

        return "home";
    }

    /**
     * Simply selects the home view to render by returning its name.
     *
     * @param locale the visitor's locale
     * @param model the MVC model
     * @param request the servlet request
     * @param response the servlet response
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/visitor", method = RequestMethod.GET)
    public String visitor(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User)request.getSession().getAttribute("user");
        if(user == null)
            return "home";

        model.addAttribute("user", user);
        
        if(user.getFullname() != null)
            model.addAttribute("firstName", user.getFullname().split(" ")[0]);
        
        model.addAttribute("device", request.getSession().getAttribute("device"));
        
        return "visitor";
    }

    /**
     * Generates a visitor's device fingerprint and saves it to the database
     *
     * @param name the User's name
     * @param username the User's login identity
     * @param password the User's password
     * @param fingerprint the Visitor's client side fingerprint
     * @param model the MVC model
     * @param request the servlet request
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public @ResponseBody
    Object signup(@RequestParam("name") String name, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("fingerprint") String fingerprint, Model model, HttpServletRequest request) {
        User user = userSvc.getByUsername(username);

        if (user != null) {
            return "User already exists";
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> fp = mapper.readValue(fingerprint, new TypeReference<HashMap<String,String>>(){});
            
            user = new User();
            user.setFullname(name);
            user.setUsername(username);
            user.setPassword(EncryptionUtils.computeSHA256(password));
            user.setDateAdded(new Date());
            user = userSvc.saveUser(user);
            
            DeviceConfig deviceCfg = getDevice(fp, request, user);
            deviceCfg = deviceCfgSvc.saveConfig(deviceCfg);
   
            BrowserConfig browser = getBrowser(fp, request, deviceCfg);
            browser = browserCfgSvc.saveBrowser(browser);

            Session session = new Session();
            session.setKey(request.getSession().getId());
            session.setStartTime(new Date());
            session.setLocation(GeoUtils.getLocation(request.getRemoteAddr()));
            session.setBrowser(browser);
            session.setDeviceConfig(deviceCfg);
            session.setUser(user);
            
            sessionSvc.saveSession(session);
            
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("device", deviceCfg);
        } catch (Exception ex) {
            logger.error("Error creating user profile", ex);
            return "Sorry, an error occurred. Please try again.";
        }

        return "SUCCESS";
    }

    /**
     * Verifies and logs in a user
     *
     * @param username the User's login identity
     * @param password the User's password
     * @param fingerprint the Visitor's client side fingerprint
     * @param model the MVC model
     * @param request the servlet request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    Object login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("fingerprint") String fingerprint, Model model, HttpServletRequest request) {
                
        try{
            User user = userSvc.getByUsername(username);
            
            if (user == null || !user.getPassword().equals(EncryptionUtils.computeSHA256(password))) {
                return "Invalid user! Please check your details and try again.";
            }
        
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> fp = mapper.readValue(fingerprint, new TypeReference<HashMap<String,String>>(){});
            
            //Verify user details
            DeviceConfig deviceCfg = getDevice(fp, request, user);
            deviceCfg = deviceCfgSvc.saveConfig(deviceCfg);
            
            BrowserConfig browser = getBrowser(fp, request, deviceCfg);
            browser = browserCfgSvc.saveBrowser(browser);
            
            Session session = new Session();
            session.setKey(request.getSession().getId());
            session.setStartTime(new Date());
            session.setLocation(GeoUtils.getLocation(request.getRemoteAddr()));
            session.setBrowser(browser);
            session.setDeviceConfig(deviceCfg);
            session.setUser(user);
            
            sessionSvc.saveSession(session);
        
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("device", deviceCfg);
        }catch(Exception ex){
            logger.error("Error creating session", ex);
            return "Sorry, there was an error logging you in. Please try again.";
        }
        return "SUCCESS";
    }
    
    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        return "home";
    }

    protected BrowserConfig getBrowser(Map<String, String> fingerprint, HttpServletRequest request, DeviceConfig device) throws NoSuchAlgorithmException {
        
        BrowserConfig browser = new BrowserConfig();
        browser.setDateAdded(new Date());        
        browser.setHostDevice(device);
        browser.setCodeName(fingerprint.get("appCodeName"));
        browser.setCanvas(fingerprint.get("canvas"));
        browser.setCanvasDigest(DigestUtils.shaHex(fingerprint.get("canvas")));
        browser.setCookiesEnabled(Boolean.valueOf(fingerprint.get("cookies")));
        browser.setDnt(Boolean.valueOf(fingerprint.get("dnt")));
        
        if(fingerprint.get("heapSize") != null)
            browser.setJavascriptHeapSize(Integer.valueOf(fingerprint.get("heapSize")));
        
        browser.setMimeTypes(fingerprint.get("mimeTypes"));
        browser.setMimeTypesDigest(DigestUtils.shaHex(fingerprint.get("mimeTypes")));
        browser.setName(fingerprint.get("appName"));
        browser.setPlatform(fingerprint.get("os"));
        browser.setPlugins(fingerprint.get("browserPlugins"));
        browser.setPluginsDigest(DigestUtils.shaHex(fingerprint.get("browserPlugins")));
        browser.setProduct(fingerprint.get("browserProduct"));
        browser.setProductSub(fingerprint.get("browserProductSub"));
        browser.setUserAgent(fingerprint.get("userAgent"));
        browser.setVendor(fingerprint.get("browserVedor"));
        browser.setVersion(fingerprint.get("version"));

        return browser;
    }

    protected DeviceConfig getDevice(Map<String, String> fingerprint, HttpServletRequest request, User user) throws UnknownHostException {
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, request.getLocale());
        String timezone = dateFormat.getTimeZone().getDisplayName();
        
        DeviceConfig dev = new DeviceConfig();  
        dev.setDateAdded(new Date());
        dev.setDisplayHeight(Short.valueOf(fingerprint.get("windowHeight")));
        dev.setDisplayWidth(Short.valueOf(fingerprint.get("windowWidth")));
        dev.setColourDepth(Integer.valueOf(fingerprint.get("pixelDepth")));
        dev.setFontsDigest(DigestUtils.shaHex(fingerprint.get("fonts")));
        dev.setFonts(fingerprint.get("fonts"));
        dev.setIp(getClientIP(request));
        dev.setJava(Boolean.valueOf(fingerprint.get("java")));
        dev.setLanguage(request.getLocale().getLanguage());
        dev.setLocales(request.getLocale().getDisplayName());
        dev.setPlatform(fingerprint.get("os"));
        dev.setTimezone(timezone);
        
        return dev;
    }
    
    public String getClientIP(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_CLIENT_IP");
        }if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
