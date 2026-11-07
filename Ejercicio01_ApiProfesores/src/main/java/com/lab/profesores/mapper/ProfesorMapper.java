package com.lab.profesores.mapper;

import com.lab.profesores.dto.ProfesorRequest;
import com.lab.profesores.dto.ProfesorResponse;
import com.lab.profesores.model.Profesor;
import org.springframework.stereotype.Component;

@Component
public class ProfesorMapper {

    public Profesor aEntidad(ProfesorRequest request) {
        Profesor profesor = new Profesor();
        profesor.setNombre(request.getNombre().trim());
        profesor.setApellido(request.getApellido().trim());
        profesor.setCorreo(request.getCorreo().trim().toLowerCase());
        profesor.setEspecialidad(request.getEspecialidad().trim());
        profesor.setEstado(request.getEstado());
        return profesor;
    }

    public ProfesorResponse aResponse(Profesor profesor) {
        return new ProfesorResponse(
                profesor.getId(),
                profesor.getNombre(),
                profesor.getApellido(),
                profesor.getCorreo(),
                profesor.getEspecialidad(),
                profesor.getEstado()
        );
    }
}
