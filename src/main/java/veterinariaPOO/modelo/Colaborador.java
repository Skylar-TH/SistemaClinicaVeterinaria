/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariaPOO.modelo;

/**
 *
 * @author Usuario
 */
public abstract class Colaborador extends Persona{
    // Solo lo vamos a tomar como atributo
    private double sueldo;
    
    public Colaborador(String nombre, String telefono, String id, String email, double sueldo) {
        super(nombre, telefono, id, email);
        if(sueldo<0){
        throw new IllegalArgumentException("El sueldo no puede ser negativo");
        }
         this.sueldo= sueldo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        if(sueldo<0){
        throw new IllegalArgumentException("El sueldo no puede ser negativo");
        }
        this.sueldo = sueldo;
    }
    
    
}
