package me.rayll.mercadolivreorangetalent.finalizandocompra.retornocompra;

import me.rayll.mercadolivreorangetalent.finalizandocompra.NovaCompra;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;

@Entity
public class Transacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long idCompraDoGateway;
    @ManyToOne @NotNull
    private NovaCompra compra;
    @NotNull
    private StatusTransacao statusTransacao;
    @CreationTimestamp
    private Instant instante;

    public Transacao(Long idCompraDoGateway, NovaCompra novaCompra, StatusTransacao statusTransacao) {
        this.idCompraDoGateway = idCompraDoGateway;
        this.compra = novaCompra;
        this.statusTransacao = statusTransacao;
    }

    @Deprecated
    private Transacao() {
    }

    public Long getIdCompraDoGateway() {
        return idCompraDoGateway;
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return idCompraDoGateway.equals(transacao.idCompraDoGateway);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompraDoGateway);
    }
}
