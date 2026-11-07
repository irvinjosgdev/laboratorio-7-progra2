package com.lab.profesores.service;

import com.lab.profesores.dto.ProfesorRequest;
import com.lab.profesores.dto.ProfesorResponse;

import java.util.List;

public interface ProfesorService {

    ProfesorResponse crearProfesor(ProfesorRequest request);

    List<ProfesorResponse> consultarProfesores(String especialidad);

    ProfesorResponse consultarProfesorPorId(Long id);
}
