package com.rocketwave.dto;

import com.rocketwave.model.Pedido;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ItemDTO {

    private String sku;
    private String nome;
    private Integer valorUnitario;
    private Integer quantidade;

    public ItemDTO() {
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

    public Integer getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Integer valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
