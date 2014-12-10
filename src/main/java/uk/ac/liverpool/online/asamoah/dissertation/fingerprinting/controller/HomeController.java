package uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.model.domain.User;
import uk.ac.liverpool.online.asamoah.dissertation.fingerprinting.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
        UserService userSvc;
        
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
        
        /**
         * Generates a visitor's device fingerprint and saves it to the database
         * @param model the active MVC model
         * @return 
         */
        @RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(Model model) {
		
		
		return "visitor";
	}
        
        /**
         * Verifies and logs in a user
         * @param username
         * @param model the active MVC model
         * @return 
         */
        @RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username, Model model) {
		User user = userSvc.getByUsername(username);
                if(user == null){
                    //something
                }
		
		return "visitor";
	}	
}
