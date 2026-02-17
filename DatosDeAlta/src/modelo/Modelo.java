package modelo;
import java.util.ArrayList;

public class Modelo {
    public static class Datos {
        public String cp, loc, prov, com, pais;
        public Datos(String cp, String l, String p, String c, String pa) { 
            this.cp=cp; loc=l; prov=p; com=c; pais=pa; 
        }
    }
    private ArrayList<Datos> db = new ArrayList<>();

    public Modelo() {
        db.add(new Datos("29001", "Málaga", "Málaga", "Andalucía", "España"));
        db.add(new Datos("28001", "Madrid", "Madrid", "Madrid", "España"));
        db.add(new Datos("08001", "Barcelona", "Barcelona", "Cataluña", "España"));
        db.add(new Datos("41001", "Sevilla", "Sevilla", "Andalucía", "España"));
        db.add(new Datos("15001", "A Coruña", "A Coruña", "Galicia", "España"));
        db.add(new Datos("48001", "Bilbao", "Vizcaya", "País Vasco", "España"));
    }
    
    public Datos getDatos(String cpBuscado) {
        for(Datos d : db) {
            if(d.cp.equals(cpBuscado)) return d;
        }
        return null;
    }
}