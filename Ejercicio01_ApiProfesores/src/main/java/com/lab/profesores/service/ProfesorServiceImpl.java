package com.lab.profesores.service;

import com.lab.profesores.dto.ProfesorRequest;
import com.lab.profesores.dto.ProfesorResponse;
import com.lab.profesores.exception.ProfesorNoEncontradoException;
import com.lab.profesores.mapper.ProfesorMapper;
import com.lab.profesores.model.Profesor;
import com.lab.profesores.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final ProfesorMapper profesorMapper;

    public ProfesorServiceImpl(ProfesorRepository profesorRepository, ProfesorMapper profesorMapper) {
        this.profesorRepository = profesorRepository;
        this.profesorMapper = profesorMapper;
    }

    @Override
    public ProfesorResponse crearProfesor(ProfesorRequest request) {
        Profesor profesorNuevo = profesorMapper.aEntidad(request);
        Profesor profesorGuardado = profesorRepository.guardar(profesorNuevo);
        return profesorMapper.aResponse(profesorGuardado);
    }

    @Override
    public List<ProfesorResponse> consultarProfesores(String especialidad) {
        // Sin filtro se devuelve la lista completa
        List<Profesor> profesores = (especialidad == null || especialidad.isBlank())
                ? profesorRepository.buscarTodos()
                : profesorRepository.buscarPorEspecialidad(especialidad);

        return profesores.stream()
                .map(profesorMapper::aResponse)
                .toList();
    }

    @Override
    public ProfesorResponse consultarProfesorPorId(Long id) {
        Profesor profesor = profesorRepository.buscarPorId(id)
                .orElseThrow(() -> new ProfesorNoEncontradoException(id));
        return profesorMapper.aResponse(profesor);
    }
}
