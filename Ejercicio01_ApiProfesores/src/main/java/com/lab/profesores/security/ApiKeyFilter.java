package com.lab.profesores.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private static final String NOMBRE_HEADER = "X-API-KEY";

    private final String apiKeyValida;

    public ApiKeyFilter(@Value("${api.key}") String apiKeyValida) {
        this.apiKeyValida = apiKeyValida;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String apiKeyRecibida = request.getHeader(NOMBRE_HEADER);

        if (apiKeyRecibida == null || !apiKeyRecibida.equals(apiKeyValida)) {
            responderNoAutorizado(response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    // Solo se protegen las rutas de la API
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURI().startsWith("/api/");
    }

    private void responderNoAutorizado(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                "{\"estado\":401,\"error\":\"Unauthorized\","
                        + "\"mensaje\":\"API Key ausente o invalida. Envie el header " + NOMBRE_HEADER + "\"}");
    }
}
