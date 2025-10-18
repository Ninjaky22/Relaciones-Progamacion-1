package org.example.relaciones_taller.service;

import org.example.relaciones_taller.model.*;
import org.example.relaciones_taller.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    /**
     * Crear un pedido con sus ítems
     */
    @Transactional
    public Pedido crearPedido(Long clienteId, List<ItemPedido> items) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado("NUEVO");
        pedido.setTotal(BigDecimal.ZERO);

        // Agregar items al pedido
        BigDecimal total = BigDecimal.ZERO;

        for (ItemPedido item : items) {
            Producto producto = productoRepository.findById(item.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // Validar stock
            if (producto.getStock() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            // Descontar stock
            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);

            // Calcular precio unitario y subtotal
            item.setPrecioUnitario(producto.getPrecio());
            item.setPedido(pedido);
            total = total.add(producto.getPrecio().multiply(BigDecimal.valueOf(item.getCantidad())));
        }

        pedido.setTotal(total);
        pedidoRepository.save(pedido);

        for (ItemPedido item : items) {
            itemPedidoRepository.save(item);
        }

        return pedido;
    }

    /**
     * Cambiar el estado de un pedido
     */
    @Transactional
    public Pedido cambiarEstado(Long pedidoId, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedido.setEstado(nuevoEstado);
        return pedidoRepository.save(pedido);
    }

    /**
     * Cancelar un pedido y revertir el stock
     */
    @Transactional
    public Pedido cancelarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (!pedido.getEstado().equals("NUEVO")) {
            throw new RuntimeException("Solo se pueden cancelar pedidos en estado NUEVO");
        }

        for (ItemPedido item : pedido.getItems()) {
            Producto producto = item.getProducto();
            producto.setStock(producto.getStock() + item.getCantidad());
            productoRepository.save(producto);
        }

        pedido.setEstado("CANCELADO");
        return pedidoRepository.save(pedido);
    }

    public Pedido obtenerPedido(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}
