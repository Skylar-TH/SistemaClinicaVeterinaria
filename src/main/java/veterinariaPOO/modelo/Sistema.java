/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package veterinariaPOO.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sistema {

    private final List<Cita> citas;
    private final List<Pago> pagos;
    private final List<Consulta> consultas;
    private final List<Mascota> mascotas;
    private final List<Propietario> propietarios;
    private final List<Colaborador> colaboradores;
    private final List<Vacuna> vacunas;
    private double tarifaBase;
    private double costoSedacion;
    private Recepcionista recepcionista;
    //private final String nombreClinica;

    public Sistema() {
        this.citas = new ArrayList<>();
        this.pagos = new ArrayList<>();
        this.consultas= new ArrayList<>();
        this.mascotas = new ArrayList<>();
        this.propietarios = new ArrayList<>();
        this.colaboradores = new ArrayList<>();
        this.vacunas = new ArrayList<>();
        this.tarifaBase = 50000;
        this.costoSedacion = 30000;
        //this.nombreClinica = nombreClinica;
    }

    public void agregarCita(Cita nuevaCita) {
        if (nuevaCita != null && !this.citas.contains(nuevaCita)) {
            this.citas.add(nuevaCita);
        }
    }

    public void agregarPropietario(Propietario nuevoPropietario) {

        if (nuevoPropietario == null) {
            throw new IllegalArgumentException("El propietario no puede ser nulo");
        }
        for (Propietario p : propietarios) {
            if (p.getId().equals(nuevoPropietario.getId())) {
                throw new IllegalArgumentException("Ya existe un propietario con ese id");
            }
        }
        this.propietarios.add(nuevoPropietario);
    }

    public void agregarMascota(Mascota mascota) {
        if (mascota == null) {
            throw new IllegalArgumentException("La mascota no puede estar vacia");
        }
        String id = mascota.getId();
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id no debe estar vacio");
        }
        for (Mascota m : this.mascotas) {
            if (id.equals(m.getId())) {
                throw new IllegalArgumentException("No puede agregar la misma mascota 2 veces");
            }
        }

        this.mascotas.add(mascota);
    }

    public void agregarPago(Pago pago) {
        if (pago != null && !this.pagos.contains(pago)) {
            this.pagos.add(pago);
        }
    }
    
    public void agregarConsulta(Consulta nuevaConsulta){
        if(nuevaConsulta!= null && !this.consultas.contains(nuevaConsulta)){
            this.consultas.add(nuevaConsulta);
        }
        
    }

    /*revisen que estos dos metodos los veo innecesarios porque el recepcionista verifica las instancias y
     lo envia a agregar colaborador, lo que hace que el arreglo ya sepa quien es quien*/
    public void agregarRecepcionista(Recepcionista recepcionista) {
        agregarColaborador(recepcionista);
    }

    public void agregarVeterinario(Veterinario veterinario) {
        agregarColaborador(veterinario);
    }

    public void agregarColaborador(Colaborador colaborador) {

        for (Colaborador c : colaboradores) {
            if (c.getId().equals(colaborador.getId())) {
                throw new IllegalArgumentException("Ya existe un colaborador con ese documento");
            }
        }
        this.colaboradores.add(colaborador);
    }

    public void agregarVacuna(Vacuna vacuna) {
        if (vacuna == null) {
            throw new IllegalArgumentException("La vacuna no puede ser nula");
        }
        
        if (vacuna.getNombre() == null || vacuna.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre de la vacuna es obligatorio");
        }
        if (vacuna.getCosto() < 0) {
            throw new IllegalArgumentException("El costo de la vacuna no puede ser negativo");
        }
        
        for (Vacuna v : vacunas) {
            if (v.getNombre().equalsIgnoreCase(vacuna.getNombre())) {
                throw new IllegalArgumentException("Ya existe una vacuna con ese nombre");
            }
        }
        this.vacunas.add(vacuna);
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        if (tarifaBase <= 0) {
            throw new IllegalArgumentException("La tarifa base debe ser mayor a 0");
        }
        this.tarifaBase = tarifaBase;
    }

    public double getCostoSedacion() {
        return costoSedacion;
    }

    public void setCostoSedacion(double costoSedacion) {
        if (costoSedacion < 0) {
            throw new IllegalArgumentException("El costo de la sedacion no puede ser negativo");
        }
        this.costoSedacion = costoSedacion;
    }

    public List<Vacuna> getVacunas() {
        return Collections.unmodifiableList(vacunas);
    }

    /*
    *las consultas no están guardadas en una lista propia: cada una vive dentro del HistorialMedico 
    de su mascota
    */
    public List<Consulta> getConsultas() {
        List<Consulta> todas = new ArrayList<>();
        for (Mascota m : mascotas) {
            todas.addAll(m.getHistorial().getConsultas());
        }
//Ordena la lista de menor a mayor según el ID de la consulta.
        todas.sort(Comparator.comparing(Consulta::getIdConsulta));
        return todas;
    }
// reemplaza al listarconsultas--// pendientes por facturar

    public List<Consulta> listarConsultasPendientes() {
        List<Consulta> pendientes = new ArrayList<>();
        for (Consulta c : getConsultas()) {
            if (!estaPagada(c)) {
                pendientes.add(c);
            }
        }
        return pendientes;
    }
    // ---- CitasPendientes-----por consulta
    public List<Cita> getCitasPendientes() {
    List<Cita> pendientes = new ArrayList<>();
    for (Cita c : citas) {
        if (!c.getEstado()) {
            pendientes.add(c);
        }
    }
    return Collections.unmodifiableList(pendientes);
}

    public boolean estaPagada(Consulta consulta) {
        for (Pago p : pagos) {
            if (p.getConsulta() == consulta) {
                return true;
            }
        }
        return false;
    }

    public Pago buscarPagoPorConsulta(Consulta consulta) {
        for (Pago p : pagos) {
            if (p.getConsulta() == consulta) {
                return p;
            }
        }
        throw new IllegalArgumentException("La consulta aun no tiene pago");
    }

    public Vacuna buscarVacuna(String nombre) {
        for (Vacuna v : vacunas) {
            if (v.getNombre().equalsIgnoreCase(nombre)) {
                return v;
            }
        }
        throw new IllegalArgumentException("La vacuna " + nombre + " no esta registrada (agreguela en Ajustes)");
    }

    public Recepcionista obtenerRecepcionista() {
        for (Colaborador c : colaboradores) {
            if (c instanceof Recepcionista) {
                return (Recepcionista) c;
            }
        }
        throw new IllegalArgumentException("Debe registrar al menos una recepcionista para realizar cobros");
    }

   

    public List<Pago> getPagos() {
        return Collections.unmodifiableList(pagos);
    }

    public List<Mascota> getMascotas() {
        return Collections.unmodifiableList(mascotas);
    }

    public List<Propietario> getPropietarios() {
        return Collections.unmodifiableList(propietarios);
    }

    public List<Colaborador> getColaboradores() {
        return Collections.unmodifiableList(colaboradores);
    }

    /*public String getNombreClinica() {
        return nombreClinica;
    }*/
    

    public List<Recepcionista> getRecepcionistas() {
        List<Recepcionista> recepcionistas = new ArrayList();
        for (Colaborador c : colaboradores) {
            if (c instanceof Recepcionista v) {
                recepcionistas.add(v);
            }

        }
        return Collections.unmodifiableList(recepcionistas);
    }

    public List<Veterinario> getVeterinarios() {
        List<Veterinario> veterinarios = new ArrayList<>();
        for (Colaborador c : colaboradores) {
            if (c instanceof Veterinario v) {
                veterinarios.add(v);
            }
        }
        return Collections.unmodifiableList(veterinarios);
    }

    public Mascota buscarMascota(String id) {
        for (Mascota m : mascotas) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        throw new IllegalArgumentException("Mascota no encontrada");
    }

    public Propietario buscarPropietario(String id) {
        for (Propietario p : propietarios) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Propietario no encontrada");
    }

}
