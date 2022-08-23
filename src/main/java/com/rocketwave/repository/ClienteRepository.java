package com.rocketwave.repository;

import com.rocketwave.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("FROM Cliente c WHERE c.email = ?1")
    public Cliente findClienteByEmail(String email);

}
