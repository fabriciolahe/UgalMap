package eu.crystalsystem.ugalmap.utils;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import eu.crystalsystem.ugalmap.models.User;
import eu.crystalsystem.ugalmap.repositories.UserRepository;

public class UserUtil {
	
	@Autowired
	private UserRepository userRepository;
	
	private Logger logger = Logger.getLogger(UserUtil.class.getName());
	
	
	public User getLoginResponse(User user) {
		User u = new User();
		try {
			u = userRepository.findUserByUserEmailAndUserPasswd(user.getUserEmail(), user.getUserPasswd());
			
		}catch(Exception e) {
			logger.severe("Exception getting info from user login " + e);
			e.printStackTrace();
			return new User();
		}
		if(u != null) {
			return u;
		}else {
			return new User();
		}
	}

}
