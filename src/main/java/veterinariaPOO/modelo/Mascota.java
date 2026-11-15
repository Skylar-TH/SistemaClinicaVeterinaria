package veterinariaPOO.modelo;

public abstract class Mascota {
    private String id;
    private String nombre;
    private int edad;
    private double peso;
    private Propietario propietario;
    private HistorialMedico historiaClinica;

    public Mascota(String id, String nombre, int edad, double peso, Propietario propietario, HistorialMedico historiaClinica) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.propietario = propietario;
        this.historiaClinica=new HistorialMedico("HC-"+id,this);
    }

    public HistorialMedico getHistorial() {
        return historiaClinica;
    }

    public void setHistorial(HistorialMedico historiaClinica) {
        this.historiaClinica= historiaClinica;
    }
    
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getPeso() {
        return peso;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
    
    // utilizar el metodo abstracto para cada tipo de mascota
    
    public abstract double CalcularRecargoBase(double tarifaBase);
}
