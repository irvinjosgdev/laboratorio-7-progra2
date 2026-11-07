package com.lab.proveedores.service;

import com.lab.proveedores.dto.ProveedorRequest;
import com.lab.proveedores.dto.ProveedorResponse;
import com.lab.proveedores.exception.ProveedorNoEncontradoException;
import com.lab.proveedores.mapper.ProveedorMapper;
import com.lab.proveedores.model.Proveedor;
import com.lab.proveedores.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;
    private final ProveedorMapper proveedorMapper;

    public ProveedorServiceImpl(ProveedorRepository proveedorRepository, ProveedorMapper proveedorMapper) {
        this.proveedorRepository = proveedorRepository;
        this.proveedorMapper = proveedorMapper;
    }

    @Override
    public ProveedorResponse crearProveedor(ProveedorRequest request) {
        Proveedor proveedorNuevo = proveedorMapper.aEntidad(request);
        Proveedor proveedorGuardado = proveedorRepository.guardar(proveedorNuevo);
        return proveedorMapper.aResponse(proveedorGuardado);
    }

    @Override
    public List<ProveedorResponse> consultarProveedores(String categoria) {
        // Sin filtro se devuelve la lista completa
        List<Proveedor> proveedores = (categoria == null || categoria.isBlank())
                ? proveedorRepository.buscarTodos()
                : proveedorRepository.buscarPorCategoria(categoria);

        return proveedores.stream()
                .map(proveedorMapper::aResponse)
                .toList();
    }

    @Override
    public ProveedorResponse consultarProveedorPorId(Long id) {
        Proveedor proveedor = proveedorRepository.buscarPorId(id)
                .orElseThrow(() -> new ProveedorNoEncontradoException(id));
        return proveedorMapper.aResponse(proveedor);
    }
}
