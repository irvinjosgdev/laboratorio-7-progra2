package com.lab.profesores.dto;

import com.lab.profesores.model.EstadoProfesor;

public class ProfesorResponse {

    private final Long id;
    private final String nombre;
    private final String apellido;
    private final String correo;
    private final String especialidad;
    private final EstadoProfesor estado;

    public ProfesorResponse(Long id, String nombre, String apellido, String correo,
                            String especialidad, EstadoProfesor estado) {
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

    public EstadoProfesor getEstado() {
        return estado;
    }
}
