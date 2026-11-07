package com.lab.proveedores.exception;

public class ProveedorNoEncontradoException extends RuntimeException {

    public ProveedorNoEncontradoException(Long id) {
        super("No existe un proveedor con el id: " + id);
    }
}
