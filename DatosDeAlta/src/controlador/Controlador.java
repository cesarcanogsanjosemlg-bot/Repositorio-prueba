package controlador;
import modelo.Modelo;
import vista.Vista;

public class Controlador {
    private Vista v;
    private Modelo m;
    private Oyente o;

    public Controlador() {
        this.v = new Vista();
        this.m = new Modelo();
        this.o = new Oyente(v, m);
        
        v.getBtn().addActionListener(o);
        v.getCP().addFocusListener(o);
        
        v.setVisible(true);
    }
}