package com.rocketwave.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="CLIENTE")
public class Cliente {

    @Id
    @Column(name = "ID_CLIENTE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTE")
    @SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "id_seq_cliente", allocationSize = 1)
    private Integer id;

    @Column(name = "NOME_COMPLETO")
    private String nome;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "TELEFONE")
    private String telefone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy="cliente")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonManagedReference
    private List<Pedido> pedidos;

    public Cliente() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
