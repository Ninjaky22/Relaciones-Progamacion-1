package org.example.relaciones_taller.service;

import org.example.relaciones_taller.model.Categoria;
import org.example.relaciones_taller.model.Producto;
import org.example.relaciones_taller.repository.CategoriaRepository;
import org.example.relaciones_taller.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crear producto
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Asignar categorías a un producto
    public Producto asignarCategorias(Long productoId, Set<String> nombresCategorias) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        for (String nombre : nombresCategorias) {
            Categoria categoria = categoriaRepository.findByNombre(nombre)
                    .orElseGet(() -> categoriaRepository.save(new Categoria(nombre)));
            producto.getCategorias().add(categoria);
        }

        return productoRepository.save(producto);
    }

    // Listar todos los productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Buscar productos por nombre de categoría
    public List<Producto> buscarPorCategoria(String nombreCategoria) {
        return productoRepository.findByCategoriasNombre(nombreCategoria);
    }

    // Obtener producto por ID
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Actualizar precio o stock
    public Producto actualizarProducto(Long id, BigDecimal nuevoPrecio, Integer nuevoStock) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (nuevoPrecio != null) producto.setPrecio(nuevoPrecio);
        if (nuevoStock != null) producto.setStock(nuevoStock);

        return productoRepository.save(producto);
    }

    // Eliminar producto
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
