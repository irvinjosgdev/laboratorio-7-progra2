package com.lab.proveedores.service;

import com.lab.proveedores.dto.ProveedorRequest;
import com.lab.proveedores.dto.ProveedorResponse;
import com.lab.proveedores.exception.ProveedorNoEncontradoException;
import com.lab.proveedores.mapper.ProveedorMapper;
import com.lab.proveedores.model.EstadoProveedor;
import com.lab.proveedores.model.Proveedor;
import com.lab.proveedores.repository.ProveedorRepository;
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
class ProveedorServiceImplTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    private ProveedorServiceImpl proveedorService;

    @BeforeEach
    void configurar() {
        proveedorService = new ProveedorServiceImpl(proveedorRepository, new ProveedorMapper());
    }

    @Test
    @DisplayName("Crear proveedor devuelve el proveedor con id asignado")
    void crearProveedor() {
        ProveedorRequest request = new ProveedorRequest(
                "Mario", "Castillo", "mario.castillo@distribuidora.com", "Tecnologia", EstadoProveedor.ACTIVO);

        when(proveedorRepository.guardar(any(Proveedor.class))).thenAnswer(invocacion -> {
            Proveedor proveedor = invocacion.getArgument(0);
            proveedor.setId(1L);
            return proveedor;
        });

        ProveedorResponse resultado = proveedorService.crearProveedor(request);

        assertNotNull(resultado.getId());
        assertEquals("Mario", resultado.getNombre());
        assertEquals("Castillo", resultado.getApellido());
        assertEquals("Tecnologia", resultado.getCategoria());
        verify(proveedorRepository).guardar(any(Proveedor.class));
    }

    @Test
    @DisplayName("Obtener proveedor existente devuelve sus datos")
    void obtenerProveedorExistente() {
        Proveedor proveedor = new Proveedor(
                5L, "Lucia", "Ramirez", "lucia.ramirez@distribuidora.com", "Papeleria", EstadoProveedor.ACTIVO);
        when(proveedorRepository.buscarPorId(5L)).thenReturn(Optional.of(proveedor));

        ProveedorResponse resultado = proveedorService.consultarProveedorPorId(5L);

        assertEquals(5L, resultado.getId());
        assertEquals("Lucia", resultado.getNombre());
        assertEquals("lucia.ramirez@distribuidora.com", resultado.getCorreo());
    }

    @Test
    @DisplayName("Obtener proveedor inexistente lanza ProveedorNoEncontradoException")
    void obtenerProveedorInexistente() {
        when(proveedorRepository.buscarPorId(99L)).thenReturn(Optional.empty());

        ProveedorNoEncontradoException excepcion = assertThrows(
                ProveedorNoEncontradoException.class,
                () -> proveedorService.consultarProveedorPorId(99L));

        assertEquals("No existe un proveedor con el id: 99", excepcion.getMessage());
    }
}
