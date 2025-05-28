package es.elampa.controlhorario.model;

public class ResumenItem {
    private String empleado;
    private String fecha;
    private double horas;

    public ResumenItem(String empleado, String fecha, double horas) {
        this.empleado = empleado;
        this.fecha = fecha;
        this.horas = horas;
    }

    public String getEmpleado() {
        return empleado;
    }

    public String getFecha() {
        return fecha;
    }

    public double getHoras() {
        return horas;
    }
}
