/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariaPOO.modelo;

import java.util.List;

/**
 *
 * @author Usuario
 */
public class HistorialMedico {
    private String idHistorial;
    private Mascota mascota;
    private List<Consulta> consultas;

    public HistorialMedico(String idHistorial, Mascota mascota) {
        this.idHistorial = idHistorial;
        this.mascota= mascota;
    }
    
    
}
