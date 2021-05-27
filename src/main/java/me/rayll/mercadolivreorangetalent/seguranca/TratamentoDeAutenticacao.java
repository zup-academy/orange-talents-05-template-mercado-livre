package me.rayll.mercadolivreorangetalent.seguranca;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import me.rayll.mercadolivreorangetalent.encriptador.Encriptador;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;
import me.rayll.mercadolivreorangetalent.usuario.UsuarioDTO;
import me.rayll.mercadolivreorangetalent.usuario.UsuarioRepository;

@Component
public class TratamentoDeAutenticacao implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UserDetails autenticar(UsuarioDTO usuario) {
		
		UserDetails userDetails = loadUserByUsername(usuario.getLogin());
		Boolean senhaValida = Encriptador.decode(usuario.getPass(), userDetails.getPassword());
		
		if(senhaValida)
			return userDetails;
		  
		throw new RuntimeException("Senha diferentes!");
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> optional = usuarioRepository.findByLogin(username);
		
		if(optional.isEmpty())
			throw new UsernameNotFoundException("Usuario não encontrado!");
		
		UsuarioDTO usuario = optional.get().toDTO();
		
		return User.builder()
					.username(usuario.getLogin())
					.password(usuario.getPass())
					.authorities("USUARIO")
					.build();
	}

}
