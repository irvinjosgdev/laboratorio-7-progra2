package com.lab.profesores.repository;

import com.lab.profesores.model.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorRepository {

    Profesor guardar(Profesor profesor);

    List<Profesor> buscarTodos();

    List<Profesor> buscarPorEspecialidad(String especialidad);

    Optional<Profesor> buscarPorId(Long id);
}
