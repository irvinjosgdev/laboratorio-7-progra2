package com.lab.proveedores.controller;

import com.lab.proveedores.dto.ProveedorRequest;
import com.lab.proveedores.dto.ProveedorResponse;
import com.lab.proveedores.service.ProveedorService;
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
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @PostMapping
    public ResponseEntity<ProveedorResponse> crearProveedor(@Valid @RequestBody ProveedorRequest request) {
        ProveedorResponse proveedorCreado = proveedorService.crearProveedor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorCreado);
    }

    @GetMapping
    public ResponseEntity<List<ProveedorResponse>> consultarProveedores(
            @RequestParam(required = false) String categoria) {
        return ResponseEntity.ok(proveedorService.consultarProveedores(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponse> consultarProveedorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.consultarProveedorPorId(id));
    }
}
