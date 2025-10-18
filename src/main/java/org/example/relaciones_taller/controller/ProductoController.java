package org.example.relaciones_taller.controller;

import org.example.relaciones_taller.model.Producto;
import org.example.relaciones_taller.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Crear producto
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.crearProducto(producto));
    }

    // Asignar categorías a un producto
    @PostMapping("/{id}/categorias")
    public ResponseEntity<Producto> asignarCategorias(
            @PathVariable Long id,
            @RequestBody Set<String> nombresCategorias) {
        return ResponseEntity.ok(productoService.asignarCategorias(id, nombresCategorias));
    }

    // Listar todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(
            @RequestParam(required = false) String categoria) {
        if (categoria != null) {
            return ResponseEntity.ok(productoService.buscarPorCategoria(categoria));
        }
        return ResponseEntity.ok(productoService.listarProductos());
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar precio o stock
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @RequestParam(required = false) BigDecimal precio,
            @RequestParam(required = false) Integer stock) {
        return ResponseEntity.ok(productoService.actualizarProducto(id, precio, stock));
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
