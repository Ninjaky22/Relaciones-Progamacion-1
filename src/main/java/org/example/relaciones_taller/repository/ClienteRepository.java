package org.example.relaciones_taller.repository;

import org.example.relaciones_taller.model.Cliente;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

    @EntityGraph(attributePaths = "direccion")
    List<Cliente> findAll();
}
