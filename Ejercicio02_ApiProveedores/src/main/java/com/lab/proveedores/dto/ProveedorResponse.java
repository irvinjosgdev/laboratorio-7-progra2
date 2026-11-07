package com.lab.proveedores.dto;

import com.lab.proveedores.model.EstadoProveedor;

public class ProveedorResponse {

    private final Long id;
    private final String nombre;
    private final String apellido;
    private final String correo;
    private final String categoria;
    private final EstadoProveedor estado;

    public ProveedorResponse(Long id, String nombre, String apellido, String correo,
                            String categoria, EstadoProveedor estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.categoria = categoria;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCategoria() {
        return categoria;
    }

    public EstadoProveedor getEstado() {
        return estado;
    }
}
