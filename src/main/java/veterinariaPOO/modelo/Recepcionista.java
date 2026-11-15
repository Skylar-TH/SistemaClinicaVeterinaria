/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariaPOO.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//listam son las mascotas 
// listap son propietarios
public class Recepcionista extends Colaborador{
    
    
    private final List<Pago> pagos;
    private final List<Cita> citas;

    public Recepcionista(String nombre, String telefono, String id, String email, List<Cita> citas, List<Pago> pagos) {
        super(nombre, telefono, id, email, sueldo);
        this.citas=new ArrayList<>();
        this.pagos= new ArrayList<>();
    }
    
   
    // los sueños si se cumplen solo tienes que ser uso de el, pensarlos en grande, siempre dar mas de lo suficiente, y entender que más alla de todo debo agradecer
    // y no dejar tampoco de lado aquellas cosas que quiero hacer 
    public void agendarCita(Sistema sistema, String idCita,  Veterinario veterinario, Mascota mascota,LocalDateTime fecha){
  if (sistema == null || mascota == null || veterinario == null) {
            throw new IllegalArgumentException("El sistema, la mascota y el veterinario son obligatorios.");
        }
        Cita nuevaCita = new Cita(idCita, veterinario, mascota, fecha, "PROGRAMADA");
        this.citas.add(nuevaCita);
        veterinario.asignarCita;
        sistema.agregarCita(nuevaCita);
        return nuevaCita;
    }
    
    public void registrarPropietario(Sistema sistema, String id, String nombre, String telefono, String direccion, boolean tieneDescuento){
        if (sistema == null) {
            throw new IllegalArgumentException("El sistema no puede ser nulo.");
        }
        Propietario nuevoPropietario = new Propietario(id, nombre, telefono, direccion, tieneDescuento);
        sistema.agregarPropietario(nuevoPropietario);
        return nuevoPropietario;
    }
    
    public void registrarMascota(Sistema sistema, Propietario propietario, Mascota mascota)  throws IllegalAccessException{
        if (sistema == null || propietario == null || mascota == null) {
            throw new IllegalArgumentException("El sistema, el propietario y la mascota son obligatorios.");
        }
        propietario.agregarMascota(mascota);
        sistema.agregarMascota(mascota);
       
    }
    
    public void realizarCobro(Sistema sistema, String idPago, Consulta consulta, Propietario propietario) {
    if (sistema == null || consulta == null || propietario == null) {
            throw new IllegalArgumentException("El sistema, la consulta y el propietario son obligatorios.");
        }
        Pago nuevoPago = new Pago(idPago, consulta, propietario);
        this.pagosRegistrados.add(nuevoPago);
        sistema.agregarPago(nuevoPago);
        return nuevoPago;
    }

   
    public void registrarColaborador (Colaborador colaborador) throws IllegalAccessException {
       if (sistema == null || nuevoColaborador == null) {
            throw new IllegalArgumentException("El sistema y el nuevo colaborador son obligatorios.");
        }
        sistema.agregarColaborador(nuevoColaborador);
    }
    
   public List<Cita> getCitasAgendadas() {
        return Collections.unmodifiableList(citasAgendadas);
    }

    public List<Pago> getPagosRegistrados() {
        return Collections.unmodifiableList(pagosRegistrados);
    }
    
    // aqui me falta indicarlo con el metodo este de que action escucha y selecciona para borrar
    public void eliminar(int posicion){
        listam.remove(posicion);
    }
    
    
    
    
    
}