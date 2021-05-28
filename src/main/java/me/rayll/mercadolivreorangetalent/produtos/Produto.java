package me.rayll.mercadolivreorangetalent.produtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import me.rayll.mercadolivreorangetalent.categoria.Categoria;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;

@Entity
public class Produto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @JsonIgnore
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private int quantidade;
	@Column(nullable = false)
	private String descricao;
	@Column(nullable = false)
	private BigDecimal valor;
	@NotNull @Valid @ManyToOne
	private Categoria categoria;
	@NotNull @Valid @ManyToOne
	private Usuario dono;
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<Caracteristica> caracteristicas = new HashSet<>();
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	
	@Deprecated
	private Produto() {}

	public Produto(String nome, int quantidade, String descricao, BigDecimal valor, Categoria categoria, Usuario dono, Collection<CaracteristicaDTO> caracteristicas) {
		
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.categoria = categoria;
		this.dono = dono;
		Set<Caracteristica> novasCaracteristicas = caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this)).collect(Collectors.toSet());
		this.caracteristicas.addAll(novasCaracteristicas);
	}
	
	public ProdutoDTO toDTO() {
		
		List<CaracteristicaDTO> list = new ArrayList<>();
		
		caracteristicas.forEach(c -> list.add(c.toDTO()));
		
		return new ProdutoDTO(nome, quantidade, descricao, valor, categoria.getId(), list );
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens =  links.stream().map(link -> new ImagemProduto(this, link)).collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", quantidade=" + quantidade + ", descricao=" + descricao + ", valor=" + valor
				+ ", categoria=" + categoria + ", dono=" + dono + ", caracteristicas=" + caracteristicas + ", imagens="
				+ imagens + "]";
	}

	public boolean pertenceAoUsuario(Usuario possivelDono) {
		 return this.dono.equals(possivelDono);
	}
	
	
}
