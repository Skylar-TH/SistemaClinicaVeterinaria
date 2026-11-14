/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariaPOO.modelo;

/**
 *
 * @author Usuario
 */
public class MascotaExotica extends Mascota{
    private int numeroPermisoICA;
    private boolean requiereSedacion;

    public MascotaExotica(String id, String nombre, int edad, double peso, Propietario propietario, HistorialMedico historiaClinica) {
        super(id, nombre, edad, peso, propietario, historiaClinica);
    }

    @Override
    public double CalcularRecargoBase(double tarifaBase) {
        return tarifaBase*0.25;
    }

    public int getNumeroPermisoICA() {
        return numeroPermisoICA;
    }

    public void setNumeroPermisoICA(int numeroPermisoICA) {
        this.numeroPermisoICA = numeroPermisoICA;
    }

    public boolean isRequiereSedacion() {
        return requiereSedacion;
    }

    public void setRequiereSedacion(boolean requiereSedacion) {
        this.requiereSedacion = requiereSedacion;
    }
    
    
    
}
