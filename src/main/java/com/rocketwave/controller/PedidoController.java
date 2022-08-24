package com.rocketwave.controller;

import com.rocketwave.dto.PedidoRequestDTO;

import com.rocketwave.dto.ResponseDTO;
import com.rocketwave.exception.ConflictException;
import com.rocketwave.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

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
}
