package org.example.relaciones_taller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "pedidos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    @Column(nullable = false)
    private String estado; // NUEVO, PAGADO, ENVIADO, CANCELADO, etc.

    @Column(nullable = false)
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItemPedido> items = new ArrayList<>();

    // ===== CONSTRUCTORES =====
    public Pedido() {
        this.fecha = LocalDateTime.now();
        this.estado = "NUEVO";
        this.total = BigDecimal.ZERO;
    }

    public Pedido(LocalDateTime fecha, String estado, BigDecimal total) {
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
    }

    // ===== GETTERS & SETTERS =====
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }

    // ===== MÉTODOS AUXILIARES =====
    public void addItem(ItemPedido item) {
        items.add(item);
        item.setPedido(this);
    }

    public void removeItem(ItemPedido item) {
        items.remove(item);
        item.setPedido(null);
    }
}
