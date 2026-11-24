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
    private boolean usoSedacion;
    private double costoSedacion;
    private Veterinario veterinario;
    
    
// ahorita corrijo que tengo que ir al mercado
    public Consulta(String idConsulta, String diagnostico, String notasMedicas, LocalDateTime fecha, double tarifaBase, Mascota mascota, Veterinario veterinario){
        if(tarifaBase<0){
            throw new IllegalArgumentException("La tarifa base no puede ser negativa.");
        }
        this.usoSedacion = false;
        this.costoSedacion = 0.0;
        this.vacunasAplicadas = new ArrayList<>();
        this.idConsulta = idConsulta;
        setDiagnostico(diagnostico);
        setNotasMedicas(notasMedicas);
        this.fecha = fecha;
        this.tarifaBase = tarifaBase;
        this.mascota= mascota;
        this.veterinario= veterinario;
        
    }
    
    public void aplicarVacuna(Vacuna vacuna) {
        if (vacuna == null) {
            throw new IllegalArgumentException("La vacuna no puede ser nula.");
        }
        // solo las mascotas convencionales llevan vacunas (y su esquema)
        if (!(this.mascota instanceof MascotaConvencional)) {
            throw new IllegalArgumentException("Solo las mascotas convencionales reciben vacunas.");
        }
        this.vacunasAplicadas.add(vacuna);
        ((MascotaConvencional) this.mascota).agregarVacunas(vacuna);
    }

   
    public void aplicarSedacion(double costoInsumos) {
        if (!(this.mascota instanceof MascotaExotica)) {
            throw new IllegalArgumentException("Solo las mascotas exoticas llevan sedacion.");
        }
        if (costoInsumos < 0) {
            throw new IllegalArgumentException("El costo de los insumos no puede ser negativo.");
        }
        this.usoSedacion = true;
        this.costoSedacion = costoInsumos;
    }
    
    public double calcularRecargo() {
        return this.mascota.CalcularRecargoBase(this.tarifaBase);
    }
    
    public double calcularCostoVacunas() {
        double total = 0.0;
        for (Vacuna v : vacunasAplicadas) {
            total += v.getCosto();
        }
        return total;
    }
    
    public double calcularTotalConsulta(){
        return this.tarifaBase + calcularRecargo() + calcularCostoVacunas() + getCostoSedacion();
    }

    public Veterinario getVeterinario() {
        return veterinario;
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

    public boolean isUsoSedacion() {
        return usoSedacion;
    }

    public double getCostoSedacion() {
        if (usoSedacion) {
            return costoSedacion;
        }
        return 0.0;
    }
    
    public List<Vacuna> getVacunasAplicadas() {
        return Collections.unmodifiableList(vacunasAplicadas);
    }

    public void setVacunasAplicadas(List<Vacuna> vacunasAplicadas) {
        this.vacunasAplicadas = vacunasAplicadas;
    }

    public void setIdConsulta(String idConsulta) {
        this.idConsulta = idConsulta;
    }

    public void setDiagnostico(String diagnostico) {
        if(diagnostico== null || diagnostico.isBlank()){
            throw new IllegalArgumentException("El diagnostico debe ser ingresado");
        }
        this.diagnostico = diagnostico;
    }

    public void setNotasMedicas(String notasMedicas) {
        if(notasMedicas== null || notasMedicas.isBlank()){
            throw new IllegalArgumentException("Debe ingresar las notas medicas");
        }
        this.notasMedicas = notasMedicas;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    
    

 
    
    
}
