package me.rayll.mercadolivreorangetalent.seguranca;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import me.rayll.mercadolivreorangetalent.seguranca.jwt.JWTUso;

@Component	
public class AuthFilter extends OncePerRequestFilter{

	private JWTUso jwtUso = new JWTUso();
	
	@Autowired
	private TratamentoDeAutenticacao tsc;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorization = request.getHeader("Authorization");
		
		if(authorization != null && authorization.startsWith("Bearer")) {
			
			String token = authorization.split(" ")[1];
			Boolean tokenValido = jwtUso.tokenValido(token);
			
			if(tokenValido) {
				
				String loginUsuario = jwtUso.obterLoginDoUsuario(token);
				UserDetails userDetails = tsc.loadUserByUsername(loginUsuario);				
				UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(user);
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
