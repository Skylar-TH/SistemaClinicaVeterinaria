/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariaPOO.modelo;

import java.util.ArrayList;
import java.util.List;

//listam son las mascotas 
// listap son propietarios
public class Recepcionista extends Colaborador{
    
    private final List<Mascota> listam;
    private final List<Propietario> listap;
    private final List<Cita> listac;
    private final List<Colaborador> listaColaborador;

    public Recepcionista(String nombre, String telefono, String id, String email, double sueldo, List<Mascota> listam, List<Propietario> listap, List<Cita> listac) {
        super(nombre, telefono, id, email, sueldo);
        this.listam = new ArrayList<>();
        this.listap = new ArrayList<>();
        this.listac = new ArrayList<>();
        this.listaColaborador = new ArrayList<>();
    }
    
   
    // los sueños si se cumplen solo tienes que ser uso de el, pensarlos en grande, siempre dar mas de lo suficiente, y entender que más alla de todo debo agradecer
    // y no dejar tampoco de lado aquellas cosas que quiero hacer 
    public void agendarCita(Cita c){
        
        
        listac.add(c);
    }
    
    public void realizarCobro(){
        // si quedo con el metodo contador
        //int cantidad = recepcionista.contarMascotas(propietario);
        //boolean tieneDescuento =propietario.tieneDerechoADescuento(cantidad);
    }
    
    public void registrarPropietario(Propietario p)  throws IllegalAccessException{
        
        if(p.getId().isBlank()){
            throw new IllegalAccessException("Debe ingresar un Id");
        }
        if(p.getNombre().isBlank()){
            throw new IllegalAccessException("Tiene que ingresar el nombre");
        }
         if (p.getTelefono().isBlank()){
            throw new IllegalArgumentException("Verifique la edad, debe ser mayor a 0");}
        if(p.getTelefono().length()>10 || p.getTelefono().length()<10){
            throw new IllegalArgumentException("El numero ingresado debe tener 10 digitos");
        }
         if (p.getEmail().isBlank()) {
            throw new IllegalArgumentException("Debe ingresar un peso mayor a 0");
        }
         listap.add(p);
    }
    
    public void registrarMascota(Mascota m, Propietario propietario)throws IllegalAccessException {

        if(m.getId().isBlank()){
            throw new IllegalAccessException("Debe ingresar un Id");
        }
        if(m.getNombre().isBlank()){
            throw new IllegalAccessException("Tiene que ingresar el nombre");
        }
         if (m.getEdad() <= 0){
            throw new IllegalArgumentException("Verifique la edad, debe ser mayor a 0");}
        
         if (m.getPeso() <= 0) {
            throw new IllegalArgumentException("Debe ingresar un peso mayor a 0");
        }
         m.setPropietario(propietario);
         listam.add(m);
    }

   
    public void registrarColaborador (Colaborador colaborador) throws IllegalAccessException {
        if(colaborador.getNombre().isBlank()){
            throw new IllegalAccessException("Tiene que ingresar el nombre");
        }
        if (colaborador.getSueldo() <= 0) {
            throw new IllegalArgumentException("Debe ingresar un sueldo mayor a 0");
        }
        listaColaborador.add(colaborador);
    }
    
    public List<Mascota> listar(){
        return new ArrayList<>(listam);
    }
    
    // aqui me falta indicarlo con el metodo este de que action escucha y selecciona para borrar
    public void eliminar(int posicion){
        listam.remove(posicion);
    }
    
    
    
    
    
}