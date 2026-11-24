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
    private LocalDateTime fechaHora;
    private boolean estado; // false para programada, true para atendida
    private Consulta consultaGenerada;
    private Recepcionista recepcionista;

    public Cita(String idCita, Veterinario veterinario, Mascota mascota, LocalDateTime fechaHora, boolean estado, Recepcionista recepcionista) {
        if (idCita.isEmpty() || idCita.isBlank()) {
            throw new IllegalArgumentException("El ID de la cita no puede estar vacío.");
        }
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.consultaGenerada = null;
        this.recepcionista= recepcionista;
    }
    
    public void agregarConsulta(Consulta consulta){
        if(estado){
            throw new IllegalArgumentException("La cita ya fue atendida");
        }
        
        if(consulta.getMascota()!=this.mascota || consulta.getVeterinario()!= this.veterinario){
            throw new IllegalArgumentException("La mascota debe coincidir con el veterinario del que ha sido atendido");
        }
        
        this.consultaGenerada= consulta;
        this.estado= true;
    }
    
    /**
     *public eliminarCita(Consulta) 
     * 
    */
    
    public Recepcionista getRecepcionista() {
        return recepcionista;
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
        return fechaHora;
    }

    public boolean getEstado() {
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
        this.fechaHora = fecha;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
    
}
