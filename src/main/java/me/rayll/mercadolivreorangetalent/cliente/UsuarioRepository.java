package me.rayll.mercadolivreorangetalent.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public boolean existsByLogin(String login);
}
