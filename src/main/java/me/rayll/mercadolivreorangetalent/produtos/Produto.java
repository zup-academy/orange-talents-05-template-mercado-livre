package me.rayll.mercadolivreorangetalent.produtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import me.rayll.mercadolivreorangetalent.categoria.Categoria;
import me.rayll.mercadolivreorangetalent.opiniaoproduto.OpiniaoProduto;
import me.rayll.mercadolivreorangetalent.perguntaproduto.PerguntaProduto;
import me.rayll.mercadolivreorangetalent.perguntaproduto.PerguntaProdutoDTO;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;
import net.bytebuddy.build.HashCodeAndEqualsPlugin.Sorted;

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
	@OneToMany(mappedBy = "produto") @OrderBy()
	private SortedSet<PerguntaProduto> perguntas = new TreeSet<PerguntaProduto>();
	@OneToMany(mappedBy = "produto") @OrderBy("titulo asc")
	private SortedSet<OpiniaoProduto> opinioes = new TreeSet<OpiniaoProduto>();
	
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
		List<CaracteristicaDTO> listaCaracteristicas = new ArrayList<>();
		caracteristicas.forEach(c -> listaCaracteristicas.add(c.toDTO()));

		Set<PerguntaProdutoDTO> listaPerguntas = perguntas.stream().map(pergunta -> pergunta.toDTO()).collect(Collectors.toSet());
		
		 ProdutoDTO produtoDTO = new ProdutoDTO(nome, quantidade, descricao, valor, categoria.getId(), listaCaracteristicas );
		 
		 produtoDTO.setPerguntas(listaPerguntas);
		 produtoDTO.setImagens(imagens);
		 return produtoDTO;
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

	public @NotNull Long getId() {
		return this.id;
	}

	public Usuario getDono() {
		return this.dono;
	}

	public SortedSet<PerguntaProduto> getPerguntas() {
		return perguntas;
	}
	
	public SortedSet<OpiniaoProduto> getOpinioes() {
		return opinioes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((dono == null) ? 0 : dono.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (dono == null) {
			if (other.dono != null)
				return false;
		} else if (!dono.equals(other.dono))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
	
	
	
}
