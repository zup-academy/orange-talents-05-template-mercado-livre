package me.rayll.mercadolivreorangetalent.encriptador;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encriptador {

	public static String encode(String pass) {
		
		PasswordEncoder passEncoder = new BCryptPasswordEncoder();		
		String hashPass = passEncoder.encode(pass);		
		return hashPass;
	}
	
	public static boolean decode(String pass, String hash) {
		PasswordEncoder passEncoder = new BCryptPasswordEncoder();
		return passEncoder.matches(pass, pass);
	}
	
}
