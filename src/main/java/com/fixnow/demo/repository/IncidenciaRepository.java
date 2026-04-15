package com.fixnow.demo.repository;

import com.fixnow.demo.model.Incidencia;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class IncidenciaRepository {
    private final List<Incidencia> incidencias = new ArrayList<>();

    public Incidencia save(Incidencia incidencia) {
        incidencias.add(incidencia);
        return incidencia;
    }

    public List<Incidencia> findAll() {
        return incidencias;
    }

    public Optional<Incidencia> findById(String id) {
        return incidencias.stream()
            .filter(i -> i.getId().equals(id))
            .findFirst();
    }

    public Incidencia update(Incidencia incidenciaActualizada) {
        for (int i = 0; i < incidencias.size(); i++) {
            if (incidencias.get(i).getId().equals(incidenciaActualizada.getId())) {
                incidencias.set(i, incidenciaActualizada);
                return incidenciaActualizada;
            }
        }
        return null;
    }

    public boolean delete(String id) {
        return incidencias.removeIf(i -> i.getId().equals(id));
    }

    public List<Incidencia> findByPrioridad(String nivelPrioridad) {
        return incidencias.stream()
            .filter(i -> i.getNivelPrioridad().equalsIgnoreCase(nivelPrioridad))
            .collect(Collectors.toList());
    }
}
