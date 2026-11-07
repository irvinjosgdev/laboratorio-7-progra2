package com.lab.profesores.service;

import com.lab.profesores.dto.ProfesorRequest;
import com.lab.profesores.dto.ProfesorResponse;
import com.lab.profesores.exception.ProfesorNoEncontradoException;
import com.lab.profesores.mapper.ProfesorMapper;
import com.lab.profesores.model.EstadoProfesor;
import com.lab.profesores.model.Profesor;
import com.lab.profesores.repository.ProfesorRepository;
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
class ProfesorServiceImplTest {

    @Mock
    private ProfesorRepository profesorRepository;

    private ProfesorServiceImpl profesorService;

    @BeforeEach
    void configurar() {
        profesorService = new ProfesorServiceImpl(profesorRepository, new ProfesorMapper());
    }

    @Test
    @DisplayName("Crear profesor devuelve el profesor con id asignado")
    void crearProfesor() {
        ProfesorRequest request = new ProfesorRequest(
                "Ana", "Lopez", "ana.lopez@universidad.edu", "Matematica", EstadoProfesor.ACTIVO);

        when(profesorRepository.guardar(any(Profesor.class))).thenAnswer(invocacion -> {
            Profesor profesor = invocacion.getArgument(0);
            profesor.setId(1L);
            return profesor;
        });

        ProfesorResponse resultado = profesorService.crearProfesor(request);

        assertNotNull(resultado.getId());
        assertEquals("Ana", resultado.getNombre());
        assertEquals("Lopez", resultado.getApellido());
        assertEquals("Matematica", resultado.getEspecialidad());
        verify(profesorRepository).guardar(any(Profesor.class));
    }

    @Test
    @DisplayName("Obtener profesor existente devuelve sus datos")
    void obtenerProfesorExistente() {
        Profesor profesor = new Profesor(
                5L, "Carlos", "Perez", "carlos.perez@universidad.edu", "Fisica", EstadoProfesor.ACTIVO);
        when(profesorRepository.buscarPorId(5L)).thenReturn(Optional.of(profesor));

        ProfesorResponse resultado = profesorService.consultarProfesorPorId(5L);

        assertEquals(5L, resultado.getId());
        assertEquals("Carlos", resultado.getNombre());
        assertEquals("carlos.perez@universidad.edu", resultado.getCorreo());
    }

    @Test
    @DisplayName("Obtener profesor inexistente lanza ProfesorNoEncontradoException")
    void obtenerProfesorInexistente() {
        when(profesorRepository.buscarPorId(99L)).thenReturn(Optional.empty());

        ProfesorNoEncontradoException excepcion = assertThrows(
                ProfesorNoEncontradoException.class,
                () -> profesorService.consultarProfesorPorId(99L));

        assertEquals("No existe un profesor con el id: 99", excepcion.getMessage());
    }
}
