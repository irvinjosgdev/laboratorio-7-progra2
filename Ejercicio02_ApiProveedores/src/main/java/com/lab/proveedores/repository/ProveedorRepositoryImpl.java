package com.lab.proveedores.repository;

import com.lab.proveedores.model.Proveedor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProveedorRepositoryImpl implements ProveedorRepository {

    private final List<Proveedor> proveedores = new ArrayList<>();
    private final AtomicLong generadorId = new AtomicLong(1);

    @Override
    public Proveedor guardar(Proveedor proveedor) {
        proveedor.setId(generadorId.getAndIncrement());
        proveedores.add(proveedor);
        return proveedor;
    }

    @Override
    public List<Proveedor> buscarTodos() {
        return new ArrayList<>(proveedores);
    }

    @Override
    public List<Proveedor> buscarPorCategoria(String categoria) {
        return proveedores.stream()
                .filter(proveedor -> proveedor.getCategoria().equalsIgnoreCase(categoria.trim()))
                .toList();
    }

    @Override
    public Optional<Proveedor> buscarPorId(Long id) {
        return proveedores.stream()
                .filter(proveedor -> proveedor.getId().equals(id))
                .findFirst();
    }
}
