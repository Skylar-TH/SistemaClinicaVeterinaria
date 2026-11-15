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
public class Consulta {
    private List<Vacuna> vacunasAplicadas;
    private String idConsulta;
    private String diagnostico;
    private String notasMedicas;
    private LocalDateTime fecha;
    private double tarifaBase;
    private Mascota mascota;
    private Sedacion sedacion;
    
    
// ahorita corrijo que tengo que ir al mercado
    public Consulta(String idConsulta, String diagnostico, String notasMedicas, LocalDateTime fecha, double tarifaBase, Mascota mascota){
        if(tarifaBase<0){
            throw new IllegalArgumentException("La tarifa base no puede ser negativa.");
        }
        this.sedacion= null;
        this.vacunasAplicadas = new ArrayList<>();
        this.idConsulta = idConsulta;
        this.diagnostico = diagnostico;
        this.notasMedicas = notasMedicas;
        this.fecha = fecha;
        this.tarifaBase = tarifaBase;
        this.mascota= mascota;
        
    }
    
    public void aplicarVacuna(Vacuna vacuna) {
        if (vacuna == null) {
            throw new IllegalArgumentException("La vacuna no puede ser nula.");
        }
        this.vacunasAplicadas.add(vacuna);
    }

    // Asigna la sedación aplicada en esta consulta (si la hubo)
    public void aplicarSedacion(Sedacion sedacion) {
        if (sedacion == null) {
            throw new IllegalArgumentException("La sedación no puede ser nula.");
        }
        this.sedacion = sedacion;
    }
    
    public double calcularTotalConsulta(){
        double total= this.tarifaBase+this.mascota.CalcularRecargoBase(tarifaBase);
        for(Vacuna v : vacunasAplicadas){
            total+= v.getCosto();
            return total;
        }
        
        if(this.sedacion!=null){
            total+= this.sedacion.getCosto();
            return total;
        }
       return total;
    }

    public String getIdConsulta() {
        return idConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getNotasMedicas() {
        return notasMedicas;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public Sedacion getSedacion() {
        return sedacion;
    }
    
    public List<Vacuna> getVacunasAplicadas() {
        return Collections.unmodifiableList(vacunasAplicadas);
    }
}
