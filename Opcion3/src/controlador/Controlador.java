package controlador;

import modelo.Datos;
import vista.Ventana;

public class Controlador {
    public void arrancar() {
        Datos modelo = new Datos();
        Ventana vista = new Ventana();    
        Oyente oyente = new Oyente(vista, modelo);
        vista.boton.addActionListener(oyente);       
        vista.setVisible(true);
    }
}
