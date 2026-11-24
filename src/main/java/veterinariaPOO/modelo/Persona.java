/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariaPOO.modelo;

/**
 *
 * @author Usuario
 */
public abstract class Persona{
    private String nombre;
    private String telefono;
    private String id;
    private String email;

    public Persona(String nombre, String telefono, String id, String email) {
        if (id.isEmpty() || nombre.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException("El documento, el nombre y el email son obligatorios");
        }
        
        if (id.isBlank()|| nombre.isBlank() || email.isBlank()) {
            throw new IllegalArgumentException("El documento, el nombre y el email son obligatorios");
        }
        
        if (!telefono.matches("\\d{10}")) {
            throw new IllegalArgumentException("El telefono debe tener 10 digitos");
        }
        
        this.nombre = nombre;
        this.telefono = telefono;
        this.id = id;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
    
    
}
