package com.rocketwave.repository;

import com.rocketwave.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("FROM Pedido p WHERE p.cliente.id = ?1")
    public List<Pedido> findPedidoByCliente(Integer idCliente);

    @Modifying
    @Query("DELETE FROM Pedido d where d.id = ?1")
    public void delete(Integer pedido);

}
