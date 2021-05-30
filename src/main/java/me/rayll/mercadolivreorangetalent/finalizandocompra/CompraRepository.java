package me.rayll.mercadolivreorangetalent.finalizandocompra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<NovaCompra, Long>{

}
