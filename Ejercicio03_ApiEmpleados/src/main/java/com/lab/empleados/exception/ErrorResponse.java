package com.lab.empleados.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

    private final LocalDateTime fecha;
    private final int estado;
    private final String error;
    private final String mensaje;
    private final List<String> detalles;

    public ErrorResponse(int estado, String error, String mensaje, List<String> detalles) {
        this.fecha = LocalDateTime.now();
        this.estado = estado;
        this.error = error;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public int getEstado() {
        return estado;
    }

    public String getError() {
        return error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public List<String> getDetalles() {
        return detalles;
    }
}
