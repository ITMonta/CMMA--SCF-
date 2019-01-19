package TONICKXS.SCFT.utilities;

import javax.annotation.PostConstruct;


import TONICKXS.SCFT.entities.User;
import TONICKXS.SCFT.services.UserService;



public class DBPopulator {
	
	private UserService userServiceLocal;

	public DBPopulator() {
	}

	@PostConstruct
	public void init() {
		User user = new User("user", "u", "u", "user@bitbox.tn");

		userServiceLocal.update(user);
	}
}
