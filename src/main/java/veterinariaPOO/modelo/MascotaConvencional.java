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
public class MascotaConvencional extends Mascota{
    private String raza;
    private List<Vacuna> esquemaVacunas;

    public MascotaConvencional(String id, String nombre, int edad, double peso, Propietario propietario, HistorialMedico historiaClinica) {
        super(id, nombre, edad, peso, propietario, historiaClinica);
        this.esquemaVacunas = new ArrayList<>();
    }
//hora
    @Override
    public double CalcularRecargoBase(double tarifaBase) {
        return 0.0;
    }
    
}
