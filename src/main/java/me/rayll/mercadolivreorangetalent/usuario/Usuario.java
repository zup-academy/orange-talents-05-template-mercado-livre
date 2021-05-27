package me.rayll.mercadolivreorangetalent.usuario;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String login;
	@Column(nullable = false)
	private String pass;
	
	private Instant instante;
	
	@Deprecated
	private Usuario() {}

	public Usuario(String login, String pass) {
		this.login = login;
		this.pass = pass;
		this.instante = Instant.now();
	}
	
	public UsuarioDTO toDTO() {
		return new UsuarioDTO(login, pass);
	}
	
}
