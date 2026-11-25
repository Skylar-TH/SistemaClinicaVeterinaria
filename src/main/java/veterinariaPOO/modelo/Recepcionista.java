/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariaPOO.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recepcionista extends Colaborador {

   

    public Recepcionista(String nombre, String telefono, String id, String email, double sueldo) {
        super(nombre, telefono, id, email, sueldo);
        
    }

    public Cita agendarCita(Sistema sistema, String idCita, Veterinario veterinario, Mascota mascota, LocalDateTime fechaHora, Recepcionista recepcionista) {
        if (sistema == null || mascota == null || veterinario == null || fechaHora == null ) {
            throw new IllegalArgumentException("El sistema, la mascota, el veterinario, la fecha/hora");
        }
        Cita nuevaCita = new Cita(idCita, veterinario, mascota, fechaHora, false,recepcionista);
        sistema.agregarCita(nuevaCita);
        
        

        return nuevaCita;
    }

    public Propietario registrarPropietario(Sistema sistema, String id, String nombre, String telefono, String email) {
        
        Propietario nuevoPropietario = new Propietario(nombre, telefono, id, email);
        sistema.agregarPropietario(nuevoPropietario);
        return nuevoPropietario;
    }

    public void registrarMascota(Sistema sistema, Propietario propietario, Mascota mascota) {
        if (sistema == null || propietario == null || mascota == null) {
            throw new IllegalArgumentException("El sistema, el propietario y la mascota son obligatorios");
        }
        // ((MascotaConvencional) mascota).getRaza() surgio al poner mascota.getraza();
        if (mascota instanceof MascotaConvencional) {
            String raza= ((MascotaConvencional) mascota).getRaza();
            if(raza.isBlank() || raza.isEmpty()){
            throw new IllegalArgumentException("La raza es obligatoria");
            }
        }
        sistema.agregarMascota(mascota);
        propietario.agregarMascotasPropia(mascota);

    }

    public Pago realizarCobro(Sistema sistema, String idPago, Consulta consulta) {
        if (sistema == null || consulta == null) {
            throw new IllegalArgumentException("El sistema, la consulta y el propietario son obligatorios.");
        }

        Mascota mascota = consulta.getMascota();
        if (mascota == null || mascota.getPropietario() == null) {
            throw new IllegalArgumentException("La mascota o el propietario no puede ser nulos");
        }
        Propietario propietario = mascota.getPropietario();
        Pago nuevoPago = new Pago(idPago, propietario, consulta);
        sistema.agregarPago(nuevoPago);
        nuevoPago.calcularPago();
        return nuevoPago;
    }

    public void registrarColaborador(Sistema sistema, Colaborador nuevoColaborador){
        
        if(sistema==null || nuevoColaborador==null){
           throw new IllegalArgumentException("El sistema y el colaborador son obligatorios");
        }
        
        if(nuevoColaborador instanceof Recepcionista){
            sistema.agregarColaborador(nuevoColaborador);
        }
        
        if(nuevoColaborador instanceof Veterinario){
            
          String especialidad= ((Veterinario) nuevoColaborador).getEspecialidad();
          String tarjeta= ((Veterinario) nuevoColaborador).getTarjetaProfesional();
          
        if (especialidad.isEmpty() || tarjeta.isEmpty()) {
                throw new IllegalArgumentException("La especialidad y la tarjeta profesional son obligatorias");
            }
        
        sistema.agregarColaborador(nuevoColaborador);
        }
    }

   

    
}
