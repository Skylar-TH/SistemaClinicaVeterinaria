/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariaPOO.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Usuario
 */
public class Pago {
    private String idPago;
    private LocalDateTime fechaPago;
    private double subtotal;
    private double total;
    private double descuento;
    private Consulta consulta;
    private Propietario propietario;
    

    public Pago(String idPago, Propietario propietario, Consulta consulta) {
       
        this.idPago = idPago;
        this.fechaPago = LocalDateTime.now();
        this.consulta = consulta;
        this.propietario= propietario;

    }
    
    public void calcularPago(){
        this.subtotal= this.consulta.calcularTotalConsulta();
        
        if(this.propietario.tieneDerechoDescuento()){
            this.descuento=this.subtotal*0.1;
        }else{
            this.descuento=0.0;
        }
        this.total=this.subtotal-this.descuento;
       
    }

    public String imprimirComprobante(){
         DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("           COMPROBANTE DE PAGO         \n");
        sb.append("ID pago: ").append(this.idPago).append("\n");
        sb.append("Fecha pago: ").append(this.fechaPago.format(formato)).append("\n");
        sb.append("Consulta: ").append(this.consulta.getIdConsulta()).append("\n");
        sb.append("Fecha consulta: ").append(this.consulta.getFecha().format(formato)).append("\n");
        sb.append("Propietario: ").append(this.propietario.getNombre()).append("\n");
        sb.append("Mascota: ").append(this.consulta.getMascota().getNombre()).append("\n");
        sb.append("Veterinario: ").append(this.consulta.getVeterinario().getNombre()).append("\n");
        sb.append("-----------------------------------------\n");
        sb.append(String.format("Tarifa base: $ %,.2f%n", this.consulta.getTarifaBase()));
        if (this.consulta.calcularRecargo() > 0) {
            sb.append(String.format("Recargo mascota exotica (25%%): $ %,.2f%n", this.consulta.calcularRecargo()));
        }
        for (Vacuna v : this.consulta.getVacunasAplicadas()) {
            sb.append(String.format("Vacuna %s: $ %,.2f%n", v.getNombre(), v.getCosto()));
        }
        if (this.consulta.isUsoSedacion()) {
            sb.append(String.format("Insumos de sedacion: $ %,.2f%n", this.consulta.getCostoSedacion()));
        }
        sb.append("-----------------------------------------\n");
        sb.append(String.format("Subtotal: $ %,.2f%n", this.subtotal));
        if (this.descuento > 0) {
            sb.append(String.format("Descuento (10%% por mas de 2 mascotas): - $ %,.2f%n", this.descuento));
        }
        sb.append(String.format("TOTAL A PAGAR: $ %,.2f%n", this.total));
        return sb.toString();
    }

    
    public String getIdPago() {
        return idPago;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTotal() {
        return total;
    }

    public double getDescuento() {
        return descuento;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public Propietario getPropietario() {
        return propietario;
    }
    
    
    
}
