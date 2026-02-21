package vista;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {

    // Variables con nombres descriptivos
    private JTextField campoNombre, campoApellido1, campoApellido2, campoDocumento, campoEmail;
    private JTextField campoTelefonoCasa, campoMovil1, campoMovil2;
    private JTextField campoNombreVia, campoNumero, campoPiso, campoPuerta;
    private JTextField campoCodigoPostal, campoLocalidad, campoMunicipio;
    
    private JComboBox<String> comboSexo, comboTipoDocumento, comboTipoVia;
    private JLabel etiquetaProvincia, etiquetaComunidad, etiquetaPais;
    private JButton botonValidar;

    public Vista() {
        setTitle("Formulario de Altas");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Principal con Scroll
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        add(new JScrollPane(panelCentral));

        // --- SECCIÓN DATOS PERSONALES ---
        JPanel panelPersonales = new JPanel(new GridBagLayout());
        panelPersonales.setBorder(BorderFactory.createTitledBorder("Datos Personales"));
        panelCentral.add(panelPersonales);
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.weightx = 1.0;

        agregarCampo(panelPersonales, "Nombre:", campoNombre = new JTextField(), constraints, 0, 0);
        agregarCampo(panelPersonales, "Primer Apellido:", campoApellido1 = new JTextField(), constraints, 2, 0);
        agregarCampo(panelPersonales, "Segundo Apellido:", campoApellido2 = new JTextField(), constraints, 4, 0);
        
        // Sexo y Tipo Documento
        panelPersonales.add(new JLabel("Sexo"), generarConstraints(6, 0));
        panelPersonales.add(comboSexo = new JComboBox<>(new String[]{"HOMBRE", "MUJER"}), generarConstraints(7, 0));
        
        panelPersonales.add(new JLabel("Tipo Documento"), generarConstraints(0, 1));
        panelPersonales.add(comboTipoDocumento = new JComboBox<>(new String[]{"DNI", "NIE"}), generarConstraints(1, 1));
        
        agregarCampo(panelPersonales, "Núm Documento:", campoDocumento = new JTextField(), constraints, 2, 1);
        agregarCampo(panelPersonales, "Email:", campoEmail = new JTextField(), constraints, 0, 2);
        
        // Teléfonos
        agregarCampo(panelPersonales, "Teléfono Casa:", campoTelefonoCasa = new JTextField(), constraints, 4, 2);
        agregarCampo(panelPersonales, "Móvil Personal:", campoMovil1 = new JTextField(), constraints, 6, 2);
        agregarCampo(panelPersonales, "Móvil Trabajo:", campoMovil2 = new JTextField(), constraints, 0, 3);

        // --- SECCIÓN DIRECCIÓN ---
        JPanel panelDireccion = new JPanel(new GridBagLayout());
        panelDireccion.setBorder(BorderFactory.createTitledBorder("Dirección"));
        panelCentral.add(panelDireccion);

        // Vía
        panelDireccion.add(new JLabel("Tipo Vía"), generarConstraints(0, 0));
        panelDireccion.add(comboTipoVia = new JComboBox<>(new String[]{"Calle", "Avenida", "Plaza", "Paseo"}), generarConstraints(1, 0));
        
        agregarCampo(panelDireccion, "Nombre Vía:", campoNombreVia = new JTextField(), constraints, 2, 0);
        agregarCampo(panelDireccion, "Número:", campoNumero = new JTextField(), constraints, 4, 0);
        agregarCampo(panelDireccion, "Piso:", campoPiso = new JTextField(), constraints, 6, 0);
        agregarCampo(panelDireccion, "Puerta:", campoPuerta = new JTextField(), constraints, 8, 0);

        // Código Postal y Automáticos
        agregarCampo(panelDireccion, "Código Postal:", campoCodigoPostal = new JTextField(), constraints, 0, 1);
        campoCodigoPostal.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));

        agregarCampo(panelDireccion, "Localidad:", campoLocalidad = new JTextField(), constraints, 2, 1);
        campoLocalidad.setEditable(false);
        
        agregarCampo(panelDireccion, "Municipio:", campoMunicipio = new JTextField(), constraints, 4, 1);
        campoMunicipio.setEditable(false);
        
        // Etiquetas de solo lectura
        panelDireccion.add(new JLabel("Provincia"), generarConstraints(6, 1));
        panelDireccion.add(etiquetaProvincia = new JLabel("-"), generarConstraints(7, 1));
        etiquetaProvincia.setForeground(Color.BLUE);

        panelDireccion.add(new JLabel("Comunidad"), generarConstraints(8, 1));
        panelDireccion.add(etiquetaComunidad = new JLabel("-"), generarConstraints(9, 1));
        etiquetaComunidad.setForeground(Color.BLUE);
        
        panelDireccion.add(new JLabel("País"), generarConstraints(0, 2));
        panelDireccion.add(etiquetaPais = new JLabel("España"), generarConstraints(1, 2));

        // Botón
        botonValidar = new JButton("Validar Formulario");
        add(botonValidar, BorderLayout.SOUTH);
    }

    // Métodos auxiliares para diseño
    private void agregarCampo(JPanel panel, String texto, Component componente, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x; gbc.gridy = y; panel.add(new JLabel(texto), gbc);
        gbc.gridx = x + 1; panel.add(componente, gbc);
    }
    
    private GridBagConstraints generarConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x; gbc.gridy = y; gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); gbc.weightx = 1.0;
        return gbc;
    }

    // --- GETTERS (Nombres descriptivos) ---
    public JButton getBotonValidar() { return botonValidar; }
    
    public JTextField getCampoNombre() { return campoNombre; }
    public JTextField getCampoApellido1() { return campoApellido1; }
    public JTextField getCampoDocumento() { return campoDocumento; }
    public JTextField getCampoEmail() { return campoEmail; }
    
    public JTextField getCampoTelefonoCasa() { return campoTelefonoCasa; }
    public JTextField getCampoMovil1() { return campoMovil1; }
    public JTextField getCampoMovil2() { return campoMovil2; }
    
    public JComboBox<String> getComboTipoDocumento() { return comboTipoDocumento; }
    
    public JTextField getCampoNumero() { return campoNumero; }
    public JTextField getCampoCodigoPostal() { return campoCodigoPostal; }
    
    // --- SETTERS (Para actualizar la vista) ---
    public void setLocalidad(String texto) { campoLocalidad.setText(texto); }
    public void setMunicipio(String texto) { campoMunicipio.setText(texto); }
    public void setProvincia(String texto) { etiquetaProvincia.setText(texto); }
    public void setComunidad(String texto) { etiquetaComunidad.setText(texto); }
    public void setPais(String texto) { etiquetaPais.setText(texto); }
}