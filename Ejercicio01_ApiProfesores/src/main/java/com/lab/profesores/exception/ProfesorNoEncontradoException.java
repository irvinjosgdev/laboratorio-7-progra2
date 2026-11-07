package com.lab.profesores.exception;

public class ProfesorNoEncontradoException extends RuntimeException {

    public ProfesorNoEncontradoException(Long id) {
        super("No existe un profesor con el id: " + id);
    }
}
