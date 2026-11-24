/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package principal.veterinariapoo;

import veterinariaPOO.controlador.Controlador;
import veterinariaPOO.modelo.*;
import veterinariaPOO.vista.Vista;

/**
 *
 * @author Usuario
 */
public class VeterinariaPOO {

    public static void main(String[] args) {

        Vista vista = new Vista();
        Sistema sistema = new Sistema();
        Recepcionista recepcionista = new Recepcionista("Laura Gomez", "3042567328", "REC-001", "laura@gmail.com", 0);
        Recepcionista recepcionista2 = new Recepcionista("Carlos Perez", "3101234567", "REC-002", "carlos@gmail.com", 0);

        Veterinario veterinario1 = new Veterinario("Medicina general", "TP-001", "Maria Mora", "3205550001", "VET-001", "maria@gmail.com", 0);
        Veterinario veterinario2 = new Veterinario("Animales exoticos", "TP-002", "Andres Rios", "3205550002", "VET-002", "andres@gmail.com", 0);

        recepcionista.registrarColaborador(sistema, recepcionista);
        recepcionista.registrarColaborador(sistema, recepcionista2);
        recepcionista.registrarColaborador(sistema, veterinario1);
        recepcionista.registrarColaborador(sistema, veterinario2);
        
        sistema.agregarVacuna(new Vacuna("Rabia", 35000));
        sistema.agregarVacuna(new Vacuna("Parvovirus", 45000));
        sistema.agregarVacuna(new Vacuna("Moquillo", 40000));
        sistema.agregarVacuna(new Vacuna("Triple felina", 50000));

        Controlador controlador = new Controlador(vista, sistema, recepcionista);

        vista.setVisible(true);
    }
}
