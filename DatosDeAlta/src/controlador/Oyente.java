package controlador;

import modelo.Modelo;
import vista.Vista;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class Oyente implements ActionListener, FocusListener {

    private Vista v;
    private Modelo m;

    public Oyente(Vista v, Modelo m) {
        this.v = v;
        this.m = m;
    }

    // --- SECCIÓN DE EVENTOS (Ahora están limpios) ---

    @Override
    public void actionPerformed(ActionEvent e) {
        // Al pulsar el botón, llamamos a la función de validar
        procesarValidacion();
    }

    @Override
    public void focusLost(FocusEvent e) {
        // Al salir del campo CP, llamamos a la función de buscar dirección
        procesarCodigoPostal();
    }

    @Override
    public void focusGained(FocusEvent e) {
        // No se usa, pero es obligatorio dejarlo
    }

    // --- LÓGICA DE NEGOCIO (Métodos privados separados) ---

    private void procesarCodigoPostal() {
        String cp = v.getCP().getText().trim();
        Modelo.Datos d = m.getDatos(cp);

        if (d != null) {
            v.setLoc(d.loc);
            v.setProv(d.prov);
            v.setCom(d.com);
            v.setPais(d.pais);
            // Extraer 3 primeros dígitos para municipio
            v.setMun(cp.length() >= 3 ? cp.substring(0, 3) : "");
        } else {
            v.setLoc("");
            v.setProv("-");
            v.setCom("-");
            v.setPais("España");
            v.setMun("");
        }
    }

    private void procesarValidacion() {
        StringBuilder errores = new StringBuilder();

        // 1. Comprobamos campos vacíos básicos
        if (hayCamposVacios()) {
            errores.append("- Falta Nombre, Apellidos o Documento.\n");
        }

        // 2. Comprobamos que haya al menos un teléfono
        if (!hayTelefono()) {
            errores.append("- Debe poner al menos un teléfono.\n");
        }

        // 3. Validamos el formato del documento (DNI o NIE)
        String errorDoc = validarDocumento();
        if (!errorDoc.isEmpty()) {
            errores.append(errorDoc);
        }

        // 4. Validamos el Email
        if (!v.getEmail().getText().isEmpty() && !v.getEmail().getText().contains("@")) {
            errores.append("- El Email debe llevar arroba (@).\n");
        }

        // 5. Validamos que los campos numéricos no tengan letras
        if (!esNumero(v.getNum().getText()) || !esNumero(v.getT1().getText())) {
            errores.append("- El número de calle o teléfono tiene letras.\n");
        }

        // MOSTRAR RESULTADO
        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(v, errores.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(v, "Formulario Correcto", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // --- FUNCIONES AUXILIARES (Para no ensuciar la validación) ---

    private boolean hayCamposVacios() {
        return v.getNom().getText().trim().isEmpty() ||
               v.getAp1().getText().trim().isEmpty() ||
               v.getDoc().getText().trim().isEmpty();
    }

    private boolean hayTelefono() {
        return !v.getT1().getText().isEmpty() ||
               !v.getT2().getText().isEmpty() ||
               !v.getT3().getText().isEmpty();
    }

    private String validarDocumento() {
        String tipo = (String) v.getCbDoc().getSelectedItem();
        String numero = v.getDoc().getText().trim();

        if ("DNI".equals(tipo) && !numero.matches("\\d+")) {
            return "- El DNI solo puede tener números.\n";
        }
        if ("NIE".equals(tipo) && !numero.matches("^[A-Za-z].*[A-Za-z]$")) {
            return "- El NIE debe empezar y terminar por letra.\n";
        }
        return ""; // Sin errores
    }

    private boolean esNumero(String texto) {
        return texto == null || texto.isEmpty() || texto.matches("\\d+");
    }
}