package com.lab.proveedores.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class ManejadorExcepciones {

    @ExceptionHandler(ProveedorNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> manejarProveedorNoEncontrado(ProveedorNoEncontradoException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage(), List.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> manejarValidacion(MethodArgumentNotValidException ex) {
        List<String> errores = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        return construirRespuesta(HttpStatus.BAD_REQUEST, "Los datos del proveedor no son validos", errores);
    }

    // Cubre JSON mal formado y valores de estado que no existen en el enum
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> manejarCuerpoInvalido(HttpMessageNotReadableException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST,
                "El cuerpo de la solicitud no es valido. El estado debe ser ACTIVO o INACTIVO", List.of());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> manejarTipoInvalido(MethodArgumentTypeMismatchException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST,
                "El parametro " + ex.getName() + " debe ser numerico", List.of());
    }

    private ResponseEntity<ErrorResponse> construirRespuesta(HttpStatus estado, String mensaje,
                                                             List<String> detalles) {
        ErrorResponse error = new ErrorResponse(estado.value(), estado.getReasonPhrase(), mensaje, detalles);
        return ResponseEntity.status(estado).body(error);
    }
}
