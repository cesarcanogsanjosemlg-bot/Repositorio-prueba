package vista;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {
    public JButton boton;
    public JTextField caja;

    public Vista() {
        setTitle("Opcion 3 dom");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        boton = new JButton(); 
        add(boton);

        caja = new JTextField(); 
       
    }
}
