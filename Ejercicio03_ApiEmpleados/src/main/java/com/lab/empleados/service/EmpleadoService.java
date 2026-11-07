package com.lab.empleados.service;

import com.lab.empleados.dto.EmpleadoRequest;
import com.lab.empleados.dto.EmpleadoResponse;

import java.util.List;

public interface EmpleadoService {

    EmpleadoResponse crearEmpleado(EmpleadoRequest request);

    List<EmpleadoResponse> consultarEmpleados(String especialidad);

    EmpleadoResponse consultarEmpleadoPorId(Long id);
}
