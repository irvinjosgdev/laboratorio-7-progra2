package com.lab.empleados.dto;

import com.lab.empleados.model.EstadoEmpleado;

public class EmpleadoResponse {

    private final Long id;
    private final String nombre;
    private final String apellido;
    private final String correo;
    private final String especialidad;
    private final EstadoEmpleado estado;

    public EmpleadoResponse(Long id, String nombre, String apellido, String correo,
                            String especialidad, EstadoEmpleado estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.especialidad = especialidad;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public EstadoEmpleado getEstado() {
        return estado;
    }
}
