package com.lab.empleados.repository;

import com.lab.empleados.model.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository {

    Empleado guardar(Empleado empleado);

    List<Empleado> buscarTodos();

    List<Empleado> buscarPorEspecialidad(String especialidad);

    Optional<Empleado> buscarPorId(Long id);
}
