package org.example.relaciones_taller.service;

import org.example.relaciones_taller.model.Cliente;
import org.example.relaciones_taller.model.Direccion;
import org.example.relaciones_taller.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente crearCliente(Cliente cliente) {
        Direccion direccion = cliente.getDireccion();
        if (direccion != null) {
            direccion.setCliente(cliente);
        }
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Optional<Cliente> obtenerPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setEmail(clienteActualizado.getEmail());
        cliente.setDireccion(clienteActualizado.getDireccion());
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
