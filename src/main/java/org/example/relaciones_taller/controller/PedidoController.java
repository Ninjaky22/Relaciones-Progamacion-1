package org.example.relaciones_taller.controller;

import org.example.relaciones_taller.model.ItemPedido;
import org.example.relaciones_taller.model.Pedido;
import org.example.relaciones_taller.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Crear pedido para un cliente
    @PostMapping("/cliente/{clienteId}")
    public ResponseEntity<Pedido> crearPedido(
            @PathVariable Long clienteId,
            @RequestBody List<ItemPedido> items) {
        return ResponseEntity.ok(pedidoService.crearPedido(clienteId, items));
    }

    // Cambiar estado
    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String valor) {
        return ResponseEntity.ok(pedidoService.cambiarEstado(id, valor));
    }

    // Cancelar pedido
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Pedido> cancelarPedido(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.cancelarPedido(id));
    }

    // Obtener pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedido(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.obtenerPedido(id));
    }

    // Listar todos los pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }
}
