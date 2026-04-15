package com.fixnow.demo.controller;

import com.fixnow.demo.model.Incidencia;
import com.fixnow.demo.service.IncidenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {

    private final IncidenciaService service;

    public IncidenciaController(IncidenciaService service) {
        this.service = service;
    }

    // 1. Registrar nueva incidencia
    @PostMapping
    public ResponseEntity<?> registrarIncidencia(@RequestBody Incidencia incidencia) {
        try {
            Incidencia nueva = service.registrar(incidencia);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    // 2. Consultar incidencias existentes
    @GetMapping
    public ResponseEntity<List<Incidencia>> listarIncidencias() {
        return new ResponseEntity<>(service.obtenerTodas(), HttpStatus.OK); // 200 OK
    }

    // 3. Consulta específica (Buscar por prioridad)
    @GetMapping("/prioridad/{nivel}")
    public ResponseEntity<List<Incidencia>> buscarPorPrioridad(@PathVariable String nivel) {
        return new ResponseEntity<>(service.buscarPorPrioridad(nivel), HttpStatus.OK);
    }

    // 4. Actualizar información
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarIncidencia(@PathVariable String id, @RequestBody Incidencia incidencia) {
        try {
            Incidencia actualizada = service.actualizar(id, incidencia);
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    // 5. Eliminar incidencia
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarIncidencia(@PathVariable String id) {
        try {
            service.eliminar(id);
            return new ResponseEntity<>("Incidencia eliminada exitosamente", HttpStatus.NO_CONTENT); // 204 No Content
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
