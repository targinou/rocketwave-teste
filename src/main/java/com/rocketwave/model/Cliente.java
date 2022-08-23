package com.rocketwave.model;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Entity
public class Cliente {

    @Id
    @Column(name = "ID_CLIENTE")
    private Integer id;

    @Column(name = "NOME_COMPLETO")
    private String nome;

    @Column(name = "CPF", unique = true)
    @CPF(message = "CPF inválido")
    private String cpf;

    @Column(name = "EMAIL", unique = true)
    @Email(message = "E-mail inválido!")
    private String email;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "TELEFONE")
    private String telefone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    public Cliente() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
