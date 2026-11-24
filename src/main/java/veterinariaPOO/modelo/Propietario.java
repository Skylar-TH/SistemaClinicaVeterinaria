/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariaPOO.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Propietario extends Persona {

    private List<Mascota> mascotas;

    public Propietario(String nombre, String telefono, String id, String email) {
        super(nombre, telefono, id, email);
        this.mascotas = new ArrayList<>();
    }

    public void agregarMascotasPropia(Mascota mascota) {
        if (mascota != null && !this.mascotas.contains(mascota)) {
            this.mascotas.add(mascota);

            mascota.setPropietario(this);
        }
    }

    public boolean tieneDerechoDescuento() {
        if (mascotas.size() > 2) {
            return true;
        }
        return false;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

}
