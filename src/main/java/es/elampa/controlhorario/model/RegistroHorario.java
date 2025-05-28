package es.elampa.controlhorario.model;

import java.time.LocalDateTime;

public class RegistroHorario {
    private String nombreEmpleado;
    private LocalDateTime fechaHora;
    private String tipo;

    public RegistroHorario(String nombreEmpleado, LocalDateTime fechaHora, String tipo) {
        this.nombreEmpleado = nombreEmpleado;
        this.fechaHora = fechaHora;
        this.tipo = tipo;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getTipo() {
        return tipo;
    }
}
