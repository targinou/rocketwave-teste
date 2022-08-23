package com.rocketwave.service;

import com.rocketwave.dto.ResponseDTO;
import com.rocketwave.exception.ConflictException;
import com.rocketwave.exception.ValidationException;
import com.rocketwave.model.Cliente;
import com.rocketwave.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public ResponseEntity<?> salvarCliente (Cliente cliente) {

        validarCliente(cliente);

        clienteRepository.save(cliente);

        return new ResponseEntity<>(new ResponseDTO(201, "Cliente cadastrado com sucesso!"), HttpStatus.CREATED);
    }

    private void validarCliente (Cliente cliente)  {

        if (cliente.getNome() == null || Objects.equals(cliente.getNome(), "")) {
            throw new ValidationException("Deve ser informado o nome do cliente.");
        }

        if (cliente.getCpf() == null || Objects.equals(cliente.getCpf(), "")) {
            throw new ValidationException("Deve ser informado um CPF.");
        }

        if (cliente.getEmail() == null || Objects.equals(cliente.getEmail(), "")) {
            throw new ValidationException("Deve ser informado um email.");
        }

        if (clienteRepository.findClienteByEmail(cliente.getEmail()) != null) {
            throw new ConflictException("Email já registrado.");
        }

        if (cliente.getEndereco() == null || Objects.equals(cliente.getEndereco(), "")) {
            throw new ValidationException("Deve ser informado um endereço.");
        }

        if (cliente.getTelefone() == null || Objects.equals(cliente.getTelefone(), "")) {
            throw new ValidationException("Deve ser informado um telefone.");
        }

        if (cliente.getDataNascimento() == null) {
            throw new ValidationException("Deve ser informado uma data de nascimento.");
        }


    }


}
