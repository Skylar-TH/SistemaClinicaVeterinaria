/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariaPOO.modelo;

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

    public Veterinario(String especialidad, String tarjetaProfesional, List<Cita> citasAsignadas) {
        this.especialidad = especialidad;
        this.tarjetaProfesional = tarjetaProfesional;
        this.citasAsignadas = new ArrayList<>();
    }
    
    public void registrarDiagnostico(){
        
    }
    
    public void registrarVacunas(){
        
    }
    
    public void registrarNotasMedicas(){
        
    }
    
    
}
