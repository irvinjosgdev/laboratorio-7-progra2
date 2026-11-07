package com.lab.empleados.controller;

import com.lab.empleados.dto.EmpleadoRequest;
import com.lab.empleados.dto.EmpleadoResponse;
import com.lab.empleados.service.EmpleadoService;
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
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponse> crearEmpleado(@Valid @RequestBody EmpleadoRequest request) {
        EmpleadoResponse empleadoCreado = empleadoService.crearEmpleado(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(empleadoCreado);
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoResponse>> consultarEmpleados(
            @RequestParam(required = false) String especialidad) {
        return ResponseEntity.ok(empleadoService.consultarEmpleados(especialidad));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> consultarEmpleadoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empleadoService.consultarEmpleadoPorId(id));
    }
}
