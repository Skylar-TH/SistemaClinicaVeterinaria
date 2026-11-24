package veterinariaPOO.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Veterinario extends Colaborador{
    private String especialidad;
    private String tarjetaProfesional;
    private List<Cita> citasAsignadas;
    private List<Consulta> consultasAtendidas;

    public Veterinario(String especialidad, String tarjetaProfesional,String nombre, String telefono, String id, String email, double sueldo) {
        super(nombre, telefono, id, email, sueldo);
        this.especialidad = especialidad;
        this.tarjetaProfesional = tarjetaProfesional;
        this.citasAsignadas = new ArrayList<>();
        this.consultasAtendidas = new ArrayList<>();
    }
    
    

    public void asignarCita(Cita cita) {
        if (cita != null && !this.citasAsignadas.contains(cita)) {
            this.citasAsignadas.add(cita);
        }
    }
    
    public Consulta registrarConsulta(HistorialMedico historial, String idConsulta, String diagnostico, String notasMedicas, LocalDateTime fecha, double tarifaBase) {
        if (historial == null) {
            throw new IllegalArgumentException("debe haber un historial médico ");
        }
        Consulta nuevaConsulta = historial.registrarConsulta(idConsulta, diagnostico,notasMedicas, fecha, tarifaBase, this);
        this.consultasAtendidas.add(nuevaConsulta);
        return nuevaConsulta;
    }
    public void registrarDiagnostico(Consulta consulta, String diagnostico){
        consulta.setDiagnostico(diagnostico);
    }
    
    
    public void registrarVacunas(Consulta consulta, Vacuna vacuna){
        consulta.aplicarVacuna(vacuna);
    }
    
    public void registrarNotasMedicas(Consulta consulta, String notasMedicas){
        consulta.setNotasMedicas(notasMedicas);
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }
    
    
}
