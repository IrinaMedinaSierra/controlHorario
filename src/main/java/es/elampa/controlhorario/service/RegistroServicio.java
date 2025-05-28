package es.elampa.controlhorario.service;


import es.elampa.controlhorario.model.Empleado;
import es.elampa.controlhorario.model.RegistroHorario;
import es.elampa.controlhorario.model.ResumenItem;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RegistroServicio {

    private List<RegistroHorario> registros = new ArrayList<>();
    private List<Empleado> empleados = new ArrayList<>();

    public void registrar(String nombre, String tipo) {
        RegistroHorario nuevo = new RegistroHorario(nombre, LocalDateTime.now(), tipo);
        registros.add(nuevo);
    }

    public List<RegistroHorario> obtenerTodos() {
        return registros;
    }

    public void agregarEmpleado(String nombre) {
        empleados.add(new Empleado(nombre));
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public List<ResumenItem> generarResumen() {
        List<ResumenItem> resumen = new ArrayList<>();
        Map<String, Map<LocalDate, List<RegistroHorario>>> agrupado = new HashMap<>();

        for (RegistroHorario r : registros) {
            agrupado
                .computeIfAbsent(r.getNombreEmpleado(), k -> new HashMap<>())
                .computeIfAbsent(r.getFechaHora().toLocalDate(), k -> new ArrayList<>())
                .add(r);
        }

        for (String nombre : agrupado.keySet()) {
            for (LocalDate fecha : agrupado.get(nombre).keySet()) {
                List<RegistroHorario> lista = agrupado.get(nombre).get(fecha);
                lista.sort(Comparator.comparing(RegistroHorario::getFechaHora));

                long totalSegundos = 0;
                for (int i = 0; i < lista.size() - 1; i++) {
                    RegistroHorario r1 = lista.get(i);
                    RegistroHorario r2 = lista.get(i + 1);
                    if (r1.getTipo().equals("Entrada") && r2.getTipo().equals("Salida")) {
                        totalSegundos += Duration.between(r1.getFechaHora(), r2.getFechaHora()).getSeconds();
                        i++;
                    }
                }
                double horas = Math.round((totalSegundos / 3600.0) * 100.0) / 100.0;
                resumen.add(new ResumenItem(nombre, fecha.toString(), horas));
            }
        }

        return resumen;
    }
}
