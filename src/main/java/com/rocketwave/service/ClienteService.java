package com.rocketwave.service;

import com.rocketwave.dto.ClienteDTO;
import com.rocketwave.dto.ResponseDTO;
import com.rocketwave.exception.ConflictException;
import com.rocketwave.exception.ValidationException;
import com.rocketwave.model.Cliente;
import com.rocketwave.model.Pedido;
import com.rocketwave.repository.ClienteRepository;
import com.rocketwave.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public ResponseEntity<?> salvarCliente (ClienteDTO clienteDTO) {

        Cliente cliente = fromDTO(clienteDTO);
        validarCliente(cliente);

        List<Pedido> pedidos = new ArrayList<Pedido>();
        cliente.setPedidos(pedidos);

        clienteRepository.save(cliente);

        return new ResponseEntity<>(new ResponseDTO(201, "Cliente cadastrado com sucesso!"), HttpStatus.CREATED);
    }

    public ResponseEntity<?> atualizarCliente (ClienteDTO clienteDTO, Integer id) {

        Cliente cliente = fromDTO(clienteDTO);
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

            List<Pedido> listaDePedidosDoCliente = pedidoRepository.findPedidoByCliente(id);

            for(Pedido pedido : listaDePedidosDoCliente) {
                pedido.setCliente(null);
                pedidoRepository.save(pedido);
            }

            clienteRepository.findById(id).get().setPedidos(null);

            clienteRepository.delete(id);
        } else {
            throw new ValidationException("Cliente não encontrado.");
        }

        return new ResponseEntity<>(new ResponseDTO(204, "Cliente removido com sucesso!"), HttpStatus.NO_CONTENT);
    }

    public Cliente salvarClienteDoPedido (Cliente cliente) {

        validarCliente(cliente);
        List<Pedido> pedidos = new ArrayList<Pedido>();
        cliente.setPedidos(pedidos);
        return clienteRepository.save(cliente);

    }

    public void cadastrarPedidosDoCliente (Cliente cliente, Pedido pedido) {

        List<Pedido> pedidosDoCliente = cliente.getPedidos();
        pedidosDoCliente.add(pedido);
        cliente.setPedidos(pedidosDoCliente);
        clienteRepository.save(cliente);

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

    public Cliente fromDTO (ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();

        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());

        return cliente;
    }




}
