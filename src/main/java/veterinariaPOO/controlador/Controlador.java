package veterinariaPOO.controlador;

import veterinariaPOO.modelo.*;
import veterinariaPOO.vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Controlador implements ActionListener {

    private Recepcionista recepcionista;
    private Sistema sistema;
    private Vista vista;
    private Cita citaActual;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Controlador(Vista vista, Sistema sistema, Recepcionista recepcionista) {
        this.recepcionista = recepcionista;
        this.sistema = sistema;
        this.vista = vista;
        this.vista.setControlador(this);

        this.vista.setTxtTarifaBase(formatoNumero(sistema.getTarifaBase()));
        this.vista.setTxtCostoSedacion(formatoNumero(sistema.getCostoSedacion()));
        this.vista.setTxtFecha(LocalDateTime.now().format(FORMATO_FECHA));
        actualizarListas();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            switch (comando) {
                case "REGISTRAR_PERSONA" ->
                    registrarPersona();
                case "REGISTRAR_MASCOTA" ->
                    registrarMascota();
                case "SELECCIONAR_MASCOTA" ->
                    actualizarOpcionesConsulta();
                case "GENERAR_CITA" ->
                    generarCita();
                case "ATENDER_CONSULTA" ->
                    atenderConsulta();
                case "SELECCIONAR_CITA" -> {
                    actualizarOpcionesConsulta();
                }
                case "GENERAR_CONSULTA" ->
                    generarConsulta();

                case "GENERAR_FACTURA" ->
                    generarFactura();
                case "MOSTRAR_HISTORIAL" ->
                    mostrarHistorial();
                case "AGREGAR_VACUNA" ->
                    agregarVacuna();
                case "CAMBIAR_TARIFA" ->
                    cambiarTarifa();
                case "CAMBIAR_COSTO_SEDACION" ->
                    cambiarCostoSedacion();
                default -> {
                }
            }
        } catch (IllegalArgumentException ex) {
            vista.mostrarError(ex.getMessage());
        }
    }

    
    
    private void registrarPersona() {
        String tipo = vista.getTipoPersona();
        String id = vista.getTxtID().trim();
        String nombre = vista.getTxtNombrePersona().trim();
        String telefono = vista.getTxtTelefono().trim();
        String email = vista.getTxtEmeil().trim();
        Colaborador nuevoColaborador;

        if ("Propietario".equals(tipo)) {
            recepcionista.registrarPropietario(sistema, id, nombre, telefono, email);

        } else if ("Recepcionista".equals(tipo)) {
            Recepcionista recepcionista2 = new Recepcionista(nombre, telefono, id, email, 0);
            nuevoColaborador = recepcionista2;

            recepcionista.registrarColaborador(sistema, nuevoColaborador);
        } else {
            String especialidad = vista.getTxtEspecialidad().trim();
            String tarjeta = vista.getTxtTarjetaProfecional().trim();
            Veterinario veterinario = new Veterinario(especialidad, tarjeta, nombre, telefono, id, email, 0);
            nuevoColaborador = veterinario;

            recepcionista.registrarColaborador(sistema, nuevoColaborador);
        }

        vista.limpiarFormularioPersona();
        actualizarListas();
        vista.mostrarMensaje(tipo + " registrado correctamente");
    }

    private void registrarMascota() {
        int posPropietario = vista.getIndicePropietario();
        if (posPropietario < 0) {
            throw new IllegalArgumentException("Primero registre un propietario y seleccionelo");
        }
        
        Propietario propietario = sistema.getPropietarios().get(posPropietario);

        String nombre = vista.getTxtNombreMascota().trim();
        int edad = leerEntero(vista.getTxtEdad(), "La edad");
        double peso = leerDecimal(vista.getTxtPeso(), "El peso");
        
        // nos ayuda a crearle un ID a la mascota
        String id = "M-" + String.format("%03d", sistema.getMascotas().size() + 1);
        Mascota mascota;

        if ("Convencional".equals(vista.getTipoMascota())) {
            String raza = vista.getTxtRaza().trim();
            // esta creando el objeto mascota conven
            MascotaConvencional convencional = new MascotaConvencional(id, nombre, edad, peso, propietario, raza);

            // el esquema se escribe con los nombres de las vacunas separados por coma
            List<Vacuna> esquema = new ArrayList<>();
            String textoEsquema = vista.getTxtEsquemaVacunas().trim();
            if (!textoEsquema.isEmpty()) {
                for (String nombreVacuna : textoEsquema.split(",")) {
                    esquema.add(sistema.buscarVacuna(nombreVacuna.trim()));
                }
            }
            for (Vacuna v : esquema) {
                convencional.agregarVacunas(v);
            }
            mascota = convencional;
        } else {
            int permiso = leerEntero(vista.getTxtPermisoICA(), "El permiso ICA");
            mascota = new MascotaExotica(id, nombre, edad, peso, propietario, permiso, vista.isRequiereSedacion());
        }

        recepcionista.registrarMascota(sistema, propietario, mascota);

        vista.limpiarFormularioMascota();
        actualizarListas();
        String mensaje = "Mascota registrada con ID " + id;
        if (propietario.tieneDerechoDescuento()) {
            mensaje += "\nEl propietario tiene mas de 2 mascotas: sus consultas tendran 10% de descuento.";
        }
        vista.mostrarMensaje(mensaje);
    }
    // se ejecuta desde que se inicia el programa por eso no hay excepcion

    

    public void generarCita() {

        int posRecepcionista = vista.getIndiceRecepcionistaCita();
        if (posRecepcionista < 0) {
            throw new IllegalArgumentException("Seleccione un recepcionista para agendar la Cita");
        }
        int posMascota = vista.getIndiceMascotaConsulta();
        if (posMascota < 0) {
            throw new IllegalArgumentException("Seleccione una mascota (registrela primero)");
        }
        int posVeterinario = vista.getIndiceVeterinario();
        if (posVeterinario < 0) {
            throw new IllegalArgumentException("Seleccione un veterinario (registrelo primero)");
        }

        Recepcionista recepcionista = sistema.getRecepcionistas().get(posRecepcionista);
        Mascota mascota = sistema.getMascotas().get(posMascota);
        Veterinario veterinario = sistema.getVeterinarios().get(posVeterinario);

        LocalDateTime fecha;
        try {
            fecha = LocalDateTime.parse(vista.getTxtFecha().trim(), FORMATO_FECHA);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("La fecha debe tener el formato dd/MM/yyyy HH:mm");
        }
        String id = "CIT-" + String.format("%03d", sistema.getCitasPendientes().size() + 1);

        citaActual = recepcionista.agendarCita(sistema, id, veterinario, mascota, fecha, recepcionista);
        
        
        
        citaActual= null;
        actualizarListas(); 
        actualizarOpcionesConsulta();
        vista.mostrarMensaje(id + " registrado correctamente");
    }
    
   

    public void atenderConsulta() {
        int pos = vista.getIndiceCitapendiente();
    if (pos < 0) {
        throw new IllegalArgumentException("No hay citas pendientes para atender");
    }
        citaActual = sistema.getCitasPendientes().get(pos);
        actualizarOpcionesConsulta();
    }
    
    
    
     private void actualizarOpcionesConsulta() {
        if (citaActual == null) {
        vista.habilitarConsulta(false);
        return;}
        vista.habilitarConsulta(true);

        Mascota mascota = citaActual.getMascota();
        
        if (mascota instanceof MascotaExotica) {
            vista.configurarOpcionesConsulta(false, true, ((MascotaExotica) mascota).isRequiereSedacion());
        } else {
            vista.configurarOpcionesConsulta(true, false, false);
        }
    }

    public void generarConsulta() {
      if (citaActual == null) {
        throw new IllegalArgumentException("Primero seleccione una cita y presione Atender Consulta");
    }
    Mascota mascota = citaActual.getMascota();
    Veterinario veterinario = citaActual.getVeterinario();
    LocalDateTime fecha = citaActual.getFecha();
    String diagnostico = vista.getTxtDiagnostico().trim();
    String notas = vista.getTxtNotasMedicas().trim();

    Vacuna vacuna = null;
    int posVacuna = vista.getIndiceVacuna();
    if (mascota instanceof MascotaConvencional && posVacuna > 0) {
        vacuna = sistema.getVacunas().get(posVacuna - 1);
    }

    String id = "CON-" + String.format("%03d", sistema.getConsultas().size() + 1);
    Consulta consulta = veterinario.registrarConsulta(mascota.getHistorial(), id, diagnostico, notas, fecha, sistema.getTarifaBase());
    if (vacuna != null) {
        veterinario.registrarVacunas(consulta, vacuna);
    }
    if (mascota instanceof MascotaExotica && vista.isUsoSedacion()) {
        consulta.aplicarSedacion(sistema.getCostoSedacion());
    }

    citaActual.agregarConsulta(consulta);   // la cita pasa de false a true
    citaActual = null;                      // ya no hay cita en atención

    vista.limpiarFormularioConsulta();
    vista.setTxtFecha(LocalDateTime.now().format(FORMATO_FECHA));
    actualizarOpcionesConsulta();           // vuelve a bloquear la parte derecha
    actualizarCitas();            // la cita atendida sale del combo
    actualizarPendientes();                 // la consulta aparece en Facturación
    vista.mostrarMensaje("Consulta " + id + " generada.\nValor de la consulta (antes de descuentos): " + dinero(consulta.calcularTotalConsulta()));
    }

    // ---------------- Facturacion ----------------
    private void generarFactura() {
        int pos = vista.getIndiceConsultaPendiente();
        if (pos < 0) {
            throw new IllegalArgumentException("No hay consultas pendientes de cobro");
        }
        Consulta consulta = sistema.listarConsultasPendientes().get(pos);
        Recepcionista recepcionista = sistema.obtenerRecepcionista();

        String idPago = "PAG-" + String.format("%03d", sistema.getPagos().size() + 1);
        Pago pago = recepcionista.realizarCobro(sistema, idPago, consulta);

        vista.setFactura(pago.imprimirComprobante(recepcionista));
        actualizarPendientes();
    }

    private void mostrarHistorial() {
        int pos = vista.getIndiceMascotaHistorial();
        if (pos < 0) {
            throw new IllegalArgumentException("Seleccione una mascota");
        }
        Mascota mascota = sistema.getMascotas().get(pos);

        List<Object[]> filas = new ArrayList<>();
        for (Consulta c : mascota.getHistorial().getConsultas()) {
            String vacunas = "-";
            for (Vacuna v : c.getVacunasAplicadas()) {
                if (vacunas.equals("-")) {
                    vacunas = v.getNombre();
                } else {
                    vacunas += ", " + v.getNombre();
                }
            }
            String total = "Pendiente de cobro";
            if (sistema.estaPagada(c)) {
                total = dinero(sistema.buscarPagoPorConsulta(c).getTotal());
            }
            filas.add(new Object[]{c.getFecha().format(FORMATO_FECHA), c.getVeterinario().getNombre(), c.getDiagnostico(), vacunas, total});
        }
        vista.mostrarHistorial(filas);
    }

    private void agregarVacuna() {
        String nombre = vista.getTxtNombreVacuna().trim();
        double costo = leerDinero(vista.getTxtCostoVacuna(), "El costo de la vacuna");
        sistema.agregarVacuna(new Vacuna(nombre, costo));

        vista.limpiarFormularioVacuna();
        actualizarVacunas();
        vista.mostrarMensaje("Vacuna " + nombre + " agregada (" + dinero(costo) + ")");
    }

    private void cambiarTarifa() {
        double tarifa = leerDinero(vista.getTxtTarifaBase(), "La tarifa base");
        sistema.setTarifaBase(tarifa);
        vista.setTxtTarifaBase(formatoNumero(tarifa));
        vista.mostrarMensaje("Tarifa base actualizada a " + dinero(tarifa) + "\nSe usara en todas las consultas nuevas.");
    }

    private void cambiarCostoSedacion() {
        double costo = leerDinero(vista.getTxtCostoSedacion(), "El costo de la sedacion");
        sistema.setCostoSedacion(costo);
        vista.setTxtCostoSedacion(formatoNumero(costo));
        vista.mostrarMensaje("Costo de insumos de sedacion actualizado a " + dinero(costo) + "\nSe usara en las consultas nuevas.");
    }

    //------actualizaciones de vista o iconos ------
    private void actualizarCitas() {
        List<String> items = new ArrayList<>();
        for (Cita c : sistema.getCitasPendientes()) {
            items.add(c.getIdCita() + "-" + c.getRecepcionista().getNombre() + "-" + c.getVeterinario().getNombre());
        }
        vista.cargarCitas(items);
    }

    private void actualizarVacunas() {
        List<String> items = new ArrayList<>();
        for (Vacuna v : sistema.getVacunas()) {
            items.add(v.getNombre() + " - " + dinero(v.getCosto()));
        }
        vista.cargarVacunas(items);
    }
// este es el de facturacion
        private void actualizarPendientes() {
        List<String> items = new ArrayList<>();
        for (Consulta c : sistema.listarConsultasPendientes()) {
            items.add(c.getIdConsulta() + " - " + c.getMascota().getNombre());
        }
        vista.cargarConsultasPendientes(items);
    }
        
// aqui es donde se actualizan las listas despues de registrar a las personas, se recoge el nombre 
    // y el id para enviarlo al combo y que este se muestre en pantalla

    private void actualizarListas() {
        List<String> propietarios = new ArrayList<>();
        for (Propietario p : sistema.getPropietarios()) {
            propietarios.add(p.getId() + " - " + p.getNombre());
        }
        vista.cargarPropietarios(propietarios);

        List<String> mascotas = new ArrayList<>();
        for (Mascota m : sistema.getMascotas()) {
            mascotas.add(m.getId() + " - " + m.getNombre() + " (" + m.getPropietario().getNombre() + ")");
        }
        vista.cargarMascotas(mascotas);

        List<String> veterinarios = new ArrayList<>();
        for (Veterinario v : sistema.getVeterinarios()) {
            veterinarios.add(v.getNombre() + " - " + v.getEspecialidad());
        }
        vista.cargarVeterinarios(veterinarios);

        List<String> recepcionistas = new ArrayList<>();
        for (Recepcionista v : sistema.getRecepcionistas()) {
            recepcionistas.add(v.getNombre() + " - " + v.getId());
        }
        vista.cargarRecepcionista(recepcionistas);
        
         

        // subactualizaciones, las ponemos como otros metodos para no sobre cargar el metodo
        // se actualiza vacunas que aparecen en consultas
        actualizarCitas();
        actualizarVacunas();
        actualizarPendientes();
        actualizarOpcionesConsulta();
    }

    private int leerEntero(String texto, String campo) {
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(campo + " debe ser un numero entero");
        }
    }

    private double leerDecimal(String texto, String campo) {
        try {
            double valor = Double.parseDouble(texto.trim().replace(",", "."));
            if (!Double.isFinite(valor)) {
                throw new NumberFormatException();
            }
            return valor;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(campo + " debe ser un numero valido");
        }
    }

    // acepta 50000, 50.000, $50.000 o 50.000,50
    private double leerDinero(String texto, String campo) {
        String limpio = texto.trim().replace("$", "").replace(" ", "");
        if (limpio.matches("\\d{1,3}(\\.\\d{3})+(,\\d+)?")) {
            limpio = limpio.replace(".", "");
        }
        return leerDecimal(limpio, campo);
    }

    private String dinero(double valor) {
        return String.format("$ %,.2f", valor);
    }

    private String formatoNumero(double valor) {
        return BigDecimal.valueOf(valor).stripTrailingZeros().toPlainString();
    }

}
