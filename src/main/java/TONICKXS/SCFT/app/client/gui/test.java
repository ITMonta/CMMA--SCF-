package TONICKXS.SCFT.app.client.gui;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import TONICKXS.SCFT.entities.User;
import TONICKXS.SCFT.entities.Variante;
import TONICKXS.SCFT.services.UserService;

public class test {

	public static void main(String[] args) throws NamingException {
		// TODO Auto-generated method stub
		String l="u";
		User user = new User();
		UserService usService = new UserService();
		user.setEmail("abcd");
		List<User> users;
		users=usService.findAll();
		System.out.println(users.size());
		
		

	}

}
