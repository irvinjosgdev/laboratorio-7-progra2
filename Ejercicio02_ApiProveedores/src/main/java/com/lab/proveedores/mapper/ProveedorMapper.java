package com.lab.proveedores.mapper;

import com.lab.proveedores.dto.ProveedorRequest;
import com.lab.proveedores.dto.ProveedorResponse;
import com.lab.proveedores.model.Proveedor;
import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {

    public Proveedor aEntidad(ProveedorRequest request) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(request.getNombre().trim());
        proveedor.setApellido(request.getApellido().trim());
        proveedor.setCorreo(request.getCorreo().trim().toLowerCase());
        proveedor.setCategoria(request.getCategoria().trim());
        proveedor.setEstado(request.getEstado());
        return proveedor;
    }

    public ProveedorResponse aResponse(Proveedor proveedor) {
        return new ProveedorResponse(
                proveedor.getId(),
                proveedor.getNombre(),
                proveedor.getApellido(),
                proveedor.getCorreo(),
                proveedor.getCategoria(),
                proveedor.getEstado()
        );
    }
}
