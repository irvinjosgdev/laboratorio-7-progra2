package com.lab.profesores.dto;

import com.lab.profesores.model.EstadoProfesor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProfesorRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    // @Email por si sola acepta correos sin dominio como "juan@correo"
    @NotBlank(message = "El correo es obligatorio")
    @Email(regexp = "^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$", message = "El correo no tiene un formato valido")
    private String correo;

    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;

    @NotNull(message = "El estado es obligatorio")
    private EstadoProfesor estado;

    public ProfesorRequest() {
    }

    public ProfesorRequest(String nombre, String apellido, String correo,
                           String especialidad, EstadoProfesor estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.especialidad = especialidad;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public EstadoProfesor getEstado() {
        return estado;
    }

    public void setEstado(EstadoProfesor estado) {
        this.estado = estado;
    }
}
