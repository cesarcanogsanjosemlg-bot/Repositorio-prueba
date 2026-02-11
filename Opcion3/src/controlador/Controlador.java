/**
 * @authors César Cano González / Ricardo Ebhotemen Vázquez
 * @fecha 11/02/2026
 * @version 1.0.0
 */
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
