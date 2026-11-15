/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariaPOO.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class HistorialMedico {
    private String idHistorial;
    private Mascota mascota;
    private List<Consulta> consultas;

    
    public HistorialMedico(String idHistorial, Mascota mascota) {
        this.idHistorial = idHistorial;
        this.mascota= mascota;
        this.consultas= new ArrayList<>();
        // no se crea dentro del constructor por la cardinalidad
    }
    
    // a esas consultas les voy a pasar su historial
    public Consulta registrarConsulta(String idConsulta, String diagnostico, String notasMedicas, LocalDateTime fecha, double tarifaBase){
        Consulta nuevaconsulta = new Consulta(idConsulta, diagnostico, notasMedicas, fecha, tarifaBase, this.mascota);
        this.consultas.add(nuevaconsulta);
        return nuevaconsulta; 
    }

    public String getIdHistorial() {
        return idHistorial;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public List<Consulta> getConsultas() {
        return Collections.unmodifiableList(consultas);
        //Collections.unmodifiableList(consultas)es un metodo que permite
        //leer la lista más no modificarla
    }
    
    
    
}
