/**
 * @authors César Cano González / Ricardo Ebhotemen Vázquez
 * @fecha 11/02/2026
 * @version 1.0.0
 */
package controlador;

import modelo.Datos;
import vista.Ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Oyente implements ActionListener {
    private Ventana vista;
    private Datos modelo;

    public Oyente(Ventana vista, Datos modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.setSize(modelo.anchoVentana, modelo.altoVentana);
        this.vista.boton.setText(modelo.textoBotonCrear);
        this.vista.caja.setColumns(modelo.anchoCaja);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (modelo.existe == false) {
            modelo.existe = true;
            vista.caja.setText(modelo.textoGuardado);
            vista.add(vista.caja);
            vista.boton.setText(modelo.textoBotonOcultar);
        } else {
            if (vista.caja.getText().length() == 0) {
                vista.caja.setText(modelo.textoGuardado);
                vista.boton.setText(modelo.textoBotonOcultar);
            } else {
                modelo.textoGuardado = vista.caja.getText();
                vista.caja.setText("");
                vista.boton.setText(modelo.textoBotonMostrar);
            }
        }  
    }
}
