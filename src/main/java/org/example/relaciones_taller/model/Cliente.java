package org.example.relaciones_taller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "clientes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Direccion direccion;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    // ===== CONSTRUCTORES =====
    public Cliente() {}

    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    // ===== GETTERS & SETTERS =====
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
        if (direccion != null) {
            direccion.setCliente(this); // mantiene la relación bidireccional
        }
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    // ===== MÉTODOS AUXILIARES =====
    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
        pedido.setCliente(this);
    }

    public void removePedido(Pedido pedido) {
        pedidos.remove(pedido);
        pedido.setCliente(null);
    }
}
