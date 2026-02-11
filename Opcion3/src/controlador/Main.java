package controlador;

import javax.swing.SwingUtilities;

import vista.Vista;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Vista v = new Vista();
				Controlador c = new Controlador(v);
				v.setVisible(true);
			}
		});
	}
}
