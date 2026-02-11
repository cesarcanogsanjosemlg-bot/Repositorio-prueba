package controlador;
/**
 * @authors César Cano González / Ricardo
 * @fecha 11/02/2026
 * @version 1.0.0
 */
import javax.swing.SwingUtilities;

import vista.Ventana;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Ventana v = new Ventana();
				Controlador c = new Controlador();
				v.setVisible(true);
			}
		});
	}
}
