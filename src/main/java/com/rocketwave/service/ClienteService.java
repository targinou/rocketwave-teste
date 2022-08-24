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

import javax.transaction.Transactional;
import java.util.List;
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

    public ResponseEntity<?> atualizarCliente (Cliente cliente, Integer id) {

        cliente.setId(id);


        if (clienteRepository.findById(id).isPresent()) {
            Cliente clienteSalvo = clienteRepository.findById(id).get();
            validarCliente(cliente);
            clienteSalvo.setNome(cliente.getNome());
            clienteSalvo.setCpf(cliente.getCpf());
            clienteSalvo.setEmail(cliente.getEmail());
            clienteSalvo.setTelefone(cliente.getTelefone());
            clienteSalvo.setDataNascimento(cliente.getDataNascimento());
            clienteSalvo.setEndereco(cliente.getEndereco());
            clienteRepository.save(clienteSalvo);

        } else {
            throw new ValidationException("Cliente não encontrado.");
        }

        return new ResponseEntity<>(new ResponseDTO(201, "Cliente atualizado com sucesso!"), HttpStatus.CREATED);

    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Transactional
    public ResponseEntity<?> excluirCliente(Integer id) {

        if (clienteRepository.findById(id).isPresent()){
            clienteRepository.delete(id);
        } else {
            throw new ValidationException("Cliente não encontrado.");
        }

        return new ResponseEntity<>(new ResponseDTO(204, "Cliente removido com sucesso!"), HttpStatus.NO_CONTENT);
    }


    private void validarCliente (Cliente cliente)  {

        if (cliente.getNome() == null || Objects.equals(cliente.getNome(), "")) {
            throw new ConflictException("Deve ser informado o nome do cliente.");
        }

        if (cliente.getCpf() == null || Objects.equals(cliente.getCpf(), "")) {
            throw new ConflictException("Deve ser informado um CPF.");
        }

        if (cliente.getEmail() == null || Objects.equals(cliente.getEmail(), "")) {
            throw new ConflictException("Deve ser informado um email.");
        }

        if (clienteRepository.findClienteByEmail(cliente.getEmail()) != null &&
                !Objects.equals(clienteRepository.findClienteByEmail(cliente.getEmail()).getId(), cliente.getId())) {
            throw new ConflictException("Email já registrado.");
        }

        if (cliente.getEndereco() == null || Objects.equals(cliente.getEndereco(), "")) {
            throw new ConflictException("Deve ser informado um endereço.");
        }

        if (cliente.getTelefone() == null || Objects.equals(cliente.getTelefone(), "")) {
            throw new ConflictException("Deve ser informado um telefone.");
        }

        if (cliente.getDataNascimento() == null) {
            throw new ConflictException("Deve ser informado uma data de nascimento.");
        }

    }


}
