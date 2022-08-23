package com.rocketwave.model;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @Column(name = "ID_ITEM")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "SKU")
    private String sku;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "VALOR_UNITARIO_CENTAVOS")
    private Long valorUnitario;

    @Column(name = "VALOR_TOTAL_CENTAVOS")
    private Long valorTotal;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name="PEDIDO_ID", nullable=false)
    private Pedido pedido;


    public Item() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Long valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Long getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Long valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
