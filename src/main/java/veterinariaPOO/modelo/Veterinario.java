package veterinariaPOO.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Veterinario extends Colaborador{
    private String especialidad;
    private String tarjetaProfesional;
    
   
    public Veterinario(String especialidad, String tarjetaProfesional,String nombre, String telefono, String id, String email, double sueldo) {
        super(nombre, telefono, id, email, sueldo);
        this.especialidad = especialidad;
        this.tarjetaProfesional = tarjetaProfesional;
        
    }
    
    public Consulta registrarConsulta(Sistema sistema,HistorialMedico historial, String idConsulta, String diagnostico, String notasMedicas, LocalDateTime fecha, double tarifaBase) {
        if (historial == null) {
            throw new IllegalArgumentException("debe haber un historial médico ");
        }
        Consulta nuevaConsulta = historial.registrarConsulta(idConsulta, diagnostico,notasMedicas, fecha, tarifaBase, this);
        sistema.agregarConsulta(nuevaConsulta);
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
