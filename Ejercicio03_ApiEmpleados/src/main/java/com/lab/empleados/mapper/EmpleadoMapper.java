package com.lab.empleados.mapper;

import com.lab.empleados.dto.EmpleadoRequest;
import com.lab.empleados.dto.EmpleadoResponse;
import com.lab.empleados.model.Empleado;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {

    public Empleado aEntidad(EmpleadoRequest request) {
        Empleado empleado = new Empleado();
        empleado.setNombre(request.getNombre().trim());
        empleado.setApellido(request.getApellido().trim());
        empleado.setCorreo(request.getCorreo().trim().toLowerCase());
        empleado.setEspecialidad(request.getEspecialidad().trim());
        empleado.setEstado(request.getEstado());
        return empleado;
    }

    public EmpleadoResponse aResponse(Empleado empleado) {
        return new EmpleadoResponse(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getCorreo(),
                empleado.getEspecialidad(),
                empleado.getEstado()
        );
    }
}
