package com.rocketwave.controller;

import com.rocketwave.dto.ResponseDTO;
import com.rocketwave.exception.ConflictException;
import com.rocketwave.model.Cliente;
import com.rocketwave.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cliente cliente) {

        try {
            return clienteService.salvarCliente(cliente);
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseDTO(422, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(400, e.getMessage()));
        }

    }

    @GetMapping
    public List<Cliente> listar () {
        return clienteService.listarClientes();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {

        try {
            return clienteService.atualizarCliente(cliente, id);
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseDTO(422, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(400, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir (@PathVariable Integer id) {
        try {
            return clienteService.excluirCliente(id);
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseDTO(422, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(400, e.getMessage()));
        }
    }

}
