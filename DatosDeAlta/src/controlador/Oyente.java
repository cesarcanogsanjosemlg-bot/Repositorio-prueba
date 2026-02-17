package controlador;

import modelo.Modelo;
import vista.Vista;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class Oyente implements ActionListener, FocusListener {

    private Vista vista;
    private Modelo modelo;

    public Oyente(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    // --- EVENTOS ---

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBotonValidar()) {
            validarFormularioCompleto();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == vista.getCampoCodigoPostal()) {
            rellenarDireccionAutomatica();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        // No necesario
    }

    // --- LÓGICA PRIVADA ---

    private void rellenarDireccionAutomatica() {
        String codigoPostal = vista.getCampoCodigoPostal().getText().trim();
        
        // Buscamos en el modelo usando el nuevo método
        Modelo.DatosDireccion datos = modelo.buscarDireccion(codigoPostal);

        if (datos != null) {
            vista.setLocalidad(datos.localidad);
            vista.setProvincia(datos.provincia);
            vista.setComunidad(datos.comunidad);
            vista.setPais(datos.pais);
            
            // Simulación de código de municipio (3 primeros dígitos)
            String codMunicipio = codigoPostal.length() >= 3 ? codigoPostal.substring(0, 3) : "";
            vista.setMunicipio(codMunicipio);
        } else {
            // Limpiar campos si no existe
            vista.setLocalidad("");
            vista.setProvincia("-");
            vista.setComunidad("-");
            vista.setPais("España");
            vista.setMunicipio("");
        }
    }

    private void validarFormularioCompleto() {
        StringBuilder mensajeError = new StringBuilder();

        // 1. Validar Campos Obligatorios
        if (vista.getCampoNombre().getText().trim().isEmpty() || 
            vista.getCampoApellido1().getText().trim().isEmpty() || 
            vista.getCampoDocumento().getText().trim().isEmpty()) {
            mensajeError.append("- Nombre, Primer Apellido y Documento son obligatorios.\n");
        }

        // 2. Validar Teléfonos (al menos uno)
        boolean tieneTelefono = !vista.getCampoTelefonoCasa().getText().isEmpty() ||
                                !vista.getCampoMovil1().getText().isEmpty() ||
                                !vista.getCampoMovil2().getText().isEmpty();
        
        if (!tieneTelefono) {
            mensajeError.append("- Debe introducir al menos un teléfono de contacto.\n");
        }

        // 3. Validar Documento Identidad
        String tipoDoc = (String) vista.getComboTipoDocumento().getSelectedItem();
        String numDoc = vista.getCampoDocumento().getText().trim();

        if ("DNI".equals(tipoDoc) && !numDoc.matches("\\d+")) {
            mensajeError.append("- El DNI debe contener solo números.\n");
        }
        if ("NIE".equals(tipoDoc) && !numDoc.matches("^[A-Za-z].*[A-Za-z]$")) {
            mensajeError.append("- El NIE debe empezar y terminar por letra (Total 8 caracteres).\n");
        }

        // 4. Validar Email
        if (!vista.getCampoEmail().getText().isEmpty() && !vista.getCampoEmail().getText().contains("@")) {
            mensajeError.append("- El Email es incorrecto (falta @).\n");
        }

        // 5. Validar Numéricos
        if (!esTextoNumerico(vista.getCampoNumero().getText()) || 
            !esTextoNumerico(vista.getCampoTelefonoCasa().getText())) {
            mensajeError.append("- Número de calle o teléfonos contienen letras.\n");
        }

        // Resultado Final
        if (mensajeError.length() > 0) {
            JOptionPane.showMessageDialog(vista, mensajeError.toString(), "Errores Detectados", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vista, "Formulario Validado Correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean esTextoNumerico(String texto) {
        return texto == null || texto.isEmpty() || texto.matches("\\d+");
    }
}