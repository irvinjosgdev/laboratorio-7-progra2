package com.lab.profesores.repository;

import com.lab.profesores.model.Profesor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProfesorRepositoryImpl implements ProfesorRepository {

    private final List<Profesor> profesores = new ArrayList<>();
    private final AtomicLong generadorId = new AtomicLong(1);

    @Override
    public Profesor guardar(Profesor profesor) {
        profesor.setId(generadorId.getAndIncrement());
        profesores.add(profesor);
        return profesor;
    }

    @Override
    public List<Profesor> buscarTodos() {
        return new ArrayList<>(profesores);
    }

    @Override
    public List<Profesor> buscarPorEspecialidad(String especialidad) {
        return profesores.stream()
                .filter(profesor -> profesor.getEspecialidad().equalsIgnoreCase(especialidad.trim()))
                .toList();
    }

    @Override
    public Optional<Profesor> buscarPorId(Long id) {
        return profesores.stream()
                .filter(profesor -> profesor.getId().equals(id))
                .findFirst();
    }
}
