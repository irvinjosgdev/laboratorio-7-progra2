package com.lab.proveedores.dto;

import com.lab.proveedores.model.EstadoProveedor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProveedorRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    // @Email por si sola acepta correos sin dominio como "juan@correo"
    @NotBlank(message = "El correo es obligatorio")
    @Email(regexp = "^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$", message = "El correo no tiene un formato valido")
    private String correo;

    @NotBlank(message = "La categoria es obligatoria")
    private String categoria;

    @NotNull(message = "El estado es obligatorio")
    private EstadoProveedor estado;

    public ProveedorRequest() {
    }

    public ProveedorRequest(String nombre, String apellido, String correo,
                           String categoria, EstadoProveedor estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.categoria = categoria;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public EstadoProveedor getEstado() {
        return estado;
    }

    public void setEstado(EstadoProveedor estado) {
        this.estado = estado;
    }
}
