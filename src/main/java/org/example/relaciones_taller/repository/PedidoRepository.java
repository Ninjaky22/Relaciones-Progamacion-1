package org.example.relaciones_taller.repository;

import org.example.relaciones_taller.model.Pedido;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @EntityGraph(attributePaths = {"items", "items.producto"})
    List<Pedido> findAll();

    List<Pedido> findByClienteId(Long clienteId);
}
