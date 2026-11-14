/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariaPOO.modelo;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Consulta {
    private List<Vacuna> vacunasAplicadas;
    private String idConsulta;
    private String Diagnostico;
    private String notasMedicas;
    private LocalDateTime fecha;
    private double tarifaBase;
    
    public double calcularTotalConsulta(){
        //recibe un double
        return 0;
    }
}
