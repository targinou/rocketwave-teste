package com.rocketwave.dto;

import com.rocketwave.model.Item;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.List;

public class PedidoRequestDTO {

    private Integer id;
    private String nomeCliente;
    private String cpfCliente;
    private String emailCliente;
    private String enderecoCliente;
    private String telefoneCliente;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimentoCliente;
    private String enderecoEntrega;
    private Long valorTotal;
    private List<ItemDTO> itensDoPedido;

    public PedidoRequestDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public LocalDate getDataNascimentoCliente() {
        return dataNascimentoCliente;
    }

    public void setDataNascimentoCliente(LocalDate dataNascimentoCliente) {
        this.dataNascimentoCliente = dataNascimentoCliente;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Long getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Long valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemDTO> getItensDoPedido() {
        return itensDoPedido;
    }

    public void setItensDoPedido(List<ItemDTO> itensDoPedido) {
        this.itensDoPedido = itensDoPedido;
    }
}
