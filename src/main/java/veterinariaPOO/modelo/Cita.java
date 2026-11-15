/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariaPOO.modelo;

import java.time.LocalDateTime;

/**
 *
 * @author Usuario
 */
public class Cita {
    private String idCita;
    private Veterinario veterinario;
    private Mascota mascota;
    private LocalDateTime fecha;
    private String estado; // puede ser programada, cancelada o atendida
    private Consulta consultaGenerada;

    public Cita(String idCita, Veterinario veterinario, Mascota mascota, LocalDateTime fecha, String estado) {
        if (fecha == null || mascota == null || veterinario == null) {
            throw new IllegalArgumentException("La fecha/hora, la mascota y el veterinario son obligatorios.");
        }
        this.idCita = idCita;
        this.fecha = fecha;
        if (estado != null && !estado.isBlank()) {
             this.estado = estado;
        } else {
         this.estado = "PROGRAMADA";
        }
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.consultaGenerada = null;
    }
    
    public String getIdCita() {
        return idCita;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
}
