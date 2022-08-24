package com.rocketwave.controller;

import com.rocketwave.dto.ClienteDTO;
import com.rocketwave.dto.ResponseDTO;
import com.rocketwave.exception.ConflictException;
import com.rocketwave.model.Cliente;
import com.rocketwave.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api("Cliente controller")
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @ApiOperation(value = "Cadastrar um cliente")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Cliente cadastrado com sucesso!"),
            @ApiResponse(code = 422, message = "Deve ser informado (dados do cliente)."),
            @ApiResponse(code = 422, message = "Email já registrado.")})
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody ClienteDTO cliente) {

        try {
            return clienteService.salvarCliente(cliente);
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseDTO(422, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(400, e.getMessage()));
        }

    }

    @ApiOperation(value = "Listar clientes")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK")})
    @GetMapping
    public List<Cliente> listar () {
        return clienteService.listarClientes();
    }

    @ApiOperation(value = "Atualizar um cliente")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Cliente atualizado com sucesso!"),
            @ApiResponse(code = 422, message = "Deve ser informado (dados do cliente)."),
            @ApiResponse(code = 422, message = "Email já registrado.")})
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody ClienteDTO cliente) {

        try {
            return clienteService.atualizarCliente(cliente, id);
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseDTO(422, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(400, e.getMessage()));
        }
    }

    @ApiOperation(value = "Listar clientes")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Cliente removido com sucesso!")})
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
