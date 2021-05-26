package me.rayll.mercadolivreorangetalent.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	public boolean existsByNome(String nome);
}
