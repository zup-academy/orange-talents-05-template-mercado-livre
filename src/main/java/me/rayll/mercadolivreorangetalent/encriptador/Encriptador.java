package me.rayll.mercadolivreorangetalent.encriptador;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

public class Encriptador {

	public static String encode(String pass) {
		
		Assert.isTrue(pass.length() >= 6, "A senha precisa ter maior que 6 caracteres.");
		
		PasswordEncoder passEncoder = new BCryptPasswordEncoder();		
		String hashPass = passEncoder.encode(pass);		
		return hashPass;
	}
	
	public static boolean decode(String pass, String hash) {
		PasswordEncoder passEncoder = new BCryptPasswordEncoder();
		return passEncoder.matches(pass, hash);
	}
	
}
