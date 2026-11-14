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
    private Recepcionista recepcionista;
    private Veterinario veterinario;
    private Mascota mascota;
    private LocalDateTime fecha;
    private String estado; // puede ser programada, cancelada o atendida

    public Cita(String idCita, Recepcionista recepcionista, Veterinario veterinario, Mascota mascota, LocalDateTime fecha, String estado) {
        this.idCita = idCita;
        this.recepcionista = recepcionista;
        this.veterinario = veterinario;
        this.mascota = mascota;
        this.fecha = fecha;
        this.estado = estado;
    }

    public String getIdCita() {
        return idCita;
    }

    public Recepcionista getRecepcionista() {
        return recepcionista;
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

    public void setRecepcionista(Recepcionista recepcionista) {
        this.recepcionista = recepcionista;
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
