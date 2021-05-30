package me.rayll.mercadolivreorangetalent.seguranca.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;
import me.rayll.mercadolivreorangetalent.usuario.UsuarioDTO;

public class JWTUso {
	
	
	private String chaveAssinatura = "orangetalentturma5";
	
	private String chaveExpiracao = "60";
	
	private Claims obterClaims(String token) {
		
		return Jwts.parser()
					.setSigningKey(chaveAssinatura)
					.parseClaimsJws(token)
					.getBody();
	}
	
	public Boolean tokenValido(String token) {
		
		try {
			Claims claims = obterClaims(token);
			Date dataExpiracao = claims.getExpiration();
			LocalDateTime dateTime = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			
			return !LocalDateTime.now().isAfter(dateTime);
		}catch (Exception e) {
			return false;
		}
	}
	
	public String obterLoginDoUsuario(String token) {
		return obterClaims(token).getSubject();
	}
	
	public String gerarToken(UsuarioDTO usuario) {
		
		Long expiracaoToken = Long.valueOf(chaveExpiracao);
		LocalDateTime dataDaExpiracao = LocalDateTime.now().plusMinutes(expiracaoToken);
		
		Date date = Date.from(dataDaExpiracao.atZone(ZoneId.systemDefault()).toInstant());
		
		return Jwts.builder()
					.setSubject(usuario.getLogin())
					.setExpiration(date)
					.signWith(SignatureAlgorithm.HS512, chaveAssinatura)
					.compact();
		
	}
}
