package me.rayll.mercadolivreorangetalent.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public boolean existsByLogin(String login);

	public Optional<Usuario> findByLogin(String login);
}
