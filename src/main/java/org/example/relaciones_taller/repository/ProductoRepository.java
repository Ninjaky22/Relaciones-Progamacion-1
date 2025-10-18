package org.example.relaciones_taller.repository;

import org.example.relaciones_taller.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoriasNombre(String nombre);
}
