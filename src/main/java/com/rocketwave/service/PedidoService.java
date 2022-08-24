package com.rocketwave.service;

import com.rocketwave.dto.ItemDTO;
import com.rocketwave.dto.PedidoRequestDTO;
import com.rocketwave.dto.ResponseDTO;
import com.rocketwave.exception.ConflictException;
import com.rocketwave.exception.ValidationException;
import com.rocketwave.model.Cliente;
import com.rocketwave.model.Item;
import com.rocketwave.model.Pedido;
import com.rocketwave.repository.ClienteRepository;
import com.rocketwave.repository.ItemRepository;
import com.rocketwave.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ClienteService clienteService;

    public ResponseEntity<?> salvarPedido(PedidoRequestDTO pedidoDTO) {

        Cliente cliente = new Cliente();
        Pedido pedido = new Pedido();
        List<Item> itensDoPedido = new ArrayList<Item>();

        for(ItemDTO itemDoPedidoDTO : pedidoDTO.getItensDoPedido()) {
            Item item = new Item();
            item.setValorUnitario(itemDoPedidoDTO.getValorUnitario());
            item.setNome(itemDoPedidoDTO.getNome());
            item.setSku(itemDoPedidoDTO.getSku());
            item.setQuantidade(itemDoPedidoDTO.getQuantidade());
            item.setValorTotal(item.getValorUnitario() * item.getQuantidade());

            if (item.getValorUnitario() < 1) {
                throw new ConflictException("Valor unitário do item não pode ser menor que 1 centavo.");
            }

            item = itemRepository.save(item);
            itensDoPedido.add(item);
        }


        if (clienteRepository.findClienteByEmail(pedidoDTO.getEmailCliente()) != null) {
           cliente = clienteRepository.findClienteByEmail(pedidoDTO.getEmailCliente());
        } else {
            cliente.setEmail(pedidoDTO.getEmailCliente());
            cliente.setNome(pedidoDTO.getNomeCliente());
            cliente.setCpf(pedidoDTO.getCpfCliente());
            cliente.setEndereco(pedidoDTO.getEnderecoCliente());
            cliente.setDataNascimento(pedidoDTO.getDataNascimentoCliente());
            cliente.setTelefone(pedidoDTO.getTelefoneCliente());
            cliente = clienteService.salvarClienteDoPedido(cliente);
        }

        pedido = fromDTO(pedidoDTO, cliente, itensDoPedido);

        for (Item itemSalvo : itensDoPedido){
            itemSalvo.setPedido(pedido);
            itemRepository.save(itemSalvo);
        }

        clienteService.cadastrarPedidosDoCliente(cliente, pedido);

        return new ResponseEntity<>(new ResponseDTO(201, "Pedido adicionado com sucesso!"), HttpStatus.CREATED);

    }

    @Transactional
    public ResponseEntity<?> excluirPedido(Integer id) {

        if (pedidoRepository.findById(id).isPresent()){
            pedidoRepository.delete(id);
        } else {
            throw new ValidationException("Pedido não encontrado.");
        }

        return new ResponseEntity<>(new ResponseDTO(204, "Pedido removido com sucesso!"), HttpStatus.NO_CONTENT);
    }

    public Pedido fromDTO (PedidoRequestDTO pedidoDTO, Cliente cliente, List<Item> itensDoPedido) {
        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setItensDoPedido(itensDoPedido);
        pedido.setNomeCliente(pedidoDTO.getNomeCliente());
        pedido.setCpfCliente(pedidoDTO.getCpfCliente());
        pedido.setEmailCliente(pedidoDTO.getEmailCliente());
        pedido.setEnderecoCliente(pedidoDTO.getEnderecoCliente());
        pedido.setDataNascimentoCliente(pedidoDTO.getDataNascimentoCliente());
        pedido.setTelefoneCliente(pedidoDTO.getTelefoneCliente());
        pedido.setEnderecoEntrega(pedidoDTO.getEnderecoEntrega());

        Integer valorTotal = 0;
        for(Item itemDoPedido : itensDoPedido) {
            valorTotal = itemDoPedido.getValorTotal() + valorTotal;
        }

        pedido.setValorTotal(valorTotal);

        if (pedido.getValorTotal() < 1) {
            throw new ConflictException("Valor total do pedido não pode ser menor que 1 centavo.");
        }

        return pedidoRepository.save(pedido);

    }

}
