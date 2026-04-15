package com.fixnow.demo.service;

import com.fixnow.demo.model.Incidencia;
import com.fixnow.demo.repository.IncidenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenciaService {
    
    private final IncidenciaRepository repository;

    public IncidenciaService(IncidenciaRepository repository) {
        this.repository = repository;
    }

    public Incidencia registrar(Incidencia incidencia) {
        if (incidencia.getDescripcion() == null || incidencia.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria.");
        }
        if (incidencia.getNivelPrioridad() == null || incidencia.getNivelPrioridad().isEmpty()) {
            throw new IllegalArgumentException("El nivel de prioridad es obligatorio.");
        }
        return repository.save(incidencia);
    }

    public List<Incidencia> obtenerTodas() {
        return repository.findAll();
    }

    public Incidencia actualizar(String id, Incidencia datosActualizados) {
        Incidencia existente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con ID: " + id));
        
        existente.setDescripcion(datosActualizados.getDescripcion());
        existente.setEstado(datosActualizados.getEstado());
        existente.setNivelPrioridad(datosActualizados.getNivelPrioridad());
        
        return repository.update(existente);
    }

    public void eliminar(String id) {
        boolean eliminado = repository.delete(id);
        if (!eliminado) {
            throw new RuntimeException("No se pudo eliminar: Incidencia no encontrada.");
        }
    }

    public List<Incidencia> buscarPorPrioridad(String prioridad) {
        return repository.findByPrioridad(prioridad);
    }
}
