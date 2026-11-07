package com.lab.proveedores.repository;

import com.lab.proveedores.model.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorRepository {

    Proveedor guardar(Proveedor proveedor);

    List<Proveedor> buscarTodos();

    List<Proveedor> buscarPorCategoria(String categoria);

    Optional<Proveedor> buscarPorId(Long id);
}
