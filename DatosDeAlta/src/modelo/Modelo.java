package modelo;

import java.util.ArrayList;

public class Modelo {

    // Clase interna para guardar la información completa de una dirección
    public static class DatosDireccion {
        public String codigoPostal;
        public String localidad;
        public String provincia;
        public String comunidad;
        public String pais;

        public DatosDireccion(String cp, String loc, String prov, String com, String pais) {
            this.codigoPostal = cp;
            this.localidad = loc;
            this.provincia = prov;
            this.comunidad = com;
            this.pais = pais;
        }
    }

    // Lista que actúa como nuestra "Base de Datos"
    private ArrayList<DatosDireccion> listaDirecciones = new ArrayList<>();

    public Modelo() {
        // Carga de datos iniciales
        listaDirecciones.add(new DatosDireccion("29001", "Málaga", "Málaga", "Andalucía", "España"));
        listaDirecciones.add(new DatosDireccion("28001", "Madrid", "Madrid", "Madrid", "España"));
        listaDirecciones.add(new DatosDireccion("08001", "Barcelona", "Barcelona", "Cataluña", "España"));
        listaDirecciones.add(new DatosDireccion("41001", "Sevilla", "Sevilla", "Andalucía", "España"));
        listaDirecciones.add(new DatosDireccion("15001", "A Coruña", "A Coruña", "Galicia", "España"));
        listaDirecciones.add(new DatosDireccion("48001", "Bilbao", "Vizcaya", "País Vasco", "España"));
    }

    // Método para buscar en la lista
    public DatosDireccion buscarDireccion(String codigoPostalBuscado) {
        for (DatosDireccion direccion : listaDirecciones) {
            if (direccion.codigoPostal.equals(codigoPostalBuscado)) {
                return direccion;
            }
        }
        return null; // Si no encuentra nada
    }
}