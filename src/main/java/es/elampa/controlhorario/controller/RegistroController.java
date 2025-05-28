package es.elampa.controlhorario.controller;


import es.elampa.controlhorario.model.RegistroHorario;
import es.elampa.controlhorario.model.ResumenItem;
import es.elampa.controlhorario.service.RegistroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RegistroController {

    @Autowired
    private RegistroServicio servicio;

    @GetMapping("/")
    public String mostrarFormulario(Model model) {
        model.addAttribute("empleados", servicio.getEmpleados());
        return "registro";
    }

    @PostMapping("/registrar")
    public String registrar(@RequestParam String nombre, @RequestParam String tipo) {
        servicio.registrar(nombre, tipo);
        return "redirect:/historial";
    }

    @GetMapping("/historial")
    public String verHistorial(Model model) {
        List<RegistroHorario> registros = servicio.obtenerTodos();
        List<ResumenItem> resumen = servicio.generarResumen();
        model.addAttribute("registros", registros);
        model.addAttribute("resumen", resumen);
        return "historial";
    }

    @GetMapping("/alta-empleado")
    public String altaEmpleado() {
        return "alta";
    }

    @PostMapping("/alta-empleado")
    public String guardarEmpleado(@RequestParam String nombre) {
        servicio.agregarEmpleado(nombre);
        return "redirect:/";
    }
}
