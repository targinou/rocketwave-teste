package com.rocketwave.controller;

import com.rocketwave.dto.PedidoRequestDTO;

import com.rocketwave.dto.ResponseDTO;
import com.rocketwave.exception.ConflictException;
import com.rocketwave.service.PedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("Pedido controller")
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @ApiOperation(value = "Salvar um pedido")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Pedido adicionado com sucesso!"),
            @ApiResponse(code = 422, message = "Deve ser informado (dados do cliente).")})
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody PedidoRequestDTO pedidoDTO) {
        try {
            return pedidoService.salvarPedido(pedidoDTO);
        } catch (ConflictException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseDTO(422, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(400, e.getMessage()));
        }
    }

    @ApiOperation(value = "Deleta um pedido por id")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Pedido removido com sucesso!"),
            @ApiResponse(code = 400, message = "Pedido não encontrado.")})
    @DeleteMapping("{id}")
    public ResponseEntity<?> excluir (@PathVariable Integer id) {
        try {
            return pedidoService.excluirPedido(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(400, e.getMessage()));
        }
    }
}
