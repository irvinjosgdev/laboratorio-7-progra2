package com.lab.proveedores.service;

import com.lab.proveedores.dto.ProveedorRequest;
import com.lab.proveedores.dto.ProveedorResponse;

import java.util.List;

public interface ProveedorService {

    ProveedorResponse crearProveedor(ProveedorRequest request);

    List<ProveedorResponse> consultarProveedores(String categoria);

    ProveedorResponse consultarProveedorPorId(Long id);
}
