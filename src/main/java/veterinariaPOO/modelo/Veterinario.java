/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariaPOO.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Veterinario {
    private String especialidad;
    private String tarjetaProfesional;
    private List<Cita> citasAsignadas;
    private List<Consulta> consultasAtendidas;

    public Veterinario(String especialidad, String tarjetaProfesional, List<Cita> citasAsignadas, List<Consulta> consultasAtendidas) {
        this.especialidad = especialidad;
        this.tarjetaProfesional = tarjetaProfesional;
        this.citasAsignadas = new ArrayList<>();
        this.consultasAtendidas = new ArrayList<>();
    }

    public void asignarCita(Cita cita) {
        if (cita != null && !this.citasAsignadas.contains(cita)) {
            this.citasAsignadas.add(cita);
        }
    }
    
    
    public Consulta registrarConsulta(HistorialMedico historial, String idConsulta, String diagnostico, String notasMedicas, LocalDateTime fecha, double tarifaBase) {
        if (historial == null) {
            throw new IllegalArgumentException("debe haber un historial médico ");
        }
        Consulta nuevaConsulta = historial.registrarConsulta(idConsulta, diagnostico,notasMedicas, fecha, tarifaBase);
        this.consultasAtendidas.add(nuevaConsulta);
        return nuevaConsulta;
    }
    
    public void registrarDiagnostico(){
        
    }
    
    public void registrarVacunas(){
        
    }
    
    public void registrarNotasMedicas(){
        
    }
    
    
}
