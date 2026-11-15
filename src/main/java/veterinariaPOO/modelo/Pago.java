/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariaPOO.modelo;

import java.time.LocalDateTime;

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
        if(consulta==null || propietario==null){
            throw new IllegalArgumentException("La consulta y el propietario no pueden estar vacios");
        }
        this.idPago = idPago;
        this.fechaPago = LocalDateTime.now();
        this.subtotal = subtotal;
        this.total = total;
        this.descuento = descuento;
        this.consulta = consulta;
        this.propietario= propietario;
        
        calcularPago();
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
    
    // terminar
    public String imprimirComprobante(){
        return " COMPROBANTE"
                + "ID pago:"+this.getIdPago()
                + " Fecha pago:"+this.getFechaPago()
                + " Consulta: ";
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
