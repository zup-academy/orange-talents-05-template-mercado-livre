package me.rayll.mercadolivreorangetalent.produtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import me.rayll.mercadolivreorangetalent.categoria.Categoria;
import me.rayll.mercadolivreorangetalent.opiniaoproduto.OpiniaoProdutoDTO;
import me.rayll.mercadolivreorangetalent.perguntaproduto.PerguntaProduto;
import me.rayll.mercadolivreorangetalent.perguntaproduto.PerguntaProdutoDTO;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;

public class ProdutoDTO {
	
	@NotEmpty
	private String nome;
	@Positive
	private int quantidade;
	@NotEmpty @Size(max = 1000)
	private String descricao;
	@NotNull @Positive
	private BigDecimal valor;
	@NotNull
	private Long idCategoria;
	@Size(min = 3) @Valid
	private List<CaracteristicaDTO> caracteristicas = new ArrayList<>();
	private Set<ImagemProduto> imagens = new HashSet<>();
	private Set<PerguntaProdutoDTO> perguntas = new HashSet<>();
	private Set<OpiniaoProdutoDTO> opinioes = new HashSet<>();

	@Deprecated
	private ProdutoDTO() {}
	
	public ProdutoDTO(@NotEmpty String nome, @Positive int quantidade, @NotEmpty @Size(max = 1000) String descricao,
			@Positive BigDecimal valor, Long idCategoria, List<CaracteristicaDTO> caracteristicas) {
		
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}

	public String getNome() {
		return nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}
	
	public List<CaracteristicaDTO> getCaracteristicas(){
		return this.caracteristicas;
	}
	
	public Set<ImagemProduto> getImagens() {
		return imagens;
	}
	
	public void setImagens(Set<ImagemProduto> imagens) {
		this.imagens  = imagens;	
	}
	
	public Produto toModel(Categoria categoria, Usuario dono) {
		return new Produto(nome, quantidade, descricao, valor, categoria, dono, caracteristicas);
	}
	
	@Override
	public String toString() {
		return "ProdutoDTO [nome=" + nome + ", quantidade=" + quantidade + ", descricao=" + descricao + ", valor="
				+ valor + ", idCategoria=" + idCategoria + ", caracteristicas=" + caracteristicas + "]";
	}

	public Set<String> temCaracteristicasIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		HashSet<String> resultado = new HashSet<>();
		for (CaracteristicaDTO caracteristica : caracteristicas) {
			String nome = caracteristica.getNome();
			if(!nomesIguais.add(nome)) {
				resultado.add(nome);
			}
		}
		return resultado;
	}
	
	public Set<OpiniaoProdutoDTO> getOpinioes() {
		return opinioes;
	}

	public void setOpinioes(Set<OpiniaoProdutoDTO> opinioes) {
		this.opinioes = opinioes;
	}

	public void setPerguntas(Set<PerguntaProdutoDTO> listaPerguntas) {
		perguntas = listaPerguntas;
	}

	public Set<PerguntaProdutoDTO> getPerguntas() {
		return perguntas;
	}

}
