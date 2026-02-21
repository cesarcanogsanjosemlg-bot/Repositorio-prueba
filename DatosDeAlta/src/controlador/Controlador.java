package controlador;

import modelo.Modelo;
import vista.Vista;

public class Controlador {

    private Vista vista;
    private Modelo modelo;
    private Oyente oyente;

    public Controlador() {
        // Inicializamos componentes con nombres claros
        this.vista = new Vista();
        this.modelo = new Modelo();
        this.oyente = new Oyente(vista, modelo);
        
        // Asignamos los listeners a los componentes de la vista
        vista.getBotonValidar().addActionListener(oyente);
        vista.getCampoCodigoPostal().addFocusListener(oyente);
        
        // Mostramos la ventana
        vista.setVisible(true);
    }
}