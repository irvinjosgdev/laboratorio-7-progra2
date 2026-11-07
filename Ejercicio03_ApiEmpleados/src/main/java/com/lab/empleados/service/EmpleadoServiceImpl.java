package com.lab.empleados.service;

import com.lab.empleados.dto.EmpleadoRequest;
import com.lab.empleados.dto.EmpleadoResponse;
import com.lab.empleados.exception.EmpleadoNoEncontradoException;
import com.lab.empleados.mapper.EmpleadoMapper;
import com.lab.empleados.model.Empleado;
import com.lab.empleados.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
    }

    @Override
    public EmpleadoResponse crearEmpleado(EmpleadoRequest request) {
        Empleado empleadoNuevo = empleadoMapper.aEntidad(request);
        Empleado empleadoGuardado = empleadoRepository.guardar(empleadoNuevo);
        return empleadoMapper.aResponse(empleadoGuardado);
    }

    @Override
    public List<EmpleadoResponse> consultarEmpleados(String especialidad) {
        // Sin filtro se devuelve la lista completa
        List<Empleado> empleados = (especialidad == null || especialidad.isBlank())
                ? empleadoRepository.buscarTodos()
                : empleadoRepository.buscarPorEspecialidad(especialidad);

        return empleados.stream()
                .map(empleadoMapper::aResponse)
                .toList();
    }

    @Override
    public EmpleadoResponse consultarEmpleadoPorId(Long id) {
        Empleado empleado = empleadoRepository.buscarPorId(id)
                .orElseThrow(() -> new EmpleadoNoEncontradoException(id));
        return empleadoMapper.aResponse(empleado);
    }
}
