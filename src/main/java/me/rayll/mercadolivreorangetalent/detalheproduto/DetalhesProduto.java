package me.rayll.mercadolivreorangetalent.detalheproduto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import me.rayll.mercadolivreorangetalent.opiniaoproduto.OpiniaoProdutoDTO;
import me.rayll.mercadolivreorangetalent.perguntaproduto.PerguntaProdutoDTO;
import me.rayll.mercadolivreorangetalent.produtos.ImagemProduto;
import me.rayll.mercadolivreorangetalent.produtos.ProdutoDTO;

public class DetalhesProduto {

	private String nome;
	private String descricao;
	private BigDecimal valor;
	private Set<DetalheProdutoCaracteristica> caracteristicas = new HashSet<>();
	private Set<String> imagens = new HashSet<>();
	private Set<PerguntaProdutoDTO> perguntas = new HashSet<>();
	private Set<Map<String, String>> opinioes = new HashSet<>();
	private double mediaNotas;

	@Deprecated
	private DetalhesProduto() {}
	
	public DetalhesProduto(ProdutoDTO dto) {
		this.nome = dto.getNome();
		this.descricao = dto.getDescricao();
		this.valor = dto.getValor();
		this.caracteristicas = dto.getCaracteristicas().stream().map(DetalheProdutoCaracteristica::new).collect(Collectors.toSet());
		this.imagens = dto.getImagens().stream().map(l -> l.getLink()).collect(Collectors.toSet());
		this.perguntas = dto.getPerguntas();
		this.opinioes = dto.getOpinioes().stream().map(opiniao ->{
			return Map.of("titutlo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
		}).collect(Collectors.toSet());
		
		List<Integer> notas = dto.getOpinioes().stream().map(opiniao -> opiniao.getNota()).collect(Collectors.toList());
		IntStream mapNotas = notas.stream().mapToInt(nota -> nota);
		OptionalDouble average = mapNotas.average();
		
		if(average.isPresent()) {
			this.mediaNotas = average.getAsDouble();
		}
		
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<String> getImagens() {
		return imagens;
	}

	public Set<PerguntaProdutoDTO> getPerguntas() {
		return perguntas;
	}

	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}

	public double getMediaNotas() {
		return mediaNotas;
	}
	
	
	
}
