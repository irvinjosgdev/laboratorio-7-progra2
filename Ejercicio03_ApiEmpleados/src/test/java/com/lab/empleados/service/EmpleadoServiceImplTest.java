package com.lab.empleados.service;

import com.lab.empleados.dto.EmpleadoRequest;
import com.lab.empleados.dto.EmpleadoResponse;
import com.lab.empleados.exception.EmpleadoNoEncontradoException;
import com.lab.empleados.mapper.EmpleadoMapper;
import com.lab.empleados.model.EstadoEmpleado;
import com.lab.empleados.model.Empleado;
import com.lab.empleados.repository.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceImplTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    private EmpleadoServiceImpl empleadoService;

    @BeforeEach
    void configurar() {
        empleadoService = new EmpleadoServiceImpl(empleadoRepository, new EmpleadoMapper());
    }

    @Test
    @DisplayName("Crear empleado devuelve el empleado con id asignado")
    void crearEmpleado() {
        EmpleadoRequest request = new EmpleadoRequest(
                "Jorge", "Mendez", "jorge.mendez@empresa.com", "Contabilidad", EstadoEmpleado.ACTIVO);

        when(empleadoRepository.guardar(any(Empleado.class))).thenAnswer(invocacion -> {
            Empleado empleado = invocacion.getArgument(0);
            empleado.setId(1L);
            return empleado;
        });

        EmpleadoResponse resultado = empleadoService.crearEmpleado(request);

        assertNotNull(resultado.getId());
        assertEquals("Jorge", resultado.getNombre());
        assertEquals("Mendez", resultado.getApellido());
        assertEquals("Contabilidad", resultado.getEspecialidad());
        verify(empleadoRepository).guardar(any(Empleado.class));
    }

    @Test
    @DisplayName("Obtener empleado existente devuelve sus datos")
    void obtenerEmpleadoExistente() {
        Empleado empleado = new Empleado(
                5L, "Sofia", "Herrera", "sofia.herrera@empresa.com", "Sistemas", EstadoEmpleado.ACTIVO);
        when(empleadoRepository.buscarPorId(5L)).thenReturn(Optional.of(empleado));

        EmpleadoResponse resultado = empleadoService.consultarEmpleadoPorId(5L);

        assertEquals(5L, resultado.getId());
        assertEquals("Sofia", resultado.getNombre());
        assertEquals("sofia.herrera@empresa.com", resultado.getCorreo());
    }

    @Test
    @DisplayName("Obtener empleado inexistente lanza EmpleadoNoEncontradoException")
    void obtenerEmpleadoInexistente() {
        when(empleadoRepository.buscarPorId(99L)).thenReturn(Optional.empty());

        EmpleadoNoEncontradoException excepcion = assertThrows(
                EmpleadoNoEncontradoException.class,
                () -> empleadoService.consultarEmpleadoPorId(99L));

        assertEquals("No existe un empleado con el id: 99", excepcion.getMessage());
    }
}
