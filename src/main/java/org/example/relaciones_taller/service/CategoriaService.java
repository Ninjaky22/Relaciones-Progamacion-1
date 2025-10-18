package org.example.relaciones_taller.service;

import org.example.relaciones_taller.model.Categoria;
import org.example.relaciones_taller.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> obtenerPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Optional<Categoria> obtenerPorNombre(String nombre) {
        return categoriaRepository.findByNombre(nombre);
    }

    public Categoria actualizarCategoria(Long id, Categoria categoriaActualizada) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        categoria.setNombre(categoriaActualizada.getNombre());
        return categoriaRepository.save(categoria);
    }

    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
