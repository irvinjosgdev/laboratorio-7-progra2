package com.lab.profesores.controller;

import com.lab.profesores.dto.ProfesorRequest;
import com.lab.profesores.dto.ProfesorResponse;
import com.lab.profesores.service.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @PostMapping
    public ResponseEntity<ProfesorResponse> crearProfesor(@Valid @RequestBody ProfesorRequest request) {
        ProfesorResponse profesorCreado = profesorService.crearProfesor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(profesorCreado);
    }

    @GetMapping
    public ResponseEntity<List<ProfesorResponse>> consultarProfesores(
            @RequestParam(required = false) String especialidad) {
        return ResponseEntity.ok(profesorService.consultarProfesores(especialidad));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorResponse> consultarProfesorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(profesorService.consultarProfesorPorId(id));
    }
}
