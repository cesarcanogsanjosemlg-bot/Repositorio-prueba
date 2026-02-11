package vista;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {
  private JButton botonCrear;
	
	public Vista() {
		setTitle("Caja mágica");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		
		//Creación de botones
		botonCrear = new JButton("Crear");
		
		//Añadir a ventana
		add(botonCrear);
		
	}
	
	//Getters
	public JButton getBtnCrear() { return botonCrear; }
		
}
