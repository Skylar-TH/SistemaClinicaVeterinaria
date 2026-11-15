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
public class Sistema {
    private final List<Cita> citas;
    private final List<Pago> pagos;
    private final List<Mascota> mascotas;
    private final List<Propietario> propietarios;
    private final List<Colaborador> listaColaborador;
    private final String nombreClinica;

    public Sistema(List<Cita> citas, List<Pago> pagos, List<Mascota> mascotas, List<Propietario> propietarios, List<Colaborador> listaColaborador, String nombreClinica) {
       if(nombreClinica==null || nombreClinica.isBlank()){
           throw new IllegalArgumentException("Debe ingresar el nombre");
       }
        this.nombreClinica= nombreClinica;
        this.citas = new ArrayList<>();
        this.pagos = new ArrayList<>();
        this.mascotas =new ArrayList<>();
        this.propietarios = new ArrayList<>();
        this.listaColaborador = new ArrayList<>();
        
    }
    
    

    
    
    
    
}
