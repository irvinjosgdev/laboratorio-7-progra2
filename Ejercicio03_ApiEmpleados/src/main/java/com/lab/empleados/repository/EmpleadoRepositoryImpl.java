package com.lab.empleados.repository;

import com.lab.empleados.model.Empleado;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmpleadoRepositoryImpl implements EmpleadoRepository {

    private final List<Empleado> empleados = new ArrayList<>();
    private final AtomicLong generadorId = new AtomicLong(1);

    @Override
    public Empleado guardar(Empleado empleado) {
        empleado.setId(generadorId.getAndIncrement());
        empleados.add(empleado);
        return empleado;
    }

    @Override
    public List<Empleado> buscarTodos() {
        return new ArrayList<>(empleados);
    }

    @Override
    public List<Empleado> buscarPorEspecialidad(String especialidad) {
        return empleados.stream()
                .filter(empleado -> empleado.getEspecialidad().equalsIgnoreCase(especialidad.trim()))
                .toList();
    }

    @Override
    public Optional<Empleado> buscarPorId(Long id) {
        return empleados.stream()
                .filter(empleado -> empleado.getId().equals(id))
                .findFirst();
    }
}
