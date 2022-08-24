package com.rocketwave.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="PEDIDO")
public class Pedido {

    @Id
    @Column(name = "ID_PEDIDO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PEDIDO")
    @SequenceGenerator(name = "SEQ_PEDIDO", sequenceName = "id_seq_pedido", allocationSize = 1)
    private Integer id;

    @Column(name = "NOME_CLIENTE")
    private String nomeCliente;

    @Column(name = "CPF_CLIENTE")
    private String cpfCliente;

    @Column(name = "EMAIL_CLIENTE")
    private String emailCliente;

    @Column(name = "ENDERECO_CLIENTE")
    private String enderecoCliente;

    @Column(name = "TELEFONE_CLIENTE")
    private String telefoneCliente;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATA_NASCIMENTO_CLIENTE")
    private LocalDate dataNascimentoCliente;

    @Column(name = "ENDERECO_ENTREGA")
    private String enderecoEntrega;

    @Column(name = "VALOR_TOTAL_CENTAVOS")
    private Integer valorTotal;

    @OneToMany(mappedBy="pedido", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Item> itensDoPedido;

    @ManyToOne
    @JoinColumn(name="ID_CLIENTE", nullable = true)
    @JsonBackReference
    private Cliente cliente;

    public Pedido() {
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

    public Integer getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Integer valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Item> getItensDoPedido() {
        return itensDoPedido;
    }

    public void setItensDoPedido(List<Item> itensDoPedido) {
        this.itensDoPedido = itensDoPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
