package com.rocketwave.repository;

import com.rocketwave.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("FROM Cliente c WHERE c.email = ?1")
    public Cliente findClienteByEmail(String email);

   @Modifying
   @Query("DELETE FROM Cliente c where c.id = ?1")
    void delete(Integer cliente);
}
